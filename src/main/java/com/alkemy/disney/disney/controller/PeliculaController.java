package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PeliculaBusquedaDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaSinPersonajesDTO;
import com.alkemy.disney.disney.dto.PersonajeBusquedaDTO;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula){
        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }


    @GetMapping("{id}")
    public  ResponseEntity<PeliculaDTO> get(@PathVariable Long id){
        PeliculaDTO peliculaEncontrada = peliculaService.getPelicula(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(peliculaEncontrada);
    }

    @PutMapping("/{idPelicula}/characters/{idCaracter}")
    public ResponseEntity<Void> addPersonaje(@PathVariable Long idPelicula, @PathVariable Long idCaracter){
        this.peliculaService.addPersonaje(idPelicula,idCaracter);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



    @DeleteMapping("/{idPelicula}/characters/{idCaracter}")
    public  ResponseEntity<Void> deletePersonaje(@PathVariable Long idPelicula, @PathVariable Long idCaracter){
        this.peliculaService.removePersonaje(idPelicula,idCaracter);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }




    @PutMapping("/{idPelicula}")
    public ResponseEntity<PeliculaDTO> update(@RequestBody PeliculaSinPersonajesDTO dto, @PathVariable Long idPelicula){
        PeliculaDTO peliculaDTO = peliculaService.update(dto,idPelicula);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaDTO);
    }


    @GetMapping
    public ResponseEntity<List<PeliculaBusquedaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genero,
            @RequestParam(required = false, defaultValue = "DESC") String order
    ){
        List<PeliculaBusquedaDTO> peliculas = peliculaService.getByFilters(name,genero,order);
        return ResponseEntity.ok(peliculas);
    }



}
