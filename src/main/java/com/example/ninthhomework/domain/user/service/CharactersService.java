package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.Controller.CreateForm;
import com.example.ninthhomework.domain.user.model.Characters;

import java.util.List;

public interface CharactersService {

    public List<Characters> getCharacters();

    public List<Characters> findByAge(Integer age);

    public Characters createCharacter(CreateForm createForm);
    
}
