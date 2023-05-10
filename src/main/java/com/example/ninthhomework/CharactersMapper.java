package com.example.ninthhomework;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharactersMapper {
    // public int insertOne(Characters characters);
    
    public List<Characters> findMany();
}
