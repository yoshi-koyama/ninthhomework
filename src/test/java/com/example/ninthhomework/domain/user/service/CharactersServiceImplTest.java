package com.example.ninthhomework.domain.user.service;

import com.example.ninthhomework.domain.user.model.Characters;
import com.example.ninthhomework.exception.NotFoundException;
import com.example.ninthhomework.mapper.CharactersMapper;
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
class CharactersServiceImplTest {
    @InjectMocks
    CharactersServiceImpl charactersServiceImpl;

    @Mock
    CharactersMapper charactersMapper;

    @Test
    public void 全てのキャラクターの情報を返すこと() {
        List<Characters> characters = new ArrayList<>();
        characters.add(new Characters(1, "mei", 5));
        characters.add(new Characters(2, "satuki", 10));
        characters.add(new Characters(3, "tatuo", 32));
        doReturn(characters).when(charactersMapper).findAll();
        List<Characters> actual = charactersServiceImpl.getCharacters();
        assertThat(actual).isEqualTo(characters);
        verify(charactersMapper, times(1)).findAll();
    }

    @Test
    public void リストが空の時は空で返すこと() {
        List<Characters> characters = new ArrayList<>();
        doReturn(characters).when(charactersMapper).findAll();
        List<Characters> actual = charactersServiceImpl.getCharacters();
        assertThat(actual).isEqualTo(characters);
        verify(charactersMapper, times(1)).findAll();
    }

    @Test
    public void 指定年齢より年上のデータを返すこと() {
        List<Characters> characters = new ArrayList<>();
        characters.add(new Characters(3, "tatuo", 32));
        doReturn(characters).when(charactersMapper).searchByAge(30);
        List<Characters> actual = charactersServiceImpl.findByAge(30);
        assertThat(actual).isEqualTo(characters);
        verify(charactersMapper, times(1)).searchByAge(30);
        verify(charactersMapper, times(0)).findAll();
    }

    @Test
    public void 年齢未指定の場合全てのデータを返すこと() {
        List<Characters> characters = new ArrayList<>();
        characters.add(new Characters(1, "mei", 5));
        characters.add(new Characters(2, "satuki", 10));
        characters.add(new Characters(3, "tatuo", 32));
        doReturn(characters).when(charactersMapper).findAll();
        List<Characters> actual = charactersServiceImpl.findByAge(null);
        assertThat(actual).isEqualTo(characters);
        verify(charactersMapper, times(0)).searchByAge(null);
        verify(charactersMapper, times(1)).findAll();
    }

    @Test
    public void 指定年齢以上のデータが存在しない時空データを返すこと() {
        List<Characters> characters = new ArrayList<>();
        doReturn(characters).when(charactersMapper).searchByAge(99);
        List<Characters> actual = charactersServiceImpl.findByAge(99);
        assertThat(actual).isEqualTo(characters);
        verify(charactersMapper, times(1)).searchByAge(99);
        verify(charactersMapper, times(0)).findAll();
    }

    @Test
    public void 指定IDのデータを返すこと() throws NotFoundException {
        Optional<Characters> character = Optional.of(new Characters(1, "mei", 5));
        doReturn(character).when(charactersMapper).searchById(1);
        Characters actual = charactersServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Characters(1, "mei", 5));
        verify(charactersMapper, times(1)).searchById(1);
    }

    @Test
    public void 指定IDが存在しない時例外をスローすること() {
        doReturn(Optional.empty()).when(charactersMapper).searchById(99);
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> {
                    Characters actual = charactersServiceImpl.findById(99);
                });
        verify(charactersMapper, times(1)).searchById(99);
    }
}
