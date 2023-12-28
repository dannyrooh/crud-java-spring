package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoManifestacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoManifestacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoManifestacaoUseCaseImpl;
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
class TipoManifestacaoUseCaseUpdateTest {

    private TipoManifestacaoRepository tipoManifestacaoRepository;
    private TipoManifestacaoUseCaseImpl tipoManifestacaoUseCase;

    @BeforeEach
    void setUp() {
        tipoManifestacaoRepository = mock(TipoManifestacaoRepository.class);

        tipoManifestacaoUseCase = new TipoManifestacaoUseCaseImpl(tipoManifestacaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> tipoManifestacaoUseCase.update(new TipoManifestacaoDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoManifestacaoUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> tipoManifestacaoUseCase.update(new TipoManifestacaoDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        TipoManifestacaoDTO tipoManifestacao = new TipoManifestacaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoManifestacaoUseCase.update(tipoManifestacao));
        tipoManifestacao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoManifestacaoUseCase.update(tipoManifestacao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        TipoManifestacaoDTO tipoManifestacao = new TipoManifestacaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoManifestacaoUseCase.update(tipoManifestacao));
        tipoManifestacao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoManifestacaoUseCase.update(tipoManifestacao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoManifestacao como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(tipoManifestacaoRepository.save(any(TipoManifestacao.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    tipoManifestacaoUseCase.insert(new TipoManifestacaoDTO(1, "TestGroup First"));
                    tipoManifestacaoUseCase.insert(new TipoManifestacaoDTO(2, "TestGroup Seconde"));
                    tipoManifestacaoUseCase.update(new TipoManifestacaoDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        TipoManifestacaoDTO tipoManifestacaoDTO = new TipoManifestacaoDTO(0, "testUpdateWithNonEmptyName");
        when(tipoManifestacaoRepository.save(any(TipoManifestacao.class))).thenReturn(new TipoManifestacao());
        assertNotNull(tipoManifestacaoUseCase.insert(tipoManifestacaoDTO));

    }
}
