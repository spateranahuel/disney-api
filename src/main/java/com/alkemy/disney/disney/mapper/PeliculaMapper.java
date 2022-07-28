package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaBusquedaDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PeliculaMapper {



    //imagen titulo fechaCreacion calificacion generoId

    @Autowired
    GeneroRepository generoRepository;

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto){
        PeliculaEntity entity = new PeliculaEntity();

        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setCalificacion(dto.getCalificacion());
        entity.setIdGenero(dto.getGeneroId());
        entity.setPersonajes(this.personajeSinPeliculasDTOSet2personajeEntytySet(dto.getPersonajes()));
        return  entity;
    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity){

        PeliculaDTO dto = new PeliculaDTO();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGeneroId(entity.getIdGenero());
        dto.setGeneroNombre(generoRepository.findById(entity.getIdGenero()).get().getNombre());
        dto.setPersonajes(this.personajeEntytySet2personajeSinPeliculasDTOSet(entity.getPersonajes()));
        return dto;
    }


    public Set<PersonajeSinPeliculasDTO> personajeEntytySet2personajeSinPeliculasDTOSet(Set<PersonajeEntity> entities){
        Set<PersonajeSinPeliculasDTO> dtos = new HashSet<>();


        for(PersonajeEntity entity : entities){
            PersonajeSinPeliculasDTO dto = new PersonajeSinPeliculasDTO();
            dto.setNombre(entity.getNombre());
            dto.setEdad(entity.getEdad());
            dto.setHistoria(entity.getHistoria());
            dto.setPeso(entity.getPeso());
            dto.setImagen(entity.getImagen());
            dto.setId(entity.getId());
            dtos.add(dto);
        }
        return dtos;
    }


    public Set<PersonajeEntity> personajeSinPeliculasDTOSet2personajeEntytySet(Set<PersonajeSinPeliculasDTO> dtos){

        Set<PersonajeEntity> entities = new HashSet<>();

        for (PersonajeSinPeliculasDTO dto : dtos){
            PersonajeEntity entity = new PersonajeEntity();
            entity.setNombre(dto.getNombre());
            entity.setEdad(dto.getEdad());
            entity.setPeso(dto.getPeso());
            entity.setImagen(dto.getImagen());
            entity.setHistoria(dto.getHistoria());
            entities.add(entity);
        }
        return entities;
    }


    public List<PeliculaBusquedaDTO> peliculaEntityList2peliculaBusquedaDTOList(List<PeliculaEntity> entities) {
        List<PeliculaBusquedaDTO> dtos = new ArrayList<>();

        for (PeliculaEntity entity:entities) {
            PeliculaBusquedaDTO dto = new PeliculaBusquedaDTO();
            dto.setImagen(entity.getImagen());
            dto.setFechaCreacion(entity.getFechaCreacion());
            dto.setTitulo(entity.getTitulo());
            dtos.add(dto);
        }
        return dtos;

    }
}
