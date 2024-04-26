package com.misshhok.coursework.infrastructure.rest.mapper;

import com.misshhok.coursework.infrastructure.persistance.model.Employee;
import com.misshhok.coursework.infrastructure.persistance.model.Position;
import com.misshhok.coursework.infrastructure.persistance.repository.PositionRepository;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
import com.misshhok.coursework.infrastructure.rest.requests.PositionDto;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeMapper {
  private final PositionRepository positionRepository;

  public Employee mapDtoToModel(CreateEmployeeDto createEmployeeDto) {
    Employee employee = new Employee();
    employee.setName(createEmployeeDto.getName());
    employee.setSurname(createEmployeeDto.getSurname());
    employee.setDateOfBirth(LocalDate.parse(createEmployeeDto.getDateOfBirth()));
    employee.setPassportSeriesNumber(createEmployeeDto.getPassportSeriesNumber());
    Set<Position> positions = new HashSet<>();
    for (PositionDto positionDto : createEmployeeDto.getPositions()) {
      Position position = positionRepository.findById(positionDto.getId()).get();
      positions.add(position);
    }
    employee.setPositions(positions);
    return employee;
  }
}
