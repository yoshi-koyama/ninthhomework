package com.example.ninthhomework.mapper;

import com.example.ninthhomework.domain.user.model.Characters;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CharactersMapperTest {
    @Autowired
    CharactersMapper charactersMapper;

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 全てのデータが取得できること() {
        //DBからデータをとってくる
        List<Characters> characters = charactersMapper.findAll();
        assertThat(characters)
                .hasSize(3)
                .contains(
                        new Characters(1, "mei", 5),
                        new Characters(2, "satuki", 10),
                        new Characters(3, "tatuo", 32)
                );
    }

    @Test
    @DataSet(value = "datasets/empty.yml")
    @Transactional
    void データが空の時は空で返すこと() {
        List<Characters> characters = charactersMapper.findAll();
        assertThat(characters).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定した年齢より上のユーザーが取得できること() {
        List<Characters> characters = charactersMapper.searchByAge(23);
        assertThat(characters).hasSize(1)
                .contains(new Characters(3, "tatuo", 32));
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定した年齢より年下しか存在しない時空で返すこと() {
        List<Characters> characters = charactersMapper.searchByAge(40);
        assertThat(characters).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定したIDのデータを返すこと() {
        Optional<Characters> characters = charactersMapper.searchById(1);
        assertThat(characters).contains(new Characters(1, "mei", 5));
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定したIDが存在しない時空で返すこと() {
        Optional<Characters> characters = charactersMapper.searchById(5);
        assertThat(characters).isEmpty();
    }
}