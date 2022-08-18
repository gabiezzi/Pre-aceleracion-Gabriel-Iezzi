package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired
    MovieMapper movieMapper;

    public CharacterDTO characterEntity2DTO(CharacterEntity characterEntity, boolean loadMovies) {


        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(characterEntity.getId());
        characterDTO.setImage(characterEntity.getImage());
        characterDTO.setName(characterEntity.getName());
        characterDTO.setAge(characterEntity.getAge());
        characterDTO.setWeight(characterEntity.getWeight());
        characterDTO.setStory(characterEntity.getStory());
        if (loadMovies) {

            List<MovieEntity> movieEntities = characterEntity.getMovies();
            List<MovieDTO> movieDTOS = movieMapper.movieEntities2DTOList(movieEntities, false);
            characterDTO.setMovies(movieDTOS);

        }
        return characterDTO;
    }

    public CharacterEntity characterDTO2Entity(CharacterDTO characterDTO, boolean loadMovies) {

        CharacterEntity characterEntity = new CharacterEntity();
        if (characterDTO.getId()!=null){
            characterEntity.setId(characterDTO.getId());
        }

        characterEntity.setName(characterDTO.getName());
        characterEntity.setImage(characterDTO.getImage());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setStory(characterDTO.getStory());
        if (loadMovies) {

            List<MovieDTO> movieDTOS = characterDTO.getMovies();
            List<MovieEntity> movieEntities = movieMapper.movieDTO2EntityList(movieDTOS, false);
            characterEntity.setMovies(movieEntities);

        }
        return characterEntity;
    }

    public List<CharacterDTO> characterEntity2DTOList(List<CharacterEntity> entities, boolean loadMovies) {

        List<CharacterDTO> characterDTOS = new ArrayList<>();
        for (CharacterEntity entity : entities
        ) {

            characterDTOS.add(characterEntity2DTO(entity, loadMovies));

        }

        return characterDTOS;

    }

    public List<CharacterEntity> characterDTO2EntityList(List<CharacterDTO> characterDTOS, boolean loadMovies) {

        List<CharacterEntity> characterEntities = new ArrayList<>();

        for (CharacterDTO dto : characterDTOS
        ) {

            characterEntities.add(characterDTO2Entity(dto, loadMovies));

        }

        return characterEntities;

    }

    public CharacterEntity characterBasicDTO2Entity(CharacterBasicDTO characterBasicDTO) {

        CharacterEntity characterEntity = new CharacterEntity();
        if (characterBasicDTO.getId() != null) {
            characterEntity.setId(characterBasicDTO.getId());
        }

        characterEntity.setImage(characterEntity.getImage());
        characterEntity.setName(characterBasicDTO.getName());

        return characterEntity;

    }

    public List<CharacterEntity> characterBasicDTO2EntityList(List<CharacterBasicDTO> characterBasicDTOS) {

        List<CharacterEntity> characterEntities = new ArrayList<>();

        for (CharacterBasicDTO characterBasicDTO : characterBasicDTOS
        ) {

            characterEntities.add(characterBasicDTO2Entity(characterBasicDTO));

        }

        return characterEntities;

    }

    public CharacterBasicDTO characterBasicDTO2Entity(CharacterEntity characterEntity) {

        CharacterBasicDTO characterBasicDTO = new CharacterBasicDTO();

        characterBasicDTO.setId(characterEntity.getId());
        characterBasicDTO.setName(characterEntity.getName());
        characterBasicDTO.setImage(characterEntity.getImage());


        return characterBasicDTO;
    }

    public List<CharacterBasicDTO> characterEntity2BasicDTOList(List<CharacterEntity> characterEntities) {

        List<CharacterBasicDTO> characterBasicDTOS = new ArrayList<>();

        for (CharacterEntity characterEntity : characterEntities
        ) {

            characterBasicDTOS.add(characterBasicDTO2Entity(characterEntity));

        }

        return characterBasicDTOS;
    }

}
