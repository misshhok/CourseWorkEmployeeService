package com.misshhok.coursework.application.service.impl;

import com.misshhok.coursework.application.service.EmployeeService;
import com.misshhok.coursework.infrastructure.persistance.model.Employee;
import com.misshhok.coursework.infrastructure.persistance.model.Position;
import com.misshhok.coursework.infrastructure.persistance.model.enums.EmployeeState;
import com.misshhok.coursework.infrastructure.persistance.repository.EmployeeRepository;
import com.misshhok.coursework.infrastructure.persistance.repository.PositionRepository;
import com.misshhok.coursework.infrastructure.rest.mapper.EmployeeMapper;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
import com.misshhok.coursework.infrastructure.rest.requests.CreatePositionDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;
  private final EmployeeMapper employeeMapper;

  @Override
  public void dismissEmployee(final Long employeeId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Работника с таким ID нет в базе данных"));

    employee.setState(EmployeeState.DISMISSED);
    employeeRepository.save(employee);
  }

  @Override
  public void vacation(Long employeeId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Работника с таким ID нет в базе данных"));

    employee.setState(EmployeeState.ON_VACATION);
    employeeRepository.save(employee);
  }

  @Override
  public void working(Long employeeId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Работника с таким ID нет в базе данных"));

    employee.setState(EmployeeState.WORKING);
    employeeRepository.save(employee);
  }

  @Override
  public List<Employee> getAllEmployers() {
    return employeeRepository.findAll();
  }

  @Override
  @Transactional
  public Employee createEmployee(CreateEmployeeDto createEmployeeDto) {
    Employee employee = employeeMapper.mapDtoToModel(createEmployeeDto);
    employeeRepository.save(employee);
    return employee;
  }

  @Override
  public Position createPosition(CreatePositionDto createPositionDto) {
    Position position = new Position();
    position.setTitle(createPositionDto.getTitle());
    positionRepository.save(position);
    return position;
  }

  @Override
  public void deletePosition(Long id) {
    positionRepository.deleteById(id);
  }

  @Override
  public List<Position> getAllPositions() {
    return positionRepository.findAll();
  }

  @Override
  public Position getPositionById(final Long id) {
    return positionRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Должности с таким ID не существует"));
  }

  @Override
  public Employee getEmployeeById(final Long id) {
    return employeeRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Сотрудника с таким ID не существует"));
  }
}
