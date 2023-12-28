package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoSoloUseCaseImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

@ExtendWith(MockitoExtension.class)
class TipoSoloUseCaseDeleteTest {

    private TipoSoloRepository tipoSoloRepository;
    private TipoSoloUseCaseImpl tipoSoloUseCase;

    @BeforeEach
    void setUp() {
        tipoSoloRepository = mock(TipoSoloRepository.class);
        tipoSoloUseCase = new TipoSoloUseCaseImpl(tipoSoloRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNot'InformedException quando o id for menor que zero")
    void testDeleteIdNotOrZeroInformedException() throws ValidationException {
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> tipoSoloUseCase.delete(0));
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> tipoSoloUseCase.delete(-1));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testDeleteIdNotFoundException() throws ValidationException {
        assertThrows(WithIdNotFoundException.class,
                () -> tipoSoloUseCase.delete(1));
    }

    @Test
    void testDeleted() throws ValidationException {

        doNothing().when(tipoSoloRepository).deleteById(1);
        when(tipoSoloRepository.existsById(1)).thenReturn(Boolean.valueOf(true));

        assertDoesNotThrow(

                () -> {
                    tipoSoloUseCase.insert(new TipoSoloDTO(1, "TestGroup First"));
                    tipoSoloUseCase.delete(1);
                });
    }
}
