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
class GrupoUseCaseUpdateTest {

    @Mock
    private GrupoRepository grupoRepository;

    @InjectMocks
    private GrupoUseCaseImpl grupoUseCase;

    @Test
    void testUpdateWithEmptyName() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.update(new GrupoDTO(0, "")));
    }

    @Test
    void testUpdateWithNULL() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.update(null));
    }

    @Test
    void testUpdateWithNameMaxCharacter() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.update(new GrupoDTO(0, "a".repeat(51))));
    }

    @Test
    void testUpdateWithNonEmptyName() throws ValidationException {
        GrupoDTO grupoDTO = new GrupoDTO();
        grupoDTO.setNome("Test Group");
        when(grupoRepository.save(any(Grupo.class))).thenReturn(new Grupo());
        GrupoDTO result = grupoUseCase.update(grupoDTO);
        assertNotNull(result);

    }
}
