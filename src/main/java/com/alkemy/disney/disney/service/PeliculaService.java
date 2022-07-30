package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaBusquedaDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaSinPersonajesDTO;

import java.util.List;

public interface PeliculaService {

    public PeliculaDTO save(PeliculaDTO dto);

    PeliculaDTO getPelicula(Long id);

    void addPersonaje(Long idPelicula, Long idPersonaje);

    PeliculaDTO update(PeliculaSinPersonajesDTO dto, Long idPelicula);

    void removePersonaje(Long idPelicula, Long idCaracter);

    List<PeliculaBusquedaDTO> getByFilters(String name, Long genero, String order);

    void delete(Long idPelicula);
}
