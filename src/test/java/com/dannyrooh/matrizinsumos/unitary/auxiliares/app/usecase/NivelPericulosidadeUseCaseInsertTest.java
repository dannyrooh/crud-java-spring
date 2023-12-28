package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.NivelPericulosidade;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.NivelPericulosidadeRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.NivelPericulosidadeUseCaseImpl;
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
class NivelPericulosidadeUseCaseInsertTest {

    private NivelPericulosidadeRepository nivelPericulosidadeRepository;
    private NivelPericulosidadeUseCaseImpl nivelPericulosidadeUseCase;

    @BeforeEach
    void setUp() {
        nivelPericulosidadeRepository = mock(NivelPericulosidadeRepository.class);

        nivelPericulosidadeUseCase = new NivelPericulosidadeUseCaseImpl(nivelPericulosidadeRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> nivelPericulosidadeUseCase.insert(new NivelPericulosidadeDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> nivelPericulosidadeUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> nivelPericulosidadeUseCase.insert(new NivelPericulosidadeDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um nivelPericulosidade como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(nivelPericulosidadeRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        NivelPericulosidadeDTO nivelPericulosidadeDTO = new NivelPericulosidadeDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> nivelPericulosidadeUseCase.insert(nivelPericulosidadeDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        NivelPericulosidadeDTO nivelPericulosidadeDTO = new NivelPericulosidadeDTO(0, "TestGroup");
        when(nivelPericulosidadeRepository.save(any(NivelPericulosidade.class))).thenReturn(new NivelPericulosidade());
        NivelPericulosidadeDTO result = nivelPericulosidadeUseCase.insert(nivelPericulosidadeDTO);
        assertNotNull(result);

    }

}
