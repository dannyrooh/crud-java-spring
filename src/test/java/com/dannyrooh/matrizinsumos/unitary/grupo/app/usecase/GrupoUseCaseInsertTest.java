package com.dannyrooh.matrizinsumos.unitary.grupo.app.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.impl.GrupoUseCaseImpl;
import com.dannyrooh.matrizinsumos.grupo.domain.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.domain.repository.GrupoRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("teste")

class GrupoUseCaseInsertTest {

    @Mock
    private GrupoRepository grupoRepository;

    @InjectMocks
    private GrupoUseCaseImpl grupoUseCase;

    @Test
    @DisplayName("Deve gerar uma exception quando o nome estiver em branco")
    void testInsertWithEmptyName() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.insert(new GrupoDTO(0, "")));
    }

    @Test
    @DisplayName("Deve gerar uma exception quando o objeto não for informado")
    void testInsertWithNULL() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar uma exception quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacter() throws ValidationException {
        assertThrows(ValidationException.class,
                () -> grupoUseCase.insert(new GrupoDTO(0, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar uma exception caso já exista um grupo como o nome informado")
    void testInsertViolationUK() throws ValidationException {
        assertTrue(false);
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e rotornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        GrupoDTO grupoDTO = new GrupoDTO();
        grupoDTO.setNome("Test Group");
        when(grupoRepository.save(any(Grupo.class))).thenReturn(new Grupo());
        GrupoDTO result = grupoUseCase.insert(grupoDTO);
        assertNotNull(result);

    }
}
