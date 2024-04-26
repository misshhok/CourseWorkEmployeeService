package com.misshhok.coursework.infrastructure.persistance.model;

import com.misshhok.coursework.infrastructure.persistance.model.enums.EmployeeState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private EmployeeState state = EmployeeState.WORKING;

  private String name;
  private String surname;
  private String PassportSeriesNumber;
  private LocalDate dateOfBirth;

  @ManyToMany
  @JoinTable(
      name = "position_employee",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "position_id"))
  private Set<Position> positions = new LinkedHashSet<>();
}
