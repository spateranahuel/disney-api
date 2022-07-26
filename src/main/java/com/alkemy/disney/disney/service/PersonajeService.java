package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;



public interface PersonajeService {

    public PersonajeSinPeliculasDTO save(PersonajeSinPeliculasDTO personajeBasicDTO);

    PersonajeDTO getPersonaje(Long id);

    void delete(Long id);
}
