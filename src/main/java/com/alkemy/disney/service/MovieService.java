package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    public List<MovieBasicDTO> findAll() throws Exception;

    public MovieDTO addCharacter2Movie(Long idCharacter, Long id) throws Exception;
    public MovieDTO removeCharacter2Movie(Long idCharacter, Long id) throws Exception;

    public MovieEntity findMovieById(Long id) throws Exception ;

    List<MovieDTO> getMoviesByFilters(String name, String date, List<Long> categories, String order);
}
