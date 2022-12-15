package com.misshhok.coursework.infrastructure.rest.controller;

import com.misshhok.coursework.application.service.EmployeeService;
import com.misshhok.coursework.infrastructure.presistience.model.EmployeeModel;
import com.misshhok.coursework.infrastructure.rest.requests.ChangeStatusDto;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Employee", description = "API для работы с работниками")
@RequestMapping("employers/")
public class EmployeeController {
  private final EmployeeService employeeService;
  private final AmqpTemplate rabbitTemplate;

  @Operation(summary = "Gets all users", tags = "Employee")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the users",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = EmployeeModel.class)))
            })
      })
  @GetMapping()
  public ResponseEntity<List<EmployeeModel>> getEmployers() {
    rabbitTemplate.convertAndSend(
        "notificationQueue", "Попытка получить список актуальных работников");
    return ResponseEntity.ok().body(employeeService.getAllEmployers());
  }

  @Operation(summary = "Создать нового работника", tags = "Employee")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Создан новый работник",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = EmployeeModel.class))
            })
      })
  @PostMapping()
  public ResponseEntity<EmployeeModel> createEmployee(
      @RequestBody CreateEmployeeDto createEmployeeDto) {
    rabbitTemplate.convertAndSend(
        "notificationQueue",
        "Попытка создать нового работника с должностью/ями - " + createEmployeeDto.getPositions());
    return ResponseEntity.ok().body(employeeService.createEmployee(createEmployeeDto));
  }

  @Operation(summary = "Изменить статус работника", tags = "Employee")
  @ApiResponses(
      value = {@ApiResponse(responseCode = "204", description = "Статус работника изменен")})
  @PutMapping
  public ResponseEntity<Void> changeStatus(@RequestBody ChangeStatusDto changeStatusDto) {
    rabbitTemplate.convertAndSend(
        "notificationQueue", "Попытка изменить статус сотрудника с ID " + changeStatusDto.getId());
    employeeService.changeEmployeeStatus(changeStatusDto);
    return ResponseEntity.accepted().build();
  }

  @Operation(summary = "Удалить работника по ID", tags = "Employee")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    rabbitTemplate.convertAndSend("notificationQueue", "Попытка удалить сотрудника с ID " + id);
    employeeService.deleteEmployee(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Получить информацию о работнике по ID", tags = "Employee")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Получена информация о работнике",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = EmployeeModel.class))
            })
      })
  @GetMapping("{id}")
  public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id) {
    rabbitTemplate.convertAndSend(
        "notificationQueue", "Попытка получить информацию о работнике с ID - " + id);
    return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
  }
}
