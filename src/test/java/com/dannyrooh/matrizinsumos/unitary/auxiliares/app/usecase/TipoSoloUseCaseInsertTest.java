package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoSoloUseCaseImpl;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("teste")
class TipoSoloUseCaseInsertTest {

    private TipoSoloRepository tipoSoloRepository;
    private TipoSoloUseCaseImpl tipoSoloUseCase;

    @BeforeEach
    void setUp() {
        tipoSoloRepository = mock(TipoSoloRepository.class);

        tipoSoloUseCase = new TipoSoloUseCaseImpl(tipoSoloRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> tipoSoloUseCase.insert(new TipoSoloDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoSoloUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> tipoSoloUseCase.insert(new TipoSoloDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoSolo como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(tipoSoloRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        TipoSoloDTO tipoSoloDTO = new TipoSoloDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> tipoSoloUseCase.insert(tipoSoloDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        TipoSoloDTO tipoSoloDTO = new TipoSoloDTO(0, "TestGroup");
        when(tipoSoloRepository.save(any(TipoSolo.class))).thenReturn(new TipoSolo());
        TipoSoloDTO result = tipoSoloUseCase.insert(tipoSoloDTO);
        assertNotNull(result);

    }

}
