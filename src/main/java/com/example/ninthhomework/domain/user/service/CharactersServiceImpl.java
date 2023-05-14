package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.mapper.CharactersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersServiceImpl implements CharactersService {
    @Autowired
    private CharactersMapper charactersMapper;

    @Override
    public List<Characters> getCharacters() {
        return charactersMapper.findAll();
    }

    public List<Characters> findByAge(Integer age) {
        if (age != null) {
            return charactersMapper.searchByAge(age);
        } else {
            return charactersMapper.findAll();
        }
    }
}
