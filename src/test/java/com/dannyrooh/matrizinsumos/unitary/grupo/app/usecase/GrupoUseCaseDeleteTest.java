package com.dannyrooh.matrizinsumos.unitary.grupo.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.grupo.dataprovider.repository.GrupoRepository;
import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.domain.usecase.impl.GrupoUseCaseImpl;
import com.dannyrooh.matrizinsumos.grupo.domain.validate.impl.GrupoUseCaseValidateImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

@ExtendWith(MockitoExtension.class)
class GrupoUseCaseDeleteTest {

    private GrupoRepository grupoRepository;
    private GrupoUseCaseImpl grupoUseCase;
    private GrupoUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(GrupoRepository.class);
        grupoUseCaseValidateImpl = new GrupoUseCaseValidateImpl();
        grupoUseCase = new GrupoUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNot'InformedException quando o id for menor que zero")
    void testDeleteIdNotOrZeroInformedException() throws ValidationException {
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> grupoUseCase.delete(0));
        assertThrows(WithIdZeroOrNotInformedException.class,
                () -> grupoUseCase.delete(-1));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testDeleteIdNotFoundException() throws ValidationException {
        assertThrows(WithIdNotFoundException.class,
                () -> grupoUseCase.delete(1));
    }

    @Test
    void testDeleted() {

        doNothing().when(grupoRepository).deleteById(1);
        when(grupoRepository.existsById(1)).thenReturn(Boolean.valueOf(true));

        assertDoesNotThrow(

                () -> {
                    grupoUseCase.insert(new GrupoDTO(1, "TestGroup First"));
                    grupoUseCase.delete(1);
                });

    }

}
