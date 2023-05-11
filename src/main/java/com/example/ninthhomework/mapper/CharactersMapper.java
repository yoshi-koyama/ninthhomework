package com.example.ninthhomework.mapper;

import com.example.ninthhomework.domain.user.model.Characters;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharactersMapper {
    // public int insertOne(Characters characters);

    //@Select("select * from characters")
    public List<Characters> findAll();
}
