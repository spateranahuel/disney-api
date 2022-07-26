package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeSinPeliculasDTO;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
