package com.alkemy.disney.disney.repository.Specification;

import com.alkemy.disney.disney.dto.PersonajeFiltersDto;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {

    @PersistenceContext
    private EntityManager entityManager;

    public Specification<PersonajeEntity> getByFilters(PersonajeFiltersDto filtersDto){

        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDto.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%"+filtersDto.getName().toLowerCase()+"%"
                        )
                );
            }

            if(filtersDto.getEdad()!=null){
                predicates.add(criteriaBuilder.equal  (root.get("edad"), (Long)filtersDto.getEdad()) );
            }

            if(!CollectionUtils.isEmpty(filtersDto.getMovies())){
                Join<PeliculaEntity,PersonajeEntity> join = root.join("peliculas",JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDto.getMovies()));
            }

            query.distinct(true);


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

    }
}

