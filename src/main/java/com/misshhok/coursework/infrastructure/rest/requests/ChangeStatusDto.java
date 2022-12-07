package com.misshhok.coursework.infrastructure.rest.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Запрос на изменение статуса")
public class ChangeStatusDto {
  @Schema(description = "Идентификатор", example = "1")
  private Long id;
  @Schema(description = "Статус: \n\t true - готов к работе \n\tfalse - не готов к работе ", example = "true")
  private boolean status;
}
