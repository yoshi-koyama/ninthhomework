package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.controller.CreateForm;
import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.exception.NotFoundException;
import com.example.ninthhomework.mapper.CharactersMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CharactersServiceImpl implements CharactersService {
    private final CharactersMapper charactersMapper;

    public CharactersServiceImpl(CharactersMapper charactersMapper) {
        this.charactersMapper = charactersMapper;
    }

    @Override
    public List<Characters> getCharacters() {
        return charactersMapper.findAll();
    }

    public List<Characters> findByAge(Integer age) {
        if (Objects.isNull(age)) {
            return charactersMapper.findAll();
        } else {
            return charactersMapper.searchByAge(age);
        }
    }

    public Characters findById(int id) {
        return this.charactersMapper.searchById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Characters createCharacter(CreateForm createForm) {
        Characters characters = new Characters(createForm.getId(), createForm.getName(), createForm.getAge());
        charactersMapper.createCharacter(characters);
        return characters;
    }

    public Characters updateCharacter(int id, String name, Integer age) {
        Characters characters = charactersMapper.searchById(id).orElseThrow(() -> new NotFoundException(id));
        characters.update(name, age);
        charactersMapper.updateCharacter(characters);
        return characters;
    }

    public void deleteCharacter(int id) {
        charactersMapper.searchById(id).orElseThrow(() -> new NotFoundException(id));
        charactersMapper.deleteCharacter(id);
    }
}
