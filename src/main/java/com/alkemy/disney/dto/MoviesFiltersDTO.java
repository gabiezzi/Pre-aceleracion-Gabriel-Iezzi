package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MoviesFiltersDTO {

    private String title;
    private String date;
    private List<Long> category;
    private String order;

    public MoviesFiltersDTO(String title, String date, List<Long> category, String order) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.order = order;

    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
