package com.alkemy.disney.service;


import java.util.List;

public interface BaseService<E> {
    public List<E> findAll() throws Exception;
    public E findById(Long id) throws Exception;
    public E save(E entity) throws Exception;
    public E update( E entity) throws  Exception;
    public void delete(Long id) throws Exception;

}