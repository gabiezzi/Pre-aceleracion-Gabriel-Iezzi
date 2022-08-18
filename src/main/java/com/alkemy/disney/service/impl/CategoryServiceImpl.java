package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CategoryDTO;
import com.alkemy.disney.entity.CategoryEntity;
import com.alkemy.disney.mapper.CategoryMapper;
import com.alkemy.disney.repository.CategoryRepository;
import com.alkemy.disney.service.BaseService;
import com.alkemy.disney.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService, BaseService<CategoryDTO> {


    private final CategoryRepository  categoryRepository;

    private final CategoryMapper categoryMapper;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    @Transactional
    public List<CategoryDTO> findAll() throws Exception {
        try {
            List<CategoryEntity> entities = categoryRepository.findAll();

            //List<CategoryDTO> dtos = entities.stream().map(entity -> modelMapper.map(entity, CategoryDTO.class)).collect(Collectors.toList());

            return categoryMapper.categoryEntity2DTOList(entities);
        } catch (Exception e) {

            throw new Exception("Category not found");

        }


    }

    @Override
    public CategoryEntity findCategoryById(Long id) throws Exception {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);

        if (optional.isEmpty()) {

            throw new Exception("Param not found : Category Id");

        }
        return optional.get();


    }


    @Transactional
    public CategoryDTO findById(Long id) throws Exception {

        CategoryEntity categoryEntity = findCategoryById(id);

        return categoryMapper.categoryEntity2DTO(categoryEntity);

    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO dto) throws Exception {

        try {

            CategoryEntity categoryEntity = categoryMapper.categoryDTO2Entity(dto);

            CategoryEntity categorySaved = categoryRepository.save(categoryEntity);

            return categoryMapper.categoryEntity2DTO(categorySaved);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id) throws Exception {

        Optional<CategoryEntity> entityOptional = categoryRepository.findById(dto.getId());

        if (entityOptional.isPresent()) {

            CategoryEntity categoryEntity = categoryMapper.categoryDTO2Entity(dto);
            CategoryEntity categorySaved = categoryRepository.save(categoryEntity);

            return categoryMapper.categoryEntity2DTO(categorySaved);

        } else {
            throw new Exception("Category not found");
        }


    }

    @Override
    @Transactional
    public void delete(Long id)  {

        categoryRepository.deleteById(id);

    }
}
