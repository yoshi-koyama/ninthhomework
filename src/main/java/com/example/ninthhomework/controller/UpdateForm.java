package com.example.ninthhomework.controller;

import com.example.ninthhomework.domain.user.model.Characters;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateForm {
    @NotBlank
    private String name;
    @NotNull
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
