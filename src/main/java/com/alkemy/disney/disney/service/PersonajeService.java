package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeBusquedaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;

import java.util.List;
import java.util.Set;


public interface PersonajeService {

    public PersonajeSinPeliculasDTO save(PersonajeSinPeliculasDTO personajeBasicDTO);

    PersonajeDTO getPersonaje(Long id);

    void delete(Long id);

    PersonajeDTO update(PersonajeSinPeliculasDTO dto, Long id);

    List<PersonajeBusquedaDTO> getByFilters(String name, Long age, Set<Long> movies);
}
