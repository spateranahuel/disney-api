package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity,Long>, JpaSpecificationExecutor<PersonajeEntity> {
    public List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);
}
