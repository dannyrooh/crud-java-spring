package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoManifestacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoManifestacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoManifestacaoUseCaseImpl;
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
class TipoManifestacaoUseCaseInsertTest {

    private TipoManifestacaoRepository tipoManifestacaoRepository;
    private TipoManifestacaoUseCaseImpl tipoManifestacaoUseCase;

    @BeforeEach
    void setUp() {
        tipoManifestacaoRepository = mock(TipoManifestacaoRepository.class);

        tipoManifestacaoUseCase = new TipoManifestacaoUseCaseImpl(tipoManifestacaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> tipoManifestacaoUseCase.insert(new TipoManifestacaoDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoManifestacaoUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> tipoManifestacaoUseCase.insert(new TipoManifestacaoDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoManifestacao como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(tipoManifestacaoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        TipoManifestacaoDTO tipoManifestacaoDTO = new TipoManifestacaoDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> tipoManifestacaoUseCase.insert(tipoManifestacaoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        TipoManifestacaoDTO tipoManifestacaoDTO = new TipoManifestacaoDTO(0, "TestGroup");
        when(tipoManifestacaoRepository.save(any(TipoManifestacao.class))).thenReturn(new TipoManifestacao());
        TipoManifestacaoDTO result = tipoManifestacaoUseCase.insert(tipoManifestacaoDTO);
        assertNotNull(result);

    }

}
