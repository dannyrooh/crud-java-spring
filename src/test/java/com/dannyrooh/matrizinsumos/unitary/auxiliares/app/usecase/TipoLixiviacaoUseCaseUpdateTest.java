package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoLixiviacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoLixiviacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoLixiviacaoUseCaseImpl;
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
class TipoLixiviacaoUseCaseUpdateTest {

    private TipoLixiviacaoRepository tipoLixiviacaoRepository;
    private TipoLixiviacaoUseCaseImpl tipoLixiviacaoUseCase;

    @BeforeEach
    void setUp() {
        tipoLixiviacaoRepository = mock(TipoLixiviacaoRepository.class);

        tipoLixiviacaoUseCase = new TipoLixiviacaoUseCaseImpl(tipoLixiviacaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> tipoLixiviacaoUseCase.update(new TipoLixiviacaoDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoLixiviacaoUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> tipoLixiviacaoUseCase.update(new TipoLixiviacaoDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        TipoLixiviacaoDTO tipoLixiviacao = new TipoLixiviacaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoLixiviacaoUseCase.update(tipoLixiviacao));
        tipoLixiviacao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoLixiviacaoUseCase.update(tipoLixiviacao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        TipoLixiviacaoDTO tipoLixiviacao = new TipoLixiviacaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoLixiviacaoUseCase.update(tipoLixiviacao));
        tipoLixiviacao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoLixiviacaoUseCase.update(tipoLixiviacao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoLixiviacao como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(tipoLixiviacaoRepository.save(any(TipoLixiviacao.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    tipoLixiviacaoUseCase.insert(new TipoLixiviacaoDTO(1, "TestGroup First"));
                    tipoLixiviacaoUseCase.insert(new TipoLixiviacaoDTO(2, "TestGroup Seconde"));
                    tipoLixiviacaoUseCase.update(new TipoLixiviacaoDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        TipoLixiviacaoDTO tipoLixiviacaoDTO = new TipoLixiviacaoDTO(0, "testUpdateWithNonEmptyName");
        when(tipoLixiviacaoRepository.save(any(TipoLixiviacao.class))).thenReturn(new TipoLixiviacao());
        assertNotNull(tipoLixiviacaoUseCase.insert(tipoLixiviacaoDTO));

    }
}
