package com.misshhok.coursework.application.service;

import com.misshhok.coursework.infrastructure.persistance.model.Employee;
import com.misshhok.coursework.infrastructure.persistance.model.Position;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
import com.misshhok.coursework.infrastructure.rest.requests.CreatePositionDto;
import java.util.List;

public interface EmployeeService {

  void dismissEmployee(final Long employeeId);

  void vacation(final Long employeeId);

  void working(final Long employeeId);

  List<Employee> getAllEmployers();

  Employee createEmployee(CreateEmployeeDto createEmployeeDto);

  Position createPosition(CreatePositionDto createPositionDto);

  void deletePosition(Long id);

  List<Position> getAllPositions();

  Position getPositionById(Long id);

  Employee getEmployeeById(Long id);
}
