package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.*;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.Specification.PeliculaSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {


    @Autowired
    PeliculaMapper peliculaMapper;
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    PersonajeRepository personajeRepository;

    @Autowired
    PeliculaSpecification peliculaSpecification;

    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(entitySaved);
        return peliculaDTO;
    }

    @Override
    public PeliculaDTO getPelicula(Long id) {
        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);

        if(!peliculaEntity.isPresent()){
            //TODO ARROJAR EXCEPCION DE QUE NO EXISTE PELICULA CON EL ID INGRESADO
        }
        PeliculaDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(peliculaEntity.get());
        return peliculaDTO;
    }

    @Override
    public void addPersonaje(Long idPelicula, Long idPersonaje) {
        Optional<PersonajeEntity> personajeEntityOptional = personajeRepository.findById(idPersonaje);
        Optional<PeliculaEntity> peliculaEntityOptional = peliculaRepository.findById(idPelicula);

        PersonajeEntity personajeEntity = personajeEntityOptional.get();
        PeliculaEntity peliculaEntity = peliculaEntityOptional.get();

        peliculaEntity.getPersonajes().add(personajeEntity);
        peliculaRepository.save(peliculaEntity);

    }



    @Override
    public void removePersonaje(Long idPelicula, Long idPersonaje){
        Optional<PersonajeEntity> personajeEntityOptional = personajeRepository.findById(idPersonaje);
        Optional<PeliculaEntity> peliculaEntityOptional = peliculaRepository.findById(idPelicula);

        PersonajeEntity personajeEntity = personajeEntityOptional.get();
        PeliculaEntity peliculaEntity = peliculaEntityOptional.get();

        peliculaEntity.getPersonajes().remove(personajeEntity);
        peliculaRepository.save(peliculaEntity);

    }

    @Override
    public List<PeliculaBusquedaDTO> getByFilters(String name, Long idGenero, String order) {
        PeliculaFiltersDTO filtersDto = new PeliculaFiltersDTO(name,idGenero,order);
        List<PeliculaEntity> entities = peliculaRepository.findAll(peliculaSpecification.getByFilters(filtersDto));
        List<PeliculaBusquedaDTO> dtos = peliculaMapper.peliculaEntityList2peliculaBusquedaDTOList(entities);
        return dtos;
    }

    @Override
    public void delete(Long idPelicula) {
        this.peliculaRepository.deleteById(idPelicula);
    }


    @Override
    public PeliculaDTO update(PeliculaSinPersonajesDTO dto, Long idPelicula) {
        PeliculaEntity entity = peliculaRepository.findById(idPelicula).get();
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setCalificacion(dto.getCalificacion());
        entity.setIdGenero(dto.getGeneroId());
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(entitySaved);
        return peliculaDTO;
    }



}



