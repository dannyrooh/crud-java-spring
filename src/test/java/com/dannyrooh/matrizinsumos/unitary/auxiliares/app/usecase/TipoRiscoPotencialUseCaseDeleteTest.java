package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoRiscoPotencialRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoRiscoPotencialUseCaseImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

@ExtendWith(MockitoExtension.class)
class TipoRiscoPotencialUseCaseDeleteTest {

    private TipoRiscoPotencialRepository tipoRiscoPotencialRepository;
    private TipoRiscoPotencialUseCaseImpl tipoRiscoPotencialUseCase;

    @BeforeEach
    void setUp() {
        tipoRiscoPotencialRepository = mock(TipoRiscoPotencialRepository.class);
        tipoRiscoPotencialUseCase = new TipoRiscoPotencialUseCaseImpl(tipoRiscoPotencialRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNot'InformedException quando o id for menor que zero")
    void testDeleteIdNotOrZeroInformedException() throws ValidationException {
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> tipoRiscoPotencialUseCase.delete(0));
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> tipoRiscoPotencialUseCase.delete(-1));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testDeleteIdNotFoundException() throws ValidationException {
        assertThrows(WithIdNotFoundException.class,
                () -> tipoRiscoPotencialUseCase.delete(1));
    }

    @Test
    void testDeleted() throws ValidationException {

        doNothing().when(tipoRiscoPotencialRepository).deleteById(1);
        when(tipoRiscoPotencialRepository.existsById(1)).thenReturn(Boolean.valueOf(true));

        assertDoesNotThrow(

                () -> {
                    tipoRiscoPotencialUseCase.insert(new TipoRiscoPotencialDTO(1, "TestGroup First"));
                    tipoRiscoPotencialUseCase.delete(1);
                });
    }
}
