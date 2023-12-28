package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoInfluenciaSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoInfluenciaSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoInfluenciaSoloUseCaseImpl;
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
class TipoInfluenciaSoloUseCaseInsertTest {

    private TipoInfluenciaSoloRepository tipoInfluenciaSoloRepository;
    private TipoInfluenciaSoloUseCaseImpl tipoInfluenciaSoloUseCase;

    @BeforeEach
    void setUp() {
        tipoInfluenciaSoloRepository = mock(TipoInfluenciaSoloRepository.class);

        tipoInfluenciaSoloUseCase = new TipoInfluenciaSoloUseCaseImpl(tipoInfluenciaSoloRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> tipoInfluenciaSoloUseCase.insert(new TipoInfluenciaSoloDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoInfluenciaSoloUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> tipoInfluenciaSoloUseCase.insert(new TipoInfluenciaSoloDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoInfluenciaSolo como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(tipoInfluenciaSoloRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTO = new TipoInfluenciaSoloDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> tipoInfluenciaSoloUseCase.insert(tipoInfluenciaSoloDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTO = new TipoInfluenciaSoloDTO(0, "TestGroup");
        when(tipoInfluenciaSoloRepository.save(any(TipoInfluenciaSolo.class))).thenReturn(new TipoInfluenciaSolo());
        TipoInfluenciaSoloDTO result = tipoInfluenciaSoloUseCase.insert(tipoInfluenciaSoloDTO);
        assertNotNull(result);

    }

}
