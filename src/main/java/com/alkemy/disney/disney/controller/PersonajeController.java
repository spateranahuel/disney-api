package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.PersonajeBusquedaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;
import com.alkemy.disney.disney.service.PersonajeService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("personajes")
public class PersonajeController {


    @Autowired
    private PersonajeService personajeService;

    @PostMapping
    public ResponseEntity<PersonajeSinPeliculasDTO> save(@RequestBody PersonajeSinPeliculasDTO personajeBasicDTO){
        PersonajeSinPeliculasDTO personajeGuardado = personajeService.save(personajeBasicDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PersonajeDTO> get(@PathVariable Long id){
        PersonajeDTO personajeEncontrado = personajeService.getPersonaje(id);
        return  ResponseEntity.status(HttpStatus.FOUND).body(personajeEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> update(@RequestBody PersonajeSinPeliculasDTO dto, @PathVariable Long id){
        PersonajeDTO personajeDTO = personajeService.update(dto,id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeDTO);
    }


    @GetMapping
    public ResponseEntity<List<PersonajeBusquedaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long age,
            @RequestParam(required = false) Set<Long> movies
    ){
        List<PersonajeBusquedaDTO> personajes = personajeService.getByFilters(name,age,movies);
        return ResponseEntity.ok(personajes);
    }


}
