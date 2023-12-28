package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Atuacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.AtuacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.AtuacaoUseCaseImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AtuacaoUseCaseUpdateTest {

    private AtuacaoRepository atuacaoRepository;
    private AtuacaoUseCaseImpl atuacaoUseCase;

    @BeforeEach
    void setUp() {
        atuacaoRepository = mock(AtuacaoRepository.class);

        atuacaoUseCase = new AtuacaoUseCaseImpl(atuacaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> atuacaoUseCase.update(new AtuacaoDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> atuacaoUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> atuacaoUseCase.update(new AtuacaoDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        AtuacaoDTO atuacao = new AtuacaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> atuacaoUseCase.update(atuacao));
        atuacao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> atuacaoUseCase.update(atuacao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        AtuacaoDTO atuacao = new AtuacaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> atuacaoUseCase.update(atuacao));
        atuacao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> atuacaoUseCase.update(atuacao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um atuacao como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(atuacaoRepository.save(any(Atuacao.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    atuacaoUseCase.insert(new AtuacaoDTO(1, "TestGroup First"));
                    atuacaoUseCase.insert(new AtuacaoDTO(2, "TestGroup Seconde"));
                    atuacaoUseCase.update(new AtuacaoDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        AtuacaoDTO atuacaoDTO = new AtuacaoDTO(0, "testUpdateWithNonEmptyName");
        when(atuacaoRepository.save(any(Atuacao.class))).thenReturn(new Atuacao());
        assertNotNull(atuacaoUseCase.insert(atuacaoDTO));

    }
}
