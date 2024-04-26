package com.misshhok.coursework.infrastructure.rest.controller;

import com.misshhok.coursework.application.service.EmployeeService;
import com.misshhok.coursework.infrastructure.persistance.model.Position;
import com.misshhok.coursework.infrastructure.rest.requests.CreatePositionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Positions", description = "API для работы с должностями")
@RequestMapping("positions/")
public class PositionWebAdapter {
  private final EmployeeService employeeService;

  @Operation(summary = "Создание должности", tags = "Positions")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Успешно созданная должность",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Position.class))
            })
      })
  @PostMapping()
  public ResponseEntity<Position> createPosition(@RequestBody CreatePositionDto createPositionDto) {
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
                  array = @ArraySchema(schema = @Schema(implementation = Position.class)))
            })
      })
  @GetMapping()
  public ResponseEntity<List<Position>> getPositions() {
    return ResponseEntity.ok().body(employeeService.getAllPositions());
  }

  @Operation(summary = "Удалить позицию по ID", tags = "Positions")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
    employeeService.deletePosition(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Получить информацию о должности по ID", tags = "Positions")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Получена информация о должности",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Position.class))
            })
      })
  @GetMapping("{id}")
  public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
    return ResponseEntity.ok().body(employeeService.getPositionById(id));
  }
}
