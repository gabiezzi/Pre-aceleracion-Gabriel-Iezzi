package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @GetMapping("/list")
    public ResponseEntity<List<MovieDTO>> listMovies() throws Exception {

        List<MovieDTO> movieDTOS = movieService.findAll();

        return ResponseEntity.ok().body(movieDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> buscarPorId(@PathVariable("id") Long id) throws Exception {
        MovieDTO result = movieService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) throws Exception {

        MovieDTO movieSaved = movieService.save(movieDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);

    }

    @PutMapping
    public ResponseEntity<MovieDTO> update(@RequestBody MovieDTO movieDTO) throws Exception {

        MovieDTO result = movieService.update(movieDTO);
        return ResponseEntity.ok().body(result);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
