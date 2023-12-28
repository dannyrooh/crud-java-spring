package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoRiscoPotencial;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoRiscoPotencialRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoRiscoPotencialUseCaseImpl;
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
class TipoRiscoPotencialUseCaseInsertTest {

    private TipoRiscoPotencialRepository tipoRiscoPotencialRepository;
    private TipoRiscoPotencialUseCaseImpl tipoRiscoPotencialUseCase;

    @BeforeEach
    void setUp() {
        tipoRiscoPotencialRepository = mock(TipoRiscoPotencialRepository.class);

        tipoRiscoPotencialUseCase = new TipoRiscoPotencialUseCaseImpl(tipoRiscoPotencialRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> tipoRiscoPotencialUseCase.insert(new TipoRiscoPotencialDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoRiscoPotencialUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> tipoRiscoPotencialUseCase.insert(new TipoRiscoPotencialDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoRiscoPotencial como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(tipoRiscoPotencialRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        TipoRiscoPotencialDTO tipoRiscoPotencialDTO = new TipoRiscoPotencialDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> tipoRiscoPotencialUseCase.insert(tipoRiscoPotencialDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        TipoRiscoPotencialDTO tipoRiscoPotencialDTO = new TipoRiscoPotencialDTO(0, "TestGroup");
        when(tipoRiscoPotencialRepository.save(any(TipoRiscoPotencial.class))).thenReturn(new TipoRiscoPotencial());
        TipoRiscoPotencialDTO result = tipoRiscoPotencialUseCase.insert(tipoRiscoPotencialDTO);
        assertNotNull(result);

    }

}
