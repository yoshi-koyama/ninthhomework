package com.example.ninthhomework.mapper;

import com.example.ninthhomework.domain.user.model.Characters;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharactersMapper {
    public List<Characters> findAll();

    public List<Characters> searchByAge(Integer age);

    public List<Characters> searchById(int id);

    public void createCharacter(Characters characters);

    public void updateCharacter(Characters characters);

}
