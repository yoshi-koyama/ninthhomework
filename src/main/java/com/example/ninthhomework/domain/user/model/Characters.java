package com.example.ninthhomework.domain.user.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Characters {
    private int id;
    private String name;
    private Integer age;

    public Characters(int id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(String name, Integer age) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        }
        if (Objects.nonNull(age)) {
            this.age = age;
        }
    }
}
