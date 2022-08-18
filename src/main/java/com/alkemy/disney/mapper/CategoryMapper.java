package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CategoryDTO;
import com.alkemy.disney.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public CategoryEntity categoryDTO2Entity(CategoryDTO categoryDTO) {

        System.out.println(categoryDTO);

        CategoryEntity categoryEntity = new CategoryEntity();

        if (categoryDTO.getId() != null) {

            categoryEntity.setId(categoryDTO.getId());

        }

        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setImage(categoryDTO.getImage());

        System.out.println(categoryEntity);

        return categoryEntity;

    }

    public CategoryDTO categoryEntity2DTO(CategoryEntity categoryEntity) {

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setImage(categoryEntity.getImage());
        categoryDTO.setName(categoryEntity.getName());

        return categoryDTO;


    }

    public List<CategoryEntity> categoryDTO2EntityList(List<CategoryDTO> categoryDTOS) {

        List<CategoryEntity> categoryEntities = new ArrayList<>();

        for (CategoryDTO dto : categoryDTOS
        ) {

            categoryEntities.add(categoryDTO2Entity(dto));

        }

        return categoryEntities;

    }

    public List<CategoryDTO> categoryEntity2DTOList(List<CategoryEntity> categoryEntities) {

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (CategoryEntity entity : categoryEntities
        ) {

            categoryDTOS.add(categoryEntity2DTO(entity));

        }

        return categoryDTOS;

    }


}
