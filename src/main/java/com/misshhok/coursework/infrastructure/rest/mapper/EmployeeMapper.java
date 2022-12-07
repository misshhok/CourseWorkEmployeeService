package com.misshhok.coursework.infrastructure.rest.mapper;

import com.misshhok.coursework.infrastructure.presistience.model.EmployeeModel;
import com.misshhok.coursework.infrastructure.presistience.model.PositionModel;
import com.misshhok.coursework.infrastructure.presistience.repository.PositionRepository;
import com.misshhok.coursework.infrastructure.rest.requests.CreateEmployeeDto;
import com.misshhok.coursework.infrastructure.rest.requests.PositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeMapper {
  private final PositionRepository positionRepository;

  public EmployeeModel mapDtoToModel(CreateEmployeeDto createEmployeeDto) {
    EmployeeModel employeeModel = new EmployeeModel();
    employeeModel.setDisabled(createEmployeeDto.isStatus());
    employeeModel.setName(createEmployeeDto.getName());
    employeeModel.setSurname(createEmployeeDto.getSurname());
    employeeModel.setDateOfBirth(LocalDate.parse(createEmployeeDto.getDateOfBirth()));
    employeeModel.setPassportButch(createEmployeeDto.getPassportButch());
    employeeModel.setPassportNumber(createEmployeeDto.getPassportNumber());
    Set<PositionModel> positionModels = new HashSet<>();
    for (PositionDto positionDto : createEmployeeDto.getPositions()) {
      PositionModel positionModel = positionRepository.findById(positionDto.getId()).get();
      positionModels.add(positionModel);
    }
    employeeModel.setPositions(positionModels);
    return employeeModel;
  }
}
