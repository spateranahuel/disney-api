package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<PeliculaEntity,Long> {
}
