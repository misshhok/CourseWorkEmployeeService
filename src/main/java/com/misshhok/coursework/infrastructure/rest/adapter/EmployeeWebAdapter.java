package com.misshhok.coursework.infrastructure.rest.adapter;

import com.misshhok.coursework.application.service.EmployeeService;
import com.misshhok.coursework.infrastructure.persistance.model.Employee;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Employee", description = "API для работы с работниками")
@Slf4j
@RequestMapping("employers/")
public class EmployeeWebAdapter {
  private final EmployeeService employeeService;
  @Operation(summary = "Получить список работников", tags = "Employee")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Получен список работников",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Employee.class)))
            })
      })
  @GetMapping()
  public ResponseEntity<List<Employee>> getEmployers() {
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
                  schema = @Schema(implementation = Employee.class))
            })
      })
  @PostMapping()
  public ResponseEntity<Employee> createEmployee(
      @RequestBody CreateEmployeeDto createEmployeeDto) {
    log.info(createEmployeeDto.toString());
    return ResponseEntity.ok().body(employeeService.createEmployee(createEmployeeDto));
  }

  @Operation(summary = "Отправить работника в отпуск", tags = "Employee")
  @ApiResponses(
      value = {@ApiResponse(responseCode = "204", description = "Статус работника изменен")})
  @PutMapping("vacation/{employeeId}")
  public ResponseEntity<Void> vacation(@PathVariable Long employeeId) {
    employeeService.vacation(employeeId);
    return ResponseEntity.accepted().build();
  }

  @Operation(summary = "Уволить работника", tags = "Employee")
  @ApiResponses(
    value = {@ApiResponse(responseCode = "204", description = "Статус работника изменен")})
  @PutMapping("dismiss/{employeeId}")
  public ResponseEntity<Void> dismiss(@PathVariable Long employeeId) {
    employeeService.dismissEmployee(employeeId);
    return ResponseEntity.accepted().build();
  }

  @Operation(summary = "Вернуть работника из отпуска", tags = "Employee")
  @ApiResponses(
    value = {@ApiResponse(responseCode = "204", description = "Статус работника изменен")})
  @PutMapping("works/{employeeId}")
  public ResponseEntity<Void> work(@PathVariable Long employeeId) {
    employeeService.working(employeeId);
    return ResponseEntity.accepted().build();
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
                  schema = @Schema(implementation = Employee.class))
            })
      })
  @GetMapping("{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
  }
}
