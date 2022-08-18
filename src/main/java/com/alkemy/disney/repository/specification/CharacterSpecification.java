package com.alkemy.disney.repository.specification;


import com.alkemy.disney.dto.CharactersFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
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
public class CharacterSpecification {

    public Specification<CharacterEntity> getCharactersByFilters(CharactersFiltersDTO charactersFiltersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(charactersFiltersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + charactersFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (StringUtils.hasLength(charactersFiltersDTO.getAge().toString())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("age")), charactersFiltersDTO.getAge().toString()
                        )
                );

            }
            if (!CollectionUtils.isEmpty(charactersFiltersDTO.getMovies())) {
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> movies = join.get("id");
                predicates.add(movies.in(charactersFiltersDTO.getMovies()));

            }

            query.distinct(true);

            String orderByField = "name";

            query.orderBy(
                    charactersFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
