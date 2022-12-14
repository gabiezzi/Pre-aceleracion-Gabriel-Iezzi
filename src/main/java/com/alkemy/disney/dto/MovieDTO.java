package com.alkemy.disney.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private Double raiting;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;

    private Long categoryId;

    private List<CharacterDTO> cast;

}
