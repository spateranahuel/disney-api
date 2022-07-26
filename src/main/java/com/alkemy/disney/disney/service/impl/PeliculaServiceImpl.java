package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {


    @Autowired
    PeliculaMapper peliculaMapper;
    @Autowired
    PeliculaRepository peliculaRepository;

    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        System.out.println(dto.toString());
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        System.out.println("\n\n"+entity.toString());
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

}



