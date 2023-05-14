package com.example.ninthhomework.Controller;

import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.domain.user.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //IDは含めずに名前と年齢のみ返す
    @GetMapping("/characters")
    public List<CharactersResponse> charactersResponse() {
        return charactersService.getCharacters().stream().map(y -> new CharactersResponse(y.getName(), y.getAge())).toList();
    }

    //指定された年齢より年上のキャラクターを返す
    @GetMapping("/characters/find")
    public List<CharactersResponse> charactersFindAge(@RequestParam(name = "age", required = false) Integer age) {
        return charactersService.findByAge(age).stream()
                .map(y -> new CharactersResponse(y.getName(), y.getAge()))
                .toList();
    }
}

