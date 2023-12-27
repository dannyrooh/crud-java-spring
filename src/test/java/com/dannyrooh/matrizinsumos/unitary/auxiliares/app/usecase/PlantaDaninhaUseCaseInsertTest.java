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
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.PlantaDaninhaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("teste")
class PlantaDaninhaUseCaseInsertTest {

    private PlantaDaninhaRepository grupoRepository;
    private PlantaDaninhaUseCaseImpl grupoUseCase;
    private PlantaDaninhaUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(PlantaDaninhaRepository.class);
        grupoUseCaseValidateImpl = new PlantaDaninhaUseCaseValidateImpl();
        grupoUseCase = new PlantaDaninhaUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
    }

    private void insertPlantaDaninha(PlantaDaninhaDTO grupoDTO) {
        grupoUseCase.insert(grupoDTO);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertAll(
                () -> assertThrows(WithNameEmptyException.class, () -> insertPlantaDaninha(new PlantaDaninhaDTO(1, ""))),
                () -> assertThrows(WithNameEmptyException.class, () -> insertPlantaDaninha(null)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> grupoUseCase.insert(new PlantaDaninhaDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um grupo como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(grupoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        PlantaDaninhaDTO grupoDTO = new PlantaDaninhaDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> grupoUseCase.insert(grupoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        PlantaDaninhaDTO grupoDTO = new PlantaDaninhaDTO(0, "TestGroup");
        when(grupoRepository.save(any(PlantaDaninha.class))).thenReturn(new PlantaDaninha());
        PlantaDaninhaDTO result = grupoUseCase.insert(grupoDTO);
        assertNotNull(result);

    }

}
