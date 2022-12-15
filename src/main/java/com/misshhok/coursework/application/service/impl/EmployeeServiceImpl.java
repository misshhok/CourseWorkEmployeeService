package com.misshhok.coursework.application.service.impl;

import com.misshhok.coursework.application.service.EmployeeService;
import com.misshhok.coursework.infrastructure.presistience.model.EmployeeModel;
import com.misshhok.coursework.infrastructure.presistience.model.PositionModel;
import com.misshhok.coursework.infrastructure.presistience.repository.EmployeeRepository;
import com.misshhok.coursework.infrastructure.presistience.repository.PositionRepository;
import com.misshhok.coursework.infrastructure.rest.mapper.EmployeeMapper;
import com.misshhok.coursework.infrastructure.rest.requests.ChangeStatusDto;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
import com.misshhok.coursework.infrastructure.rest.requests.CreatePositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;
  private final EmployeeMapper employeeMapper;

  @Override
  public void changeEmployeeStatus(final ChangeStatusDto changeStatusDto) {
    EmployeeModel employeeModel =
        employeeRepository
            .findById(changeStatusDto.getId())
            .orElseThrow(() -> new RuntimeException("Работника с таким ID нет в базе данных"));
    employeeModel.setDisabled(changeStatusDto.isStatus());
    employeeRepository.save(employeeModel);
  }

  @Override
  public List<EmployeeModel> getAllEmployers() {
    return employeeRepository.findAll();
  }

  @Override
  @Transactional
  public EmployeeModel createEmployee(CreateEmployeeDto createEmployeeDto) {
    EmployeeModel employeeModel = employeeMapper.mapDtoToModel(createEmployeeDto);
    employeeRepository.save(employeeModel);
    return employeeModel;
  }

  @Override
  public PositionModel createPosition(CreatePositionDto createPositionDto) {
    PositionModel positionModel = new PositionModel();
    positionModel.setTitle(createPositionDto.getTitle());
    positionModel.setDescription(createPositionDto.getDescription());
    positionRepository.save(positionModel);
    return positionModel;
  }

  @Override
  public void deletePosition(Long id) {
    positionRepository.deleteById(id);
  }

  @Override
  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }

  @Override
  public List<PositionModel> getAllPositions() {
    return positionRepository.findAll();
  }

  @Override
  public PositionModel getPositionById(final Long id) {
    return positionRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Должности с таким ID не существует"));
  }

  @Override
  public EmployeeModel getEmployeeById(final Long id) {
    return employeeRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Сотрудника с таким ID не существует"));
  }
}
