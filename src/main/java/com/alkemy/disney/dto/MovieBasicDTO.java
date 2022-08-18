package com.alkemy.disney.dto;


import com.alkemy.disney.entity.MovieEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieBasicDTO {

    private Long id;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;

}
