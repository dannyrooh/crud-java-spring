package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalMatriz;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalMatrizRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.InformacaoAdicionalMatrizUseCaseImpl;
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
@ActiveProfiles("teste")
class InformacaoAdicionalMatrizUseCaseInsertTest {

    private InformacaoAdicionalMatrizRepository informacaoAdicionalMatrizRepository;
    private InformacaoAdicionalMatrizUseCaseImpl informacaoAdicionalMatrizUseCase;

    @BeforeEach
    void setUp() {
        informacaoAdicionalMatrizRepository = mock(InformacaoAdicionalMatrizRepository.class);

        informacaoAdicionalMatrizUseCase = new InformacaoAdicionalMatrizUseCaseImpl(informacaoAdicionalMatrizRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> informacaoAdicionalMatrizUseCase.insert(new InformacaoAdicionalMatrizDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> informacaoAdicionalMatrizUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> informacaoAdicionalMatrizUseCase.insert(new InformacaoAdicionalMatrizDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um informacaoAdicionalMatriz como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(informacaoAdicionalMatrizRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTO = new InformacaoAdicionalMatrizDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> informacaoAdicionalMatrizUseCase.insert(informacaoAdicionalMatrizDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTO = new InformacaoAdicionalMatrizDTO(0, "TestGroup");
        when(informacaoAdicionalMatrizRepository.save(any(InformacaoAdicionalMatriz.class))).thenReturn(new InformacaoAdicionalMatriz());
        InformacaoAdicionalMatrizDTO result = informacaoAdicionalMatrizUseCase.insert(informacaoAdicionalMatrizDTO);
        assertNotNull(result);

    }

}
