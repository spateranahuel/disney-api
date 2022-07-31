package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PersonajeBusquedaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeFiltersDto;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.Specification.PersonajeSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {



    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeSpecification personajeSpecification;
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaService peliculaService;

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
    public void delete(Long idPersonaje) {
        PersonajeEntity personajeEntity = personajeRepository.findById(idPersonaje).get();
        Set<PeliculaEntity> peliculaEntitySet = personajeEntity.getPeliculas();
        for (PeliculaEntity peliculaEntity:peliculaEntitySet) {
            this.peliculaService.removePersonaje(peliculaEntity.getId(),idPersonaje);
        }
        personajeEntity.getPeliculas().clear();
        this.personajeRepository.save(personajeEntity);
        this.personajeRepository.deleteById(idPersonaje);
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

    @Override
    public List<PersonajeBusquedaDTO> getByFilters(String name, Long age, Set<Long> movies) {
        PersonajeFiltersDto filtersDto = new PersonajeFiltersDto(name,age,movies);
        List<PersonajeEntity> entities = personajeRepository.findAll(personajeSpecification.getByFilters(filtersDto));
        List<PersonajeBusquedaDTO> dtos = personajeMapper.personajeEntityList2personajeBusquedaDTOList(entities);
        return dtos;
    }
}
