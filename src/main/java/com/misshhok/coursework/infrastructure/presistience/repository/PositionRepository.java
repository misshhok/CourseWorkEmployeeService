package com.misshhok.coursework.infrastructure.presistience.repository;

import com.misshhok.coursework.infrastructure.presistience.model.PositionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionModel, Long> {}
