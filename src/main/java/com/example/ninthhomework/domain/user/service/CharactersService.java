package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.controller.CreateForm;
import com.example.ninthhomework.domain.user.model.Characters;

import java.util.List;

public interface CharactersService {

    public List<Characters> getCharacters();

    public List<Characters> findByAge(Integer age);

    public Characters createCharacter(CreateForm createForm);

    public Characters updateCharacter(Characters updatecharacter);

    public Characters findById(int id);

}
