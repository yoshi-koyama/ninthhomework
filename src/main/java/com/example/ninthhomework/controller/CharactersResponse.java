package com.example.ninthhomework.controller;

public class CharactersResponse {
    private String name;
    private Integer age;

    public CharactersResponse(String name, Integer age) {
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
