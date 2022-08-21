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


    // ##CRUD : The basic operation of create, read, update and delete(soft delete)


    // Brings the list of movies loaded in the database

    @GetMapping("/list")
    public ResponseEntity<List<MovieBasicDTO>> listMovies() throws Exception {

        List<MovieBasicDTO> movieBasicDTOS = movieService.findAll();

        return ResponseEntity.ok().body(movieBasicDTOS);

    }

    //Search by title and filter by genre.
    //Sort the results by creation date in ascending or descending order.
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMoviesByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) List<Long> category,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
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

    //Add or remove a character from the movie log
    @PostMapping("/{idMovie}/character/add/{idCharacter}")
    public ResponseEntity<MovieDTO> addCharacter2Movie(@PathVariable Long idMovie, @PathVariable Long idCharacter) throws Exception {

        MovieDTO movieDTO = movieService.addCharacter2Movie(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO);

    }

    @PostMapping("/{idMovie}/character/remove/{idCharacter}")
    public ResponseEntity<MovieDTO> removeCharacter2Movie(@PathVariable Long idMovie, @PathVariable Long idCharacter) throws Exception {

        MovieDTO movieDTO = movieService.removeCharacter2Movie(idMovie, idCharacter);
        return ResponseEntity.ok().body(movieDTO);

    }




}
