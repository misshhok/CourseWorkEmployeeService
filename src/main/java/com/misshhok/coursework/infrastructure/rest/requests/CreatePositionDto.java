package com.misshhok.coursework.infrastructure.rest.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Запрос на создание новой должности")
public class CreatePositionDto {
  @Schema(description = "Название должности", example = "Профессор")
  private String title;
  @Schema(description = "Описание должности", example = "университетский преподаватель высокого уровня компетентности и одновременно учёный, являющийся экспертом в определённой области науки или искусства.")
  private String description;
}
