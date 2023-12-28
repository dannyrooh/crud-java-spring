package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.PlantaDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.PlantaDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.PlantaDaninhaUseCaseImpl;
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
class PlantaDaninhaUseCaseInsertTest {

    private PlantaDaninhaRepository plantaDaninhaRepository;
    private PlantaDaninhaUseCaseImpl plantaDaninhaUseCase;

    @BeforeEach
    void setUp() {
        plantaDaninhaRepository = mock(PlantaDaninhaRepository.class);

        plantaDaninhaUseCase = new PlantaDaninhaUseCaseImpl(plantaDaninhaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> plantaDaninhaUseCase.insert(new PlantaDaninhaDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> plantaDaninhaUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> plantaDaninhaUseCase.insert(new PlantaDaninhaDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um plantaDaninha como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(plantaDaninhaRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        PlantaDaninhaDTO plantaDaninhaDTO = new PlantaDaninhaDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> plantaDaninhaUseCase.insert(plantaDaninhaDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        PlantaDaninhaDTO plantaDaninhaDTO = new PlantaDaninhaDTO(0, "TestGroup");
        when(plantaDaninhaRepository.save(any(PlantaDaninha.class))).thenReturn(new PlantaDaninha());
        PlantaDaninhaDTO result = plantaDaninhaUseCase.insert(plantaDaninhaDTO);
        assertNotNull(result);

    }

}
