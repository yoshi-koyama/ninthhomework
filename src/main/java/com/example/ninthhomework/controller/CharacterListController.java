package com.example.ninthhomework.controller;

import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.domain.user.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class CharacterListController {
    @Autowired
    private final CharactersService charactersService;

    public CharacterListController(CharactersService charactersService) {
        this.charactersService = charactersService;
    }

    //IDも含めて全て返す
    @GetMapping("/characters/all")
    public List<Characters> charactersServices() {
        return charactersService.getCharacters().stream().toList();
    }

    //指定したIDの内容のみ返す
    @GetMapping("/characters/{id}")
    public Characters charactersFindId(@PathVariable("id") int id) {
        return charactersService.findById(id);
    }

    //IDは含めずに名前と年齢のみ返す
    @GetMapping("/characters")
    public List<CharactersResponse> charactersResponse() {
        return charactersService.getCharacters().stream().map(y -> new CharactersResponse(y.getName(), y.getAge())).toList();
    }

    //指定された年齢より年上のキャラクターを返す
    @GetMapping("/characters/age")
    public List<CharactersResponse> charactersFindAge(@RequestParam(name = "age", required = false) Integer age) {
        return charactersService.findByAge(age).stream()
                .map(y -> new CharactersResponse(y.getName(), y.getAge()))
                .toList();
    }

    @PostMapping("/characters")
    public ResponseEntity<Map<String, String>> create
            (@RequestBody @Validated CreateForm createForm, UriComponentsBuilder uriBuilder) {
        Characters characters = charactersService.createCharacter(createForm);

        URI url = uriBuilder
                .path("/characters/" + characters.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "character successfully created"));
    }

    @PatchMapping("/characters/{id}")
    public ResponseEntity<Map<String, String>> update
            (@PathVariable("id") int id, @RequestBody @Validated UpdateForm updateForm) {
        charactersService.updateCharacter(updateForm.UpdateForm(id));
        return ResponseEntity.ok(Map.of("message", "character successfully updated"));
    }
}

