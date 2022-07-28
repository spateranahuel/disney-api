package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PersonajeDTO{

    private Long id;
    private String imagen;
    private String nombre;
    private Long edad;
    private double peso;
    private String historia;
    Set<PeliculaSinPersonajesDTO>  peliculas  = new HashSet<>();
}
