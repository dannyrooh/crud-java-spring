package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.InformacaoAdicionalDaninhaUseCaseImpl;
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
class InformacaoAdicionalDaninhaUseCaseInsertTest {

    private InformacaoAdicionalDaninhaRepository informacaoAdicionalDaninhaRepository;
    private InformacaoAdicionalDaninhaUseCaseImpl informacaoAdicionalDaninhaUseCase;

    @BeforeEach
    void setUp() {
        informacaoAdicionalDaninhaRepository = mock(InformacaoAdicionalDaninhaRepository.class);

        informacaoAdicionalDaninhaUseCase = new InformacaoAdicionalDaninhaUseCaseImpl(informacaoAdicionalDaninhaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> informacaoAdicionalDaninhaUseCase.insert(new InformacaoAdicionalDaninhaDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> informacaoAdicionalDaninhaUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> informacaoAdicionalDaninhaUseCase.insert(new InformacaoAdicionalDaninhaDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um informacaoAdicionalDaninha como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(informacaoAdicionalDaninhaRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTO = new InformacaoAdicionalDaninhaDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> informacaoAdicionalDaninhaUseCase.insert(informacaoAdicionalDaninhaDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTO = new InformacaoAdicionalDaninhaDTO(0, "TestGroup");
        when(informacaoAdicionalDaninhaRepository.save(any(InformacaoAdicionalDaninha.class))).thenReturn(new InformacaoAdicionalDaninha());
        InformacaoAdicionalDaninhaDTO result = informacaoAdicionalDaninhaUseCase.insert(informacaoAdicionalDaninhaDTO);
        assertNotNull(result);

    }

}
