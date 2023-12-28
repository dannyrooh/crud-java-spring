package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.PlantaDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.PlantaDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.PlantaDaninhaUseCaseImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
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
class PlantaDaninhaUseCaseUpdateTest {

    private PlantaDaninhaRepository plantaDaninhaRepository;
    private PlantaDaninhaUseCaseImpl plantaDaninhaUseCase;

    @BeforeEach
    void setUp() {
        plantaDaninhaRepository = mock(PlantaDaninhaRepository.class);

        plantaDaninhaUseCase = new PlantaDaninhaUseCaseImpl(plantaDaninhaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> plantaDaninhaUseCase.update(new PlantaDaninhaDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> plantaDaninhaUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> plantaDaninhaUseCase.update(new PlantaDaninhaDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        PlantaDaninhaDTO plantaDaninha = new PlantaDaninhaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> plantaDaninhaUseCase.update(plantaDaninha));
        plantaDaninha.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> plantaDaninhaUseCase.update(plantaDaninha));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        PlantaDaninhaDTO plantaDaninha = new PlantaDaninhaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> plantaDaninhaUseCase.update(plantaDaninha));
        plantaDaninha.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> plantaDaninhaUseCase.update(plantaDaninha));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um plantaDaninha como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(plantaDaninhaRepository.save(any(PlantaDaninha.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    plantaDaninhaUseCase.insert(new PlantaDaninhaDTO(1, "TestGroup First"));
                    plantaDaninhaUseCase.insert(new PlantaDaninhaDTO(2, "TestGroup Seconde"));
                    plantaDaninhaUseCase.update(new PlantaDaninhaDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        PlantaDaninhaDTO plantaDaninhaDTO = new PlantaDaninhaDTO(0, "testUpdateWithNonEmptyName");
        when(plantaDaninhaRepository.save(any(PlantaDaninha.class))).thenReturn(new PlantaDaninha());
        assertNotNull(plantaDaninhaUseCase.insert(plantaDaninhaDTO));

    }
}
