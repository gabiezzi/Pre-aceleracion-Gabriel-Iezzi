package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.service.impl.CharacterServiceImpl;
import com.alkemy.disney.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private CharacterServiceImpl characterService;

    @GetMapping("/list")
    public ResponseEntity<List<MovieBasicDTO>> listMovies() throws Exception {

        List<MovieBasicDTO> movieBasicDTOS = movieService.findAll();

        return ResponseEntity.ok().body(movieBasicDTOS);

    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMoviesByFilters(
            @RequestParam(required=false) String title,
            @RequestParam(required=false) String date,
            @RequestParam(required=false) List<Long> category,
            @RequestParam(required=false , defaultValue = "ASC") String order
    ){
        List<MovieDTO> movieDTOS = this.movieService.getMoviesByFilters(title, date, category, order);

        return ResponseEntity.ok(movieDTOS);

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

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@RequestBody MovieDTO movieDTO, @PathVariable Long id) throws Exception {

        MovieDTO result = movieService.update(movieDTO, id);
        return ResponseEntity.ok().body(result);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
