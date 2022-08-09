package com.alkemy.disney.service.impl;


import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.repository.MovieRepository;

import com.alkemy.disney.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements BaseService<MovieDTO> {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public List<MovieDTO> findAll() throws Exception {

        try {
            List<MovieEntity> entities = movieRepository.findAll();
            List<MovieDTO> dtos = entities.stream().map(entity -> modelMapper.map(entity, MovieDTO.class)).collect(Collectors.toList());
            return dtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public MovieDTO findById(Long id) throws Exception {

        MovieEntity optional = movieRepository.findById(id).orElse(null);

        if (optional!=null) {
            return modelMapper.map(optional, MovieDTO.class);

        } else {
            throw new Exception("Movie not found");
        }
    }

    @Override
    @Transactional
    public MovieDTO save(MovieDTO dto) throws Exception {

        try {
            MovieEntity entity = modelMapper.map(dto, MovieEntity.class);

            MovieEntity entitySaved = movieRepository.save(entity);
            return modelMapper.map(entitySaved, MovieDTO.class);
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public MovieDTO update(MovieDTO dto) throws Exception {

        Optional<MovieEntity> entityOptional = movieRepository.findById(dto.getId());


        if (entityOptional.isPresent()) {


            return save(dto);


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
