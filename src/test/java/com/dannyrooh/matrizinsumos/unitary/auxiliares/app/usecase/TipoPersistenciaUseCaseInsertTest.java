package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoPersistencia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoPersistenciaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoPersistenciaUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.TipoPersistenciaUseCaseValidateImpl;
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
class TipoPersistenciaUseCaseInsertTest {

    private TipoPersistenciaRepository grupoRepository;
    private TipoPersistenciaUseCaseImpl grupoUseCase;
    private TipoPersistenciaUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(TipoPersistenciaRepository.class);
        grupoUseCaseValidateImpl = new TipoPersistenciaUseCaseValidateImpl();
        grupoUseCase = new TipoPersistenciaUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
    }

    private void insertTipoPersistencia(TipoPersistenciaDTO grupoDTO) {
        grupoUseCase.insert(grupoDTO);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertAll(
                () -> assertThrows(WithNameEmptyException.class, () -> insertTipoPersistencia(new TipoPersistenciaDTO(1, ""))),
                () -> assertThrows(WithNameEmptyException.class, () -> insertTipoPersistencia(null)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> grupoUseCase.insert(new TipoPersistenciaDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um grupo como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(grupoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        TipoPersistenciaDTO grupoDTO = new TipoPersistenciaDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> grupoUseCase.insert(grupoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        TipoPersistenciaDTO grupoDTO = new TipoPersistenciaDTO(0, "TestGroup");
        when(grupoRepository.save(any(TipoPersistencia.class))).thenReturn(new TipoPersistencia());
        TipoPersistenciaDTO result = grupoUseCase.insert(grupoDTO);
        assertNotNull(result);

    }

}
