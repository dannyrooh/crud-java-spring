package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalMatriz;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalMatrizRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.InformacaoAdicionalMatrizUseCaseImpl;
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
class InformacaoAdicionalMatrizUseCaseUpdateTest {

    private InformacaoAdicionalMatrizRepository informacaoAdicionalMatrizRepository;
    private InformacaoAdicionalMatrizUseCaseImpl informacaoAdicionalMatrizUseCase;

    @BeforeEach
    void setUp() {
        informacaoAdicionalMatrizRepository = mock(InformacaoAdicionalMatrizRepository.class);

        informacaoAdicionalMatrizUseCase = new InformacaoAdicionalMatrizUseCaseImpl(informacaoAdicionalMatrizRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> informacaoAdicionalMatrizUseCase.update(new InformacaoAdicionalMatrizDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> informacaoAdicionalMatrizUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> informacaoAdicionalMatrizUseCase.update(new InformacaoAdicionalMatrizDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        InformacaoAdicionalMatrizDTO informacaoAdicionalMatriz = new InformacaoAdicionalMatrizDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalMatrizUseCase.update(informacaoAdicionalMatriz));
        informacaoAdicionalMatriz.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalMatrizUseCase.update(informacaoAdicionalMatriz));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        InformacaoAdicionalMatrizDTO informacaoAdicionalMatriz = new InformacaoAdicionalMatrizDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalMatrizUseCase.update(informacaoAdicionalMatriz));
        informacaoAdicionalMatriz.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> informacaoAdicionalMatrizUseCase.update(informacaoAdicionalMatriz));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um informacaoAdicionalMatriz como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(informacaoAdicionalMatrizRepository.save(any(InformacaoAdicionalMatriz.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    informacaoAdicionalMatrizUseCase.insert(new InformacaoAdicionalMatrizDTO(1, "TestGroup First"));
                    informacaoAdicionalMatrizUseCase.insert(new InformacaoAdicionalMatrizDTO(2, "TestGroup Seconde"));
                    informacaoAdicionalMatrizUseCase.update(new InformacaoAdicionalMatrizDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTO = new InformacaoAdicionalMatrizDTO(0, "testUpdateWithNonEmptyName");
        when(informacaoAdicionalMatrizRepository.save(any(InformacaoAdicionalMatriz.class))).thenReturn(new InformacaoAdicionalMatriz());
        assertNotNull(informacaoAdicionalMatrizUseCase.insert(informacaoAdicionalMatrizDTO));

    }
}
