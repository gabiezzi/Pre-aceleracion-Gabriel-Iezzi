package com.alkemy.disney.service;


import com.alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface BaseService<E> {

    public E findById(Long id) throws Exception;
    public E save(E entity) throws Exception;
    public E update( E entity, Long id) throws  Exception;
    public void delete(Long id) throws Exception;

}