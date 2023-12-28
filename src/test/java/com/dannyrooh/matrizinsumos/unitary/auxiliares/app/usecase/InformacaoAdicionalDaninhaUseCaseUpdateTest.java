package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.InformacaoAdicionalDaninhaUseCaseImpl;
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
class InformacaoAdicionalDaninhaUseCaseUpdateTest {

    private InformacaoAdicionalDaninhaRepository informacaoAdicionalDaninhaRepository;
    private InformacaoAdicionalDaninhaUseCaseImpl informacaoAdicionalDaninhaUseCase;

    @BeforeEach
    void setUp() {
        informacaoAdicionalDaninhaRepository = mock(InformacaoAdicionalDaninhaRepository.class);

        informacaoAdicionalDaninhaUseCase = new InformacaoAdicionalDaninhaUseCaseImpl(informacaoAdicionalDaninhaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> informacaoAdicionalDaninhaUseCase.update(new InformacaoAdicionalDaninhaDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> informacaoAdicionalDaninhaUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> informacaoAdicionalDaninhaUseCase.update(new InformacaoAdicionalDaninhaDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninha = new InformacaoAdicionalDaninhaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalDaninhaUseCase.update(informacaoAdicionalDaninha));
        informacaoAdicionalDaninha.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalDaninhaUseCase.update(informacaoAdicionalDaninha));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninha = new InformacaoAdicionalDaninhaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalDaninhaUseCase.update(informacaoAdicionalDaninha));
        informacaoAdicionalDaninha.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalDaninhaUseCase.update(informacaoAdicionalDaninha));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um informacaoAdicionalDaninha como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(informacaoAdicionalDaninhaRepository.save(any(InformacaoAdicionalDaninha.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    informacaoAdicionalDaninhaUseCase.insert(new InformacaoAdicionalDaninhaDTO(1, "TestGroup First"));
                    informacaoAdicionalDaninhaUseCase.insert(new InformacaoAdicionalDaninhaDTO(2, "TestGroup Seconde"));
                    informacaoAdicionalDaninhaUseCase.update(new InformacaoAdicionalDaninhaDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTO = new InformacaoAdicionalDaninhaDTO(0, "testUpdateWithNonEmptyName");
        when(informacaoAdicionalDaninhaRepository.save(any(InformacaoAdicionalDaninha.class))).thenReturn(new InformacaoAdicionalDaninha());
        assertNotNull(informacaoAdicionalDaninhaUseCase.insert(informacaoAdicionalDaninhaDTO));

    }
}
