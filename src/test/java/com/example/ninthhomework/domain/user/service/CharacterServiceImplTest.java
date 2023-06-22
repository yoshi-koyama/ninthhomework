package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.domain.user.model.Character;
import com.example.ninthhomework.exception.NotFoundException;
import com.example.ninthhomework.mapper.CharacterMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceImplTest {
    @InjectMocks
    CharacterServiceImpl charactersServiceImpl;

    @Mock
    CharacterMapper characterMapper;

    @Test
    public void 全てのキャラクターの情報を返すこと() {
        List<Character> characters = new ArrayList<>();
        characters.add(new Character(1, "mei", 5));
        characters.add(new Character(2, "satuki", 10));
        characters.add(new Character(3, "tatuo", 32));
        doReturn(characters).when(characterMapper).findAll();
        List<Character> actual = charactersServiceImpl.getCharacters();
        assertThat(actual).isEqualTo(characters);
        verify(characterMapper, times(1)).findAll();
    }

    @Test
    public void リストが空の時は空で返すこと() {
        List<Character> characters = new ArrayList<>();
        doReturn(characters).when(characterMapper).findAll();
        List<Character> actual = charactersServiceImpl.getCharacters();
        assertThat(actual).isEqualTo(characters);
        verify(characterMapper, times(1)).findAll();
    }

    @Test
    public void 指定年齢より年上のデータを返すこと() {
        List<Character> characters = new ArrayList<>();
        characters.add(new Character(3, "tatuo", 32));
        doReturn(characters).when(characterMapper).searchByAge(30);
        List<Character> actual = charactersServiceImpl.findByAge(30);
        assertThat(actual).isEqualTo(characters);
        verify(characterMapper, times(1)).searchByAge(30);
        verify(characterMapper, never()).findAll();
    }

    @Test
    public void 年齢未指定の場合全てのデータを返すこと() {
        List<Character> characters = new ArrayList<>();
        characters.add(new Character(1, "mei", 5));
        characters.add(new Character(2, "satuki", 10));
        characters.add(new Character(3, "tatuo", 32));
        doReturn(characters).when(characterMapper).findAll();
        List<Character> actual = charactersServiceImpl.findByAge(null);
        assertThat(actual).isEqualTo(characters);
        verify(characterMapper, never()).searchByAge(null);
        verify(characterMapper, times(1)).findAll();
    }

    @Test
    public void 指定年齢以上のデータが存在しない時空データを返すこと() {
        List<Character> characters = new ArrayList<>();
        doReturn(characters).when(characterMapper).searchByAge(99);
        List<Character> actual = charactersServiceImpl.findByAge(99);
        assertThat(actual).isEqualTo(characters);
        verify(characterMapper, times(1)).searchByAge(99);
        verify(characterMapper, never()).findAll();
    }

    @Test
    public void 指定IDのデータを返すこと() throws NotFoundException {
        Optional<Character> character = Optional.of(new Character(1, "mei", 5));
        doReturn(character).when(characterMapper).searchById(1);
        Character actual = charactersServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Character(1, "mei", 5));
        verify(characterMapper, times(1)).searchById(1);
    }

    @Test
    public void 指定IDが存在しない時例外をスローすること() {
        doReturn(Optional.empty()).when(characterMapper).searchById(99);
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> {
                    Character actual = charactersServiceImpl.findById(99);
                }).withMessageMatching("ID:99は見つかりませんでした");
        verify(characterMapper, times(1)).searchById(99);
    }
}
