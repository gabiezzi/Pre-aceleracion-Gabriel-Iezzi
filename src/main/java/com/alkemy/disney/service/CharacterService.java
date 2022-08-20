package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;

import java.util.List;

public interface CharacterService {

    public List<CharacterBasicDTO> findAll() throws Exception;

    public CharacterEntity findCharacterById(Long id) throws Exception ;

    public List<CharacterDTO> getCharactersByFilters(
            String name,
            Integer age,
            Double weight,
            List<Long> movies,
            String order
    );


}
