package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

public class PeliculaDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private Byte calificacion;
    private Long generoId;
    private String generoNombre;
    private Set<PersonajeSinPeliculasDTO> personajes = new HashSet<>();



}
