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
}
