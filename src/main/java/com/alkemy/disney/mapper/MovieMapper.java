package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity(MovieDTO movieDTO, boolean loadCharacters) {

        MovieEntity movieEntity = new MovieEntity();
        if (movieDTO.getId() != null) {
            movieEntity.setId(movieDTO.getId());
        }
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setRating(movieDTO.getRaiting());
        movieEntity.setCreationDate(movieDTO.getCreationDate());
        movieEntity.setCategoryId(movieDTO.getCategoryId());

        if (loadCharacters) {

            List<CharacterDTO> characterDTOList = movieDTO.getCast();
            List<CharacterEntity> characterEntities = characterMapper.characterDTO2EntityList(characterDTOList, false);
            movieEntity.setCast(characterEntities);



        }
        return movieEntity;

    }

    public MovieDTO movieEntity2DTO(MovieEntity movieEntity, boolean loadCharacters) {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setId(movieEntity.getId());
        movieDTO.setImage(movieEntity.getImage());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setCreationDate(movieEntity.getCreationDate());
        movieDTO.setRaiting(movieEntity.getRating());
        movieDTO.setCategoryId(movieEntity.getCategoryId());
        if (loadCharacters) {

            List<CharacterEntity> characterEntities = movieEntity.getCast();
            List<CharacterDTO> characterDTOList = characterMapper.characterEntity2DTOList(characterEntities, false);
            movieDTO.setCast(characterDTOList);


        }
        return movieDTO;
    }

    public List<MovieEntity> movieDTO2EntityList(List<MovieDTO> movieDTOS, boolean loadCharacters) {

        List<MovieEntity> entities = new ArrayList<>();
        for (MovieDTO dto : movieDTOS
        ) {

            entities.add(movieDTO2Entity(dto, loadCharacters));

        }

        return entities;
    }

    public List<MovieDTO> movieEntities2DTOList(List<MovieEntity> movieEntities, boolean loadCharacters) {

        List<MovieDTO> movieDTOS = new ArrayList<>();

        for (MovieEntity entity : movieEntities
        ) {

            movieDTOS.add(movieEntity2DTO(entity, loadCharacters));

        }

        return movieDTOS;

    }

    public MovieBasicDTO movieEntity2MovieBasicDTO(MovieEntity movieEntity) {

        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();

        movieBasicDTO.setId(movieEntity.getId());
        movieBasicDTO.setTitle(movieEntity.getTitle());
        movieBasicDTO.setCreationDate(movieEntity.getCreationDate());

        return movieBasicDTO;
    }

    public MovieEntity movieBasicDTO2Entity(MovieBasicDTO movieBasicDTO) {
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setId(movieBasicDTO.getId());
        movieEntity.setTitle(movieEntity.getTitle());
        movieEntity.setCreationDate(movieBasicDTO.getCreationDate());

        return movieEntity;
    }

    public List<MovieEntity> movieBasicDTO2EntityList(List<MovieBasicDTO> movieBasicDTOS) {

        List<MovieEntity> movieEntities = new ArrayList<>();

        for (MovieBasicDTO movieBasicDTO : movieBasicDTOS
        ) {

            movieEntities.add(movieBasicDTO2Entity(movieBasicDTO));

        }

        return movieEntities;

    }

    public List<MovieBasicDTO> movieEntity2BasicDTOList(List<MovieEntity> movieEntities){

        List<MovieBasicDTO> movieBasicDTOS = new ArrayList<>();

        for (MovieEntity movieEntity: movieEntities
             ) {
            movieBasicDTOS.add(movieEntity2MovieBasicDTO(movieEntity));
        }

        return movieBasicDTOS;

    }

    public void updateAttributes(MovieDTO dto, MovieEntity entity){

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setRating(dto.getRaiting());
        entity.setCreationDate(dto.getCreationDate());
        entity.setCategoryId(dto.getCategoryId());

    }

}
