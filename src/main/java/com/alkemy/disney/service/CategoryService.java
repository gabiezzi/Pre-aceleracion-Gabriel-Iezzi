package com.alkemy.disney.service;

import com.alkemy.disney.dto.CategoryDTO;
import com.alkemy.disney.entity.CategoryEntity;


import java.util.List;

public interface CategoryService {

    public List<CategoryDTO> findAll() throws Exception;

    public CategoryEntity findCategoryById(Long id) throws Exception ;

}
