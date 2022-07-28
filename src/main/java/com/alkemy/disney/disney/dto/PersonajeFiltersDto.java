package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDto {
    private String name;
    private Long edad;
    private Set<Long> movies;


    public PersonajeFiltersDto(String name, Long edad, Set<Long> movies) {
        this.name = name;
        this.edad = edad;
        this.movies = movies;
    }



}
