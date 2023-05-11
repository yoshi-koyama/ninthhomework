package com.example.ninthhomework.domain.user.model;

//@Data Lombookダウンロード　無闇にやらない方がいいのか？
//これはエンティティクラス
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
}
