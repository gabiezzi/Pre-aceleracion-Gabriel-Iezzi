package com.alkemy.disney.controller;

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
    public ResponseEntity<List<CharacterDTO>> listCharacters() throws Exception{
        List<CharacterDTO> characterDTOS = characterService.findAll();
        return ResponseEntity.ok().body(characterDTOS);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) throws Exception {

        CharacterDTO characterSaved = characterService.save(characterDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) throws Exception{

        characterService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
