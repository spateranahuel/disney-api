package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaSinPersonajesDTO;
import com.alkemy.disney.disney.dto.PersonajeBusquedaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {


    public PersonajeEntity personajeSinPeliculasDTO2personajeEntity(PersonajeSinPeliculasDTO dto) {
        PersonajeEntity entity = new PersonajeEntity();
        entity.setEdad(dto.getEdad());
        entity.setImagen(dto.getImagen());
        entity.setHistoria(dto.getHistoria());
        entity.setPeso(dto.getPeso());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    public PersonajeSinPeliculasDTO personajeEntity2personajeSinPeliculasDTO(PersonajeEntity entity) {
        PersonajeSinPeliculasDTO dto = new PersonajeSinPeliculasDTO();
        dto.setId(entity.getId());
        dto.setEdad(entity.getEdad());
        dto.setImagen(entity.getImagen());
        dto.setHistoria(entity.getHistoria());
        dto.setPeso(entity.getPeso());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public PersonajeDTO personajeEntity2personajeDTO(PersonajeEntity entity) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setEdad(entity.getEdad());
        dto.setImagen(entity.getImagen());
        dto.setHistoria(entity.getHistoria());
        dto.setPeso(entity.getPeso());
        dto.setNombre(entity.getNombre());
        dto.setPeliculas(this.peliculaEntitySet2peliculaSinPersonajesDTOSet(entity.getPeliculas()));
        return dto;
    }



    private Set<PeliculaSinPersonajesDTO> peliculaEntitySet2peliculaSinPersonajesDTOSet(Set<PeliculaEntity> entities){
        Set<PeliculaSinPersonajesDTO> dtos = new HashSet<>();

        for (PeliculaEntity entity:entities) {
            PeliculaSinPersonajesDTO dto = new PeliculaSinPersonajesDTO();
            dto.setTitulo(entity.getTitulo());
            dto.setGeneroNombre(entity.getGenero().getNombre());
            dto.setCalificacion(entity.getCalificacion());
            dto.setFechaCreacion(entity.getFechaCreacion());
            dto.setImagen(entity.getImagen());
            dto.setId(entity.getId());
            dtos.add(dto);
        }
        return dtos;
    }





    public List<PersonajeBusquedaDTO> personajeEntityList2personajeBusquedaDTOList(List<PersonajeEntity> entities) {

        List<PersonajeBusquedaDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity: entities) {
            PersonajeBusquedaDTO dto = new PersonajeBusquedaDTO();
            dto.setImagen(entity.getImagen());
            dto.setNombre(entity.getNombre());
            dtos.add(dto);
        }
        return  dtos;

    }
}
