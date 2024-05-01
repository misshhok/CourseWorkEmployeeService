package com.misshhok.coursework.infrastructure.rest.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Новый работник")
public class CreateEmployeeDto {
  @Schema(description = "Занимаемые должности")
  private Set<PositionDto> positions;

  @Schema(description = "Имя", example = "Иван")
  private String name;

  @Schema(description = "Фамилия", example = "Иванович")
  private String surname;

  @Schema(description = "Номер паспорта", example = "723802")
  private String passportSeriesNumber;

  @Schema(description = "Дата рождения", example = "2000-11-16")
  private String dateOfBirth;
}

