package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.InformacaoAdicionalDaninhaUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.InformacaoAdicionalDaninhaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

@ExtendWith(MockitoExtension.class)
class InformacaoAdicionalDaninhaUseCaseDeleteTest {

    private InformacaoAdicionalDaninhaRepository grupoRepository;
    private InformacaoAdicionalDaninhaUseCaseImpl grupoUseCase;
    private InformacaoAdicionalDaninhaUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(InformacaoAdicionalDaninhaRepository.class);
        grupoUseCaseValidateImpl = new InformacaoAdicionalDaninhaUseCaseValidateImpl();
        grupoUseCase = new InformacaoAdicionalDaninhaUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
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
                    grupoUseCase.insert(new InformacaoAdicionalDaninhaDTO(1, "TestGroup First"));
                    grupoUseCase.delete(1);
                });

    }

}
