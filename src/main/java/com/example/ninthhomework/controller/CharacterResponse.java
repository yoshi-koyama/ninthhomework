package com.example.ninthhomework.controller;

public class CharacterResponse {
    private String name;
    private Integer age;

    public CharacterResponse(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
