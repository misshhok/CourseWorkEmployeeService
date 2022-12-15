package com.misshhok.coursework.infrastructure.rest.controller;

import com.misshhok.coursework.application.service.EmployeeService;
import com.misshhok.coursework.infrastructure.presistience.model.PositionModel;
import com.misshhok.coursework.infrastructure.rest.requests.CreatePositionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Positions", description = "API для работы с должностями")
@RequestMapping("positions/")
public class PositionController {
  private final EmployeeService employeeService;
  private final AmqpTemplate rabbitTemplate;

  @Operation(summary = "Создание должности", tags = "Positions")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Успешно созданная должность",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PositionModel.class))
            })
      })
  @PostMapping()
  public ResponseEntity<PositionModel> createPosition(
      @RequestBody CreatePositionDto createPositionDto) {
    rabbitTemplate.convertAndSend(
        "notificationQueue", "Попытка создать новую должность - " + createPositionDto.getTitle());
    return ResponseEntity.ok().body(employeeService.createPosition(createPositionDto));
  }

  @Operation(summary = "Получить все должности", tags = "Positions")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Получен список с должностями",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = PositionModel.class)))
            })
      })
  @GetMapping()
  public ResponseEntity<List<PositionModel>> getPositions() {
    rabbitTemplate.convertAndSend("notificationQueue", "Попытка получить список должностей");
    return ResponseEntity.ok().body(employeeService.getAllPositions());
  }

  @Operation(summary = "Удалить позицию по ID", tags = "Positions")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
    rabbitTemplate.convertAndSend("notificationQueue", "Попытка удалить должность с ID " + id);
    employeeService.deletePosition(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Получить информацию о должности по ID", tags = "Employee")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Получена информация о должности",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PositionModel.class))
            })
      })
  @GetMapping("{id}")
  public ResponseEntity<PositionModel> getPositionById(@PathVariable Long id) {
    rabbitTemplate.convertAndSend(
        "notificationQueue", "Попытка получить информацию о должности c ID- " + id);
    return ResponseEntity.ok().body(employeeService.getPositionById(id));
  }
}
