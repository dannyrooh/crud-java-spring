package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoLixiviacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoLixiviacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoLixiviacaoUseCaseImpl;
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
class TipoLixiviacaoUseCaseInsertTest {

    private TipoLixiviacaoRepository tipoLixiviacaoRepository;
    private TipoLixiviacaoUseCaseImpl tipoLixiviacaoUseCase;

    @BeforeEach
    void setUp() {
        tipoLixiviacaoRepository = mock(TipoLixiviacaoRepository.class);

        tipoLixiviacaoUseCase = new TipoLixiviacaoUseCaseImpl(tipoLixiviacaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> tipoLixiviacaoUseCase.insert(new TipoLixiviacaoDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoLixiviacaoUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> tipoLixiviacaoUseCase.insert(new TipoLixiviacaoDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoLixiviacao como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(tipoLixiviacaoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        TipoLixiviacaoDTO tipoLixiviacaoDTO = new TipoLixiviacaoDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> tipoLixiviacaoUseCase.insert(tipoLixiviacaoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        TipoLixiviacaoDTO tipoLixiviacaoDTO = new TipoLixiviacaoDTO(0, "TestGroup");
        when(tipoLixiviacaoRepository.save(any(TipoLixiviacao.class))).thenReturn(new TipoLixiviacao());
        TipoLixiviacaoDTO result = tipoLixiviacaoUseCase.insert(tipoLixiviacaoDTO);
        assertNotNull(result);

    }

}
