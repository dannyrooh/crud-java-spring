package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Atuacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.AtuacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.AtuacaoUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.AtuacaoUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("teste")
class AtuacaoUseCaseInsertTest {

    private AtuacaoRepository grupoRepository;
    private AtuacaoUseCaseImpl grupoUseCase;
    private AtuacaoUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(AtuacaoRepository.class);
        grupoUseCaseValidateImpl = new AtuacaoUseCaseValidateImpl();
        grupoUseCase = new AtuacaoUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
    }

    private void insertAtuacao(AtuacaoDTO grupoDTO) {
        grupoUseCase.insert(grupoDTO);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertAll(
                () -> assertThrows(WithNameEmptyException.class, () -> insertAtuacao(new AtuacaoDTO(1, ""))),
                () -> assertThrows(WithNameEmptyException.class, () -> insertAtuacao(null)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> grupoUseCase.insert(new AtuacaoDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um grupo como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(grupoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        AtuacaoDTO grupoDTO = new AtuacaoDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> grupoUseCase.insert(grupoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        AtuacaoDTO grupoDTO = new AtuacaoDTO(0, "TestGroup");
        when(grupoRepository.save(any(Atuacao.class))).thenReturn(new Atuacao());
        AtuacaoDTO result = grupoUseCase.insert(grupoDTO);
        assertNotNull(result);

    }

}
