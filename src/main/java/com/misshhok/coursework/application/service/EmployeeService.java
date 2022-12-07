package com.misshhok.coursework.application.service;

import com.misshhok.coursework.infrastructure.presistience.model.EmployeeModel;
import com.misshhok.coursework.infrastructure.presistience.model.PositionModel;
import com.misshhok.coursework.infrastructure.rest.requests.ChangeStatusDto;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
import com.misshhok.coursework.infrastructure.rest.requests.CreatePositionDto;
import java.util.List;

public interface EmployeeService {

  void changeEmployeeStatus(ChangeStatusDto changeStatusDto);

  List<EmployeeModel> getAllEmployers();

  EmployeeModel createEmployee(CreateEmployeeDto createEmployeeDto);

  PositionModel createPosition(CreatePositionDto createPositionDto);

  void deletePosition(Long id);

  void deleteEmployee(Long id);

  List<PositionModel> getAllPositions();

  PositionModel getPositionById(Long id);

  EmployeeModel getEmployeeById(Long id);
}
