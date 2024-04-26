package com.misshhok.coursework.infrastructure.persistance.repository;

import com.misshhok.coursework.infrastructure.persistance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
