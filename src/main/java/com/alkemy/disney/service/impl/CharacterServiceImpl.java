package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CharacterServiceImpl implements BaseService<CharacterDTO> {


    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<CharacterDTO> findAll() throws Exception {
        try {

            List<CharacterEntity> movieEntities = characterRepository.findAll();
            List<CharacterDTO> dtos = movieEntities.stream().map(characterEntity -> modelMapper.map(characterEntity, CharacterDTO.class)).collect(Collectors.toList());

            return dtos;
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public CharacterDTO findById(Long id) throws Exception {
        Optional<CharacterEntity> optional = characterRepository.findById(id);
        if (optional.isPresent()){
            return modelMapper.map(optional,CharacterDTO.class);
        } else {
            throw new Exception("No characters were found with that id");
        }
    }

    @Override
    @Transactional
    public CharacterDTO save(CharacterDTO dto) throws Exception {

        try {
            CharacterEntity characterEntity = modelMapper.map(dto, CharacterEntity.class);

            CharacterEntity entitySaved = characterRepository.save(characterEntity);

            return modelMapper.map(entitySaved,CharacterDTO.class);


        } catch (Exception e){
            throw new Exception("Character not found");
        }

    }

    @Override
    @Transactional
    public CharacterDTO update(CharacterDTO dto) throws Exception {


        Optional<CharacterEntity> entityOptional = characterRepository.findById(dto.getId());

        if (entityOptional.isPresent()){
            return save(dto);
        } else {

            throw new Exception("Character not found");
        }


    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {

        characterRepository.deleteById(id);

    }
}
