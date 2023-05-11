package com.example.ninthhomework;

import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.domain.user.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharacterListController {
    @Autowired
    private final CharactersService charactersService;

    public CharacterListController(CharactersService charactersService) {
        this.charactersService = charactersService;
    }


    @GetMapping("/characters")
    public List<Characters> characters() {
        return charactersService.getCharacters();
    }


}
