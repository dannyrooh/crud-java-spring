package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.EpocaAplicacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.EpocaAplicacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.EpocaAplicacaoUseCaseImpl;
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
class EpocaAplicacaoUseCaseInsertTest {

    private EpocaAplicacaoRepository epocaAplicacaoRepository;
    private EpocaAplicacaoUseCaseImpl epocaAplicacaoUseCase;

    @BeforeEach
    void setUp() {
        epocaAplicacaoRepository = mock(EpocaAplicacaoRepository.class);

        epocaAplicacaoUseCase = new EpocaAplicacaoUseCaseImpl(epocaAplicacaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> epocaAplicacaoUseCase.insert(new EpocaAplicacaoDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> epocaAplicacaoUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> epocaAplicacaoUseCase.insert(new EpocaAplicacaoDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um epocaAplicacao como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(epocaAplicacaoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        EpocaAplicacaoDTO epocaAplicacaoDTO = new EpocaAplicacaoDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> epocaAplicacaoUseCase.insert(epocaAplicacaoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        EpocaAplicacaoDTO epocaAplicacaoDTO = new EpocaAplicacaoDTO(0, "TestGroup");
        when(epocaAplicacaoRepository.save(any(EpocaAplicacao.class))).thenReturn(new EpocaAplicacao());
        EpocaAplicacaoDTO result = epocaAplicacaoUseCase.insert(epocaAplicacaoDTO);
        assertNotNull(result);

    }

}
