package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity,Long> {
}
