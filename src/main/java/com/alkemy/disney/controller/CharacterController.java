package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterServiceImpl characterService;

    @GetMapping("/list")
    public ResponseEntity<List<CharacterBasicDTO>> getAll() throws Exception {
        List<CharacterBasicDTO> characterDTOS = this.characterService.findAll();
        return ResponseEntity.ok().body(characterDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<CharacterDTO> getOne(@PathVariable Long id) throws Exception {
        CharacterDTO characterDTO = this.characterService.findById(id);
        return ResponseEntity.ok().body(characterDTO);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getCharactersByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) List<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {

        List<CharacterDTO> characterDTOS = this.characterService.getCharactersByFilters(name, age, weight, movies, order);

        return ResponseEntity.ok(characterDTOS);

    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) throws Exception {

        CharacterDTO characterSaved = characterService.save(characterDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);

    }

    @PutMapping
    public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO characterDTO, Long id) throws Exception {

        CharacterDTO result = characterService.update(characterDTO, id);
        return ResponseEntity.ok().body(result);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {

        characterService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
