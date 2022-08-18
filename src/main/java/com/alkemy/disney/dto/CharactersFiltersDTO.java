package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CharactersFiltersDTO {

    private String name;
    private Integer age;
    private List<Long> movies;
    private String order;

    public CharactersFiltersDTO(String name, Integer age, List<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
