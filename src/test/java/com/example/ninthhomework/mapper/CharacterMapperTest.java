package com.example.ninthhomework.mapper;

import com.example.ninthhomework.domain.user.model.Character;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
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
class CharacterMapperTest {
    @Autowired
    CharacterMapper characterMapper;

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 全てのデータが取得できること() {
        List<Character> characters = characterMapper.findAll();
        assertThat(characters)
                .hasSize(3)
                .contains(
                        new Character(1, "mei", 5),
                        new Character(2, "satuki", 10),
                        new Character(3, "tatuo", 32)
                );
    }

    @Test
    @DataSet(value = "datasets/empty.yml")
    @Transactional
    void データが空の時は空で返すこと() {
        List<Character> characters = characterMapper.findAll();
        assertThat(characters).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定した年齢より上のユーザーが取得できること() {
        List<Character> characters = characterMapper.searchByAge(23);
        assertThat(characters).hasSize(1)
                .contains(new Character(3, "tatuo", 32));
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定した年齢より年下しか存在しない時空で返すこと() {
        List<Character> characters = characterMapper.searchByAge(40);
        assertThat(characters).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定したIDのデータを返すこと() {
        Optional<Character> character = characterMapper.searchById(1);
        assertThat(character).contains(new Character(1, "mei", 5));
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 指定したIDが存在しない時空で返すこと() {
        Optional<Character> characters = characterMapper.searchById(5);
        assertThat(characters).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @ExpectedDataSet(value = "datasets/insert_characters.yml", ignoreCols = "id")
    @Transactional
    void データが登録できそのIDが既存のものより大きいこと() {
        Character character4 = new Character("shizuku", 14);
        assertThat(character4.getId()).isEqualTo(0);

        characterMapper.createCharacter(character4);
        assertThat(character4.getId()).isGreaterThan(0);

        Character character5 = new Character("umi", 16);
        assertThat(character5.getId()).isEqualTo(0);

        characterMapper.createCharacter(character5);
        assertThat(character5.getId()).isGreaterThan(0);

        assertThat(character5.getId() > character4.getId());

    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @ExpectedDataSet(value = "datasets/update_characters.yml")
    void 指定されたIDのデータを更新すること() {
        characterMapper.updateCharacter(new Character(3, "草壁タツオ", 34));
    }

    @Test
    @DataSet("datasets/characters.yml")
    @ExpectedDataSet("datasets/characters.yml")
    void 指定されたIDが存在しない時は何もしないこと() {
        characterMapper.updateCharacter(new Character(99, "kamide", 99));
    }

    @Test
    @DataSet("datasets/characters.yml")
    @ExpectedDataSet("datasets/delete_characters.yml")
    void 指定されたIDが削除されること() {
        characterMapper.deleteCharacter(3);
    }

    @Test
    @DataSet("datasets/characters.yml")
    @ExpectedDataSet("datasets/characters.yml")
    void 指定したIDが存在しない時は何もしないこと() {
        characterMapper.deleteCharacter(99);
    }
}
