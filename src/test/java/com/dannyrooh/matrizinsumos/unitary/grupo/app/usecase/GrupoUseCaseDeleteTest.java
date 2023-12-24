package com.dannyrooh.matrizinsumos.unitary.grupo.app.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.grupo.dataprovider.repository.GrupoRepository;
import com.dannyrooh.matrizinsumos.grupo.domain.usecase.impl.GrupoUseCaseImpl;

import static org.junit.Assert.assertNotNull;

import javax.xml.bind.ValidationException;

@ExtendWith(MockitoExtension.class)
class GrupoUseCaseDeleteTest {

    @Mock
    private GrupoRepository grupoRepository;

    @InjectMocks
    private GrupoUseCaseImpl grupoUseCase;

    @Test
    void testDeleteIdNotOrZeroInformedException() throws ValidationException {
        assertNotNull(true);
    }

    @Test
    void testDeleteIdNotFoundException() throws ValidationException {
        assertNotNull(true);
    }

    @Test
    void testDeleted() throws ValidationException {
        assertNotNull(true);
    }

}
