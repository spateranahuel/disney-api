package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaDTO;

public interface PeliculaService {

    public PeliculaDTO save(PeliculaDTO dto);

    PeliculaDTO getPelicula(Long id);

    void addPersonaje(Long idPelicula, Long idPersonaje);
}
