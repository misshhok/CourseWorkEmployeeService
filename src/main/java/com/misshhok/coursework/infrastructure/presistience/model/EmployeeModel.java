package com.misshhok.coursework.infrastructure.presistience.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean disabled;
  private String name;
  private String surname;
  private String PassportButch;
  private String PassportNumber;
  private LocalDate dateOfBirth;

  @ManyToMany
  @JoinTable(
      name = "position_employee",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "position_id"))
  private Set<PositionModel> positions = new LinkedHashSet<>();
}
