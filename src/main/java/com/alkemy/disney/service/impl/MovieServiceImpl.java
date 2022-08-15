package com.alkemy.disney.service.impl;


import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MoviesFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.MovieRepository;

import com.alkemy.disney.repository.specification.MovieSpecification;
import com.alkemy.disney.service.BaseService;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService, BaseService<MovieDTO> {

    private MovieRepository movieRepository;

    private MovieMapper movieMapper;

    private CharacterServiceImpl characterService;

    private MovieSpecification movieSpecification;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, CharacterServiceImpl characterService, MovieSpecification movieSpecification) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
        this.movieSpecification = movieSpecification;
    }

    @Override
    @Transactional
    public List<MovieBasicDTO> findAll() throws Exception {

        try {
            List<MovieEntity> entities = movieRepository.findAll();
            List<MovieBasicDTO> basicDtos = movieMapper.movieEntity2BasicDTOList(entities);
            //List<MovieDTO> dtos = entities.stream().map(entity -> modelMapper.map(entity, MovieDTO.class)).collect(Collectors.toList());
            return basicDtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public MovieDTO addCharacter2Movie(Long idCharacter, Long idMovie) throws Exception {


        MovieEntity movieEntity = findMovieById(idMovie);

        movieEntity.addCharacter(characterService.findCharacterById(idCharacter));

        return movieMapper.movieEntity2DTO(movieRepository.save(movieEntity), true);


    }

    @Override
    public MovieDTO removeCharacter2Movie(Long idCharacter, Long idMovie) throws Exception {

        MovieEntity movieEntity = findMovieById(idMovie);

        movieEntity.removeCharacter(characterService.findCharacterById(idCharacter));

        return movieMapper.movieEntity2DTO(movieRepository.save(movieEntity), true);

    }


    @Transactional
    public MovieDTO findById(Long id) throws Exception {

        MovieEntity movieEntity = findMovieById(id);

        return movieMapper.movieEntity2DTO(movieEntity, true);
    }

    @Transactional
    public MovieEntity findMovieById(Long id) throws Exception {

        Optional<MovieEntity> optional = movieRepository.findById(id);

        if (optional.isEmpty()) {

            throw new Exception("Movie not found");

            //TODO: throw new ParamNotFound("Movie id is not valid");

        }
        return optional.get();

    }

    @Override
    public List<MovieDTO> getMoviesByFilters(String name, String date, List<Long> categories, String order) {

        MoviesFiltersDTO movieFiltersDTO = new MoviesFiltersDTO(name, date, categories, order);

        List<MovieEntity> movieEntities = this.movieRepository.findAll(this.movieSpecification.getMoviesByFilters(movieFiltersDTO));

        return movieMapper.movieEntities2DTOList(movieEntities, true);

    }

    @Override
    @Transactional
    public MovieDTO save(MovieDTO dto) throws Exception {

        try {

            MovieEntity entity = movieMapper.movieDTO2Entity(dto, true);
            //MovieEntity entity = modelMapper.map(dto, MovieEntity.class);

            MovieEntity entitySaved = movieRepository.save(entity);

            return movieMapper.movieEntity2DTO(entitySaved, true);

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public MovieDTO update(MovieDTO dto) throws Exception {

        Optional<MovieEntity> entityOptional = movieRepository.findById(dto.getId());

        if (entityOptional.isPresent()) {

            MovieEntity movieEntity = movieMapper.movieDTO2Entity(dto, false);

            MovieEntity movieSaved = movieRepository.save(movieEntity);

            return movieMapper.movieEntity2DTO(movieSaved, false);

        } else {

            throw new Exception("Movie not found");

        }

    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {

        movieRepository.deleteById(id);

    }
}
