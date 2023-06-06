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

    //指定したIDの内容のみ返す
    @GetMapping("/characters/{id}")
    public Characters findCharacterById(@PathVariable("id") int id) {
        return charactersService.findById(id);
    }

    // このAPIはController層でResponseクラスへ変換する方法を試すために作りました
    @GetMapping("/characters-without-id")
    public List<CharactersResponse> selectCharacters() {
        return charactersService.getCharacters().stream().map(y -> new CharactersResponse(y.getName(), y.getAge())).toList();
    }

    //クエリに指定がない時は全件、指定ありの時は年齢でフィルターをかけて表示
    @GetMapping("/characters")
    public List<Characters> findCharacterByAge(@RequestParam(name = "age", required = false) Integer age) {
        return charactersService.findByAge(age);
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
            (@PathVariable("id") int id, @RequestBody UpdateForm updateForm) {
        charactersService.updateCharacter(id, updateForm.getName(), updateForm.getAge());
        return ResponseEntity.ok(Map.of("message", "character successfully updated"));
    }

    @DeleteMapping("characters/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") int id) {
        charactersService.deleteCharacter(id);
        return ResponseEntity.ok(Map.of("message", "character successfully deleted"));
    }
}

