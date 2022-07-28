package com.alkemy.disney.disney.repository.Specification;

import com.alkemy.disney.disney.dto.PeliculaFiltersDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSpecification {
    public Specification<PeliculaEntity> getByFilters(PeliculaFiltersDTO filtersDto) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            //Filtra titulo
            if(StringUtils.hasLength(filtersDto.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%"+filtersDto.getName().toLowerCase()+"%"
                        )
                );
            }

            //Filtra idGenero
            if(filtersDto.getIdGenero()!=null){
                predicates.add(criteriaBuilder.equal  (root.get("idGenero"), (Long)filtersDto.getIdGenero()) );
            }


            //filtra ordenado (ascendente o descendente)
            String orderByField = "fechaCreacion";
            query.orderBy(
                filtersDto.isDESC() ? criteriaBuilder.desc(root.get(orderByField)) : criteriaBuilder.asc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
