package com.misshhok.coursework.infrastructure.rest.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@Schema(description = "Новый работник")
public class CreateEmployeeDto {
  @Schema(description = "Статус: \n\t true - готов к работе \n\tfalse - не готов к работе ", example = "true")
  private boolean status;
  @Schema(description = "Занимаемые должности")
  private Set<PositionDto> positions;
  @Schema(description = "Имя", example = "Иван")
  private String name;
  @Schema(description = "Фамилия", example = "Иванович")
  private String surname;
  @Schema(description = "Серия паспорта", example = "1999")
  private String passportButch;
  @Schema(description = "Номер паспорта", example = "723802")
  private String passportNumber;
  @Schema(description = "Дата рождения", example = "2000-11-16")
  private String dateOfBirth;
}
