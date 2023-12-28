package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoLixiviacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoLixiviacaoUseCaseImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

@ExtendWith(MockitoExtension.class)
class TipoLixiviacaoUseCaseDeleteTest {

    private TipoLixiviacaoRepository tipoLixiviacaoRepository;
    private TipoLixiviacaoUseCaseImpl tipoLixiviacaoUseCase;

    @BeforeEach
    void setUp() {
        tipoLixiviacaoRepository = mock(TipoLixiviacaoRepository.class);
        tipoLixiviacaoUseCase = new TipoLixiviacaoUseCaseImpl(tipoLixiviacaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNot'InformedException quando o id for menor que zero")
    void testDeleteIdNotOrZeroInformedException() throws ValidationException {
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> tipoLixiviacaoUseCase.delete(0));
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> tipoLixiviacaoUseCase.delete(-1));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testDeleteIdNotFoundException() throws ValidationException {
        assertThrows(WithIdNotFoundException.class,
                () -> tipoLixiviacaoUseCase.delete(1));
    }

    @Test
    void testDeleted() throws ValidationException {

        doNothing().when(tipoLixiviacaoRepository).deleteById(1);
        when(tipoLixiviacaoRepository.existsById(1)).thenReturn(Boolean.valueOf(true));

        assertDoesNotThrow(

                () -> {
                    tipoLixiviacaoUseCase.insert(new TipoLixiviacaoDTO(1, "TestGroup First"));
                    tipoLixiviacaoUseCase.delete(1);
                });
    }
}
