package com.misshhok.coursework.infrastructure.presistience.repository;

import com.misshhok.coursework.infrastructure.presistience.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {}
