package com.misshhok.coursework.infrastructure.rest.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Идентификатор существующей должности")
public class PositionDto {
  @Schema(description = "Идентификатор", example = "1")
  private Long id;
}
