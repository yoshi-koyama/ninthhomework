package com.example.ninthhomework.controller;

import com.example.ninthhomework.domain.user.model.Characters;

public class UpdateForm {

    private String name;

    private Integer age;

    public Characters UpdateForm(int id) {
        Characters updatecharacter = new Characters(id, name, age);
        return updatecharacter;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
