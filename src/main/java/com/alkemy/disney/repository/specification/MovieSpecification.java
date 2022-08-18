package com.alkemy.disney.repository.specification;


import com.alkemy.disney.dto.MoviesFiltersDTO;
import com.alkemy.disney.entity.CategoryEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getMoviesByFilters(MoviesFiltersDTO movieFiltersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(movieFiltersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFiltersDTO.getTitle().toLowerCase() + "%"
                        )
                );

            }
            if (StringUtils.hasLength(movieFiltersDTO.getDate())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(movieFiltersDTO.getDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("creationDate"), date)
                );
            }

            if (!CollectionUtils.isEmpty(movieFiltersDTO.getCategory())) {
                Join<CategoryEntity, MovieEntity> join = root.join("category", JoinType.INNER);
                Expression<String> categoriesId = join.get("id");
                predicates.add(categoriesId.in(movieFiltersDTO.getCategory()));

            }

            query.distinct(true);

            String orderByField = "title";
            query.orderBy(
                    movieFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
