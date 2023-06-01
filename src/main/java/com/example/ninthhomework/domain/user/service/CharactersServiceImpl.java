package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.controller.CreateForm;
import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.mapper.CharactersMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CharactersServiceImpl implements CharactersService {
    @Autowired
    private CharactersMapper charactersMapper;

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
        return charactersMapper.searchById(id);
    }

    public Characters createCharacter(CreateForm createForm) {
        Characters characters = new Characters(createForm.getId(), createForm.getName(), createForm.getAge());
        charactersMapper.createCharacter(characters);
        return characters;
    }

    public Characters updateCharacter(Characters updatecharacter) {
        Characters characters = charactersMapper.searchById(updatecharacter.getId());
        if (StringUtils.isNotBlank(updatecharacter.getName())) {
            charactersMapper.updateCharacter(updatecharacter);
        } else {
            updatecharacter.setName(characters.getName());
        }

        if (Objects.isNull(updatecharacter.getAge())) {
            updatecharacter.setAge(characters.getAge());
        }
        charactersMapper.updateCharacter(updatecharacter);
        return updatecharacter;
    }
}
