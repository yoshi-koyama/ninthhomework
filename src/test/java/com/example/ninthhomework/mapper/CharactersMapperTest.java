package com.example.ninthhomework.mapper;

import com.example.ninthhomework.domain.user.model.Characters;
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
class CharactersMapperTest {
    @Autowired
    CharactersMapper charactersMapper;

    @Test
    @DataSet(value = "datasets/characters.yml")
    @Transactional
    void 全てのデータが取得できること() {
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

    @Test
    @DataSet(value = "datasets/characters.yml")
    @ExpectedDataSet(value = "datasets/insert_characters.yml", ignoreCols = "id")
    @Transactional
    void データが登録できそのIDが既存のものより大きいこと() {
        Characters character4 = new Characters("shizuku", 14);
        assertThat(character4.getId()).isEqualTo(0);

        charactersMapper.createCharacter(character4);
        assertThat(character4.getId()).isGreaterThan(0);

        Characters characters5 = new Characters("umi", 16);
        assertThat(characters5.getId()).isEqualTo(0);

        charactersMapper.createCharacter(characters5);
        assertThat(characters5.getId()).isGreaterThan(0);
        
        assertThat(characters5.getId() > character4.getId());

    }

    @Test
    @DataSet(value = "datasets/characters.yml")
    @ExpectedDataSet(value = "datasets/update_characters.yml")
    void 指定されたIDのデータを更新すること() {
        charactersMapper.updateCharacter(new Characters(3, "草壁タツオ", 34));
    }

    @Test
    @DataSet("datasets/characters.yml")
    @ExpectedDataSet("datasets/characters.yml")
    void 指定されたIDが存在しない時は何もしないこと() {
        charactersMapper.updateCharacter(new Characters(99, "kamide", 99));
    }

    @Test
    @DataSet("datasets/characters.yml")
    @ExpectedDataSet("datasets/delete_characters.yml")
    void 指定されたIDが削除されること() {
        charactersMapper.deleteCharacter(3);
    }

    @Test
    @DataSet("datasets/characters.yml")
    @ExpectedDataSet("datasets/characters.yml")
    void 指定したIDが存在しない時は何もしないこと() {
        charactersMapper.deleteCharacter(99);
    }
}
