package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.ReactiveTransaction;

@Getter
@Setter
public class PeliculaFiltersDTO {

    private String name;
    private Long idGenero;
    private String order;


    public PeliculaFiltersDTO(String name, Long idGenero, String order) {
        this.name=name;
        this.idGenero=idGenero;
        this.order=order;
    }


    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC")==0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
