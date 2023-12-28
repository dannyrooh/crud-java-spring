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
class TipoPersistenciaUseCaseInsertTest {

    private TipoPersistenciaRepository tipoPersistenciaRepository;
    private TipoPersistenciaUseCaseImpl tipoPersistenciaUseCase;

    @BeforeEach
    void setUp() {
        tipoPersistenciaRepository = mock(TipoPersistenciaRepository.class);

        tipoPersistenciaUseCase = new TipoPersistenciaUseCaseImpl(tipoPersistenciaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> tipoPersistenciaUseCase.insert(new TipoPersistenciaDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoPersistenciaUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> tipoPersistenciaUseCase.insert(new TipoPersistenciaDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoPersistencia como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(tipoPersistenciaRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        TipoPersistenciaDTO tipoPersistenciaDTO = new TipoPersistenciaDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> tipoPersistenciaUseCase.insert(tipoPersistenciaDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        TipoPersistenciaDTO tipoPersistenciaDTO = new TipoPersistenciaDTO(0, "TestGroup");
        when(tipoPersistenciaRepository.save(any(TipoPersistencia.class))).thenReturn(new TipoPersistencia());
        TipoPersistenciaDTO result = tipoPersistenciaUseCase.insert(tipoPersistenciaDTO);
        assertNotNull(result);

    }

}
