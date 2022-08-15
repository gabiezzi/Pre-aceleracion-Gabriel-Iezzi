package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MoviesFiltersDTO {

    private String name;
    private String date;
    private List<Long> categories;
    private String order;

    public MoviesFiltersDTO(String name , String date , List<Long> categories , String order) {
        this.name = name;
        this.date = date;
        this.categories = categories ;
        this.order = order;

    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC")==0;}
    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC")==0;}

}
