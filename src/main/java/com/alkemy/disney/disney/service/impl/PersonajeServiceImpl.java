package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {



    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public PersonajeSinPeliculasDTO save(PersonajeSinPeliculasDTO personajeBasicDTO) {
        PersonajeEntity entity = personajeMapper.personajeSinPeliculasDTO2personajeEntity(personajeBasicDTO);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeSinPeliculasDTO respuesta = personajeMapper.personajeEntity2personajeSinPeliculasDTO(entitySaved);
        return respuesta;
    }

    @Override
    public PersonajeDTO getPersonaje(Long id) {
        Optional<PersonajeEntity> personajeEntity = personajeRepository.findById(id);

        if(!personajeEntity.isPresent()){
            //TODO arrojar excepcion de id invalido
        }
        PersonajeDTO personajeDTO = personajeMapper.personajeEntity2personajeDTO(personajeEntity.get());
        return personajeDTO;
    }

    @Override
    public void delete(Long id) {
        this.personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO update(PersonajeSinPeliculasDTO dto, Long id) {
        PersonajeEntity entity = personajeRepository.findById(id).get();

        entity.setEdad(dto.getEdad());
        entity.setImagen(dto.getImagen());
        entity.setHistoria(dto.getHistoria());
        entity.setPeso(dto.getPeso());
        entity.setNombre(dto.getNombre());

        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO personajeDTO = personajeMapper.personajeEntity2personajeDTO(entitySaved);
        return personajeDTO;
    }
}
