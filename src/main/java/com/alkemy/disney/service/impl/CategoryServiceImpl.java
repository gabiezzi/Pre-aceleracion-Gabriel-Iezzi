package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CategoryDTO;
import com.alkemy.disney.entity.CategoryEntity;
import com.alkemy.disney.repository.CategoryRepository;
import com.alkemy.disney.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements BaseService<CategoryDTO> {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<CategoryDTO> findAll() throws Exception {
        try {
            List<CategoryEntity> entities = categoryRepository.findAll();
            List<CategoryDTO> dtos = entities.stream().map(entity -> modelMapper.map(entity, CategoryDTO.class)).collect(Collectors.toList());

            return dtos;
        } catch (Exception e) {

            throw new Exception("Category not found ");

        }



    }

    @Override
    @Transactional
    public CategoryDTO findById(Long id) throws Exception {

        Optional<CategoryEntity> optionalEntity = categoryRepository.findById(id);

        if (optionalEntity.isPresent()){

            return modelMapper.map(optionalEntity,CategoryDTO.class);

        } else {

            throw new Exception("Category not found");
        }

    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO dto) throws Exception {

        try{
            CategoryEntity categoryEntity = modelMapper.map(dto, CategoryEntity.class);
            CategoryEntity categorySaved = categoryRepository.save(categoryEntity);
            return modelMapper.map(categorySaved,CategoryDTO.class);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public CategoryDTO update(CategoryDTO dto) throws Exception {

        Optional<CategoryEntity> entityOptional = categoryRepository.findById(dto.getId());

        if (entityOptional.isPresent()) {

            return save(dto);

        } else {
            throw new Exception("Category not found");
        }


    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {

        categoryRepository.deleteById(id);

    }
}
