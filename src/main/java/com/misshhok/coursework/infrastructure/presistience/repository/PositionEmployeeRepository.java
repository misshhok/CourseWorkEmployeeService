package com.misshhok.coursework.infrastructure.presistience.repository;

import com.misshhok.coursework.infrastructure.presistience.model.PositionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionEmployeeRepository extends JpaRepository<PositionEmployee, Long> {}
