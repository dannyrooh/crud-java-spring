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
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.InformacaoAdicionalMatrizUseCaseValidateImpl;
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
class InformacaoAdicionalMatrizUseCaseInsertTest {

    private InformacaoAdicionalMatrizRepository grupoRepository;
    private InformacaoAdicionalMatrizUseCaseImpl grupoUseCase;
    private InformacaoAdicionalMatrizUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(InformacaoAdicionalMatrizRepository.class);
        grupoUseCaseValidateImpl = new InformacaoAdicionalMatrizUseCaseValidateImpl();
        grupoUseCase = new InformacaoAdicionalMatrizUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
    }

    private void insertInformacaoAdicionalMatriz(InformacaoAdicionalMatrizDTO grupoDTO) {
        grupoUseCase.insert(grupoDTO);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertAll(
                () -> assertThrows(WithNameEmptyException.class, () -> insertInformacaoAdicionalMatriz(new InformacaoAdicionalMatrizDTO(1, ""))),
                () -> assertThrows(WithNameEmptyException.class, () -> insertInformacaoAdicionalMatriz(null)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> grupoUseCase.insert(new InformacaoAdicionalMatrizDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um grupo como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(grupoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        InformacaoAdicionalMatrizDTO grupoDTO = new InformacaoAdicionalMatrizDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> grupoUseCase.insert(grupoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        InformacaoAdicionalMatrizDTO grupoDTO = new InformacaoAdicionalMatrizDTO(0, "TestGroup");
        when(grupoRepository.save(any(InformacaoAdicionalMatriz.class))).thenReturn(new InformacaoAdicionalMatriz());
        InformacaoAdicionalMatrizDTO result = grupoUseCase.insert(grupoDTO);
        assertNotNull(result);

    }

}
