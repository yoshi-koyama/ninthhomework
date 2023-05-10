package com.example.ninthhomework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersServiceImpl implements CharactersService {
    @Autowired
    //mapperクラスをフィールドにおく
    private CharactersMapper charactersMapper;

//    @Override
//    public void signup(Characters characters) {
//        charactersMapper.insertOne(characters);
//    }

    @Override
    public List<Characters> getCharacters() {
        return charactersMapper.findMany();
    }
}
