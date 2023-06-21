package com.example.ninthhomework.mapper;

import com.example.ninthhomework.domain.user.model.Character;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CharacterMapper {
    public List<Character> findAll();

    public List<Character> searchByAge(Integer age);

    public Optional<Character> searchById(int id);

    public void createCharacter(Character character);

    public void updateCharacter(Character character);

    public void deleteCharacter(int id);

}
