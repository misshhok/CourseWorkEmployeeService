package com.misshhok.coursework.infrastructure.presistience.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class PositionEmployeeId implements Serializable {
  private Long position_id;
  private Long employee_id;
}
