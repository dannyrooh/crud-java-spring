package com.dannyrooh.matrizinsumos.unitary.grupo.app.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;
import com.dannyrooh.matrizinsumos.grupo.dataprovider.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.dataprovider.repository.GrupoRepository;
import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.domain.usecase.impl.GrupoUseCaseImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("teste")

class GrupoUseCaseInsertTest {

    @Mock
    private GrupoRepository grupoRepository;

    @InjectMocks
    private GrupoUseCaseImpl grupoUseCase;

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco")
    void testInsertWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class,
                () -> grupoUseCase.insert(new GrupoDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception NullPointerException quando o objeto não for informado")
    void testInsertWithNULLException() throws ValidationException {
        assertThrows(NullPointerException.class,
                () -> grupoUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> grupoUseCase.insert(new GrupoDTO(0, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um grupo como o nome informado")
    void testInsertWithNameAlreadInformedException() throws ValidationException {

        when(grupoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        GrupoDTO grupoDTO = new GrupoDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> grupoUseCase.insert(grupoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        GrupoDTO grupoDTO = new GrupoDTO(0, "TestGroup");
        when(grupoRepository.save(any(Grupo.class))).thenReturn(new Grupo());
        GrupoDTO result = grupoUseCase.insert(grupoDTO);
        assertNotNull(result);

    }
}
