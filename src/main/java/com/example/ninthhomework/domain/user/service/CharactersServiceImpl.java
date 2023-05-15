package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.mapper.CharactersMapper;
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
}
