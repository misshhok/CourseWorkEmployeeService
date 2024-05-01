package com.misshhok.coursework.infrastructure.persistance.repository;

import com.misshhok.coursework.infrastructure.persistance.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {}


