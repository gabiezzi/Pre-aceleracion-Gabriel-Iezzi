package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CategoryDTO;
import com.alkemy.disney.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    // ##CRUD : The basic operation of create, read, update and delete(soft delete)


    // Brings the list of categories loaded in the database
    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> listCategories() throws Exception {

        List<CategoryDTO> categoryDTOS = categoryService.findAll();

        return ResponseEntity.ok().body(categoryDTOS);

    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) throws Exception {

        CategoryDTO categorySaved = categoryService.save(categoryDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);


    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO, Long id) throws Exception {

        CategoryDTO result = categoryService.update(categoryDTO, id);

        return ResponseEntity.ok().body(result);


    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {

        categoryService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
