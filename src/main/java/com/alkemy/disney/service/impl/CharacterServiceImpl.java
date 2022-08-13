package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.service.BaseService;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService, BaseService<CharacterDTO> {


    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Override
    @Transactional
    public List<CharacterBasicDTO> findAll() throws Exception {
        try {

            List<CharacterEntity> movieEntities = characterRepository.findAll();

            List<CharacterBasicDTO> basicDTOS = characterMapper.characterEntity2BasicDTOList(movieEntities);

            //List<CharacterDTO> dtos = movieEntities.stream().map(characterEntity -> modelMapper.map(characterEntity, CharacterDTO.class)).collect(Collectors.toList());

            return basicDTOS;
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CharacterEntity findCharacterById(Long id) throws Exception {
        Optional<CharacterEntity> optional = characterRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Param not found : Character id");
        }

        return optional.get();

    }

    @Override
    @Transactional
    public CharacterDTO findById(Long id) throws Exception {

        CharacterEntity characterEntity = findCharacterById(id);

        return characterMapper.characterEntity2DTO(characterEntity,true);
    }

    @Override
    @Transactional
    public CharacterDTO save(CharacterDTO dto) throws Exception {

        try {
            CharacterEntity characterEntity = characterMapper.characterDTO2Entity(dto,true);

            CharacterEntity entitySaved = characterRepository.save(characterEntity);

            return characterMapper.characterEntity2DTO(entitySaved,true);


        } catch (Exception e) {
            throw new Exception("Character not found");
        }

    }

    @Override
    @Transactional
    public CharacterDTO update(CharacterDTO dto) throws Exception {


        Optional<CharacterEntity> entityOptional = characterRepository.findById(dto.getId());

        if (entityOptional.isPresent()) {
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
