package com.dannyrooh.matrizinsumos.unitary.grupo.app.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.impl.GrupoUseCaseImpl;
import com.dannyrooh.matrizinsumos.grupo.domain.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.domain.repository.GrupoRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GrupoUseCaseTest {

    @Mock
    private GrupoRepository grupoRepository;

    @InjectMocks
    private GrupoUseCaseImpl grupoUseCase;

    @Test
    void testInsertWithEmptyName() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.insert(new GrupoDTO(0, "")));
    }

    @Test
    void testInsertWithNULL() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.insert(null));
    }

    @Test
    void testInsertWithNameMaxCharacter() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.insert(new GrupoDTO(0, "a".repeat(51))));
    }

    @Test
    void testInsertWithNonEmptyName() throws ValidationException {
        GrupoDTO grupoDTO = new GrupoDTO();
        grupoDTO.setNome("Test Group");
        when(grupoRepository.save(any(Grupo.class))).thenReturn(new Grupo());
        GrupoDTO result = grupoUseCase.insert(grupoDTO);
        assertNotNull(result);

    }
}
