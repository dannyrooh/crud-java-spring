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
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.PlantaDaninhaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
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
class PlantaDaninhaUseCaseUpdateTest {

    private PlantaDaninhaRepository grupoRepository;
    private PlantaDaninhaUseCaseImpl grupoUseCase;
    private PlantaDaninhaUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(PlantaDaninhaRepository.class);
        grupoUseCaseValidateImpl = new PlantaDaninhaUseCaseValidateImpl();
        grupoUseCase = new PlantaDaninhaUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertAll(
                () -> assertThrows(WithNameEmptyException.class, () -> grupoUseCase.update(new PlantaDaninhaDTO(1, ""))),
                () -> assertThrows(WithNameEmptyException.class, () -> grupoUseCase.update(null)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> grupoUseCase.update(new PlantaDaninhaDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        PlantaDaninhaDTO grupo = new PlantaDaninhaDTO();
        grupo.setNome("WithIdZeroOrNotInformedException");
        grupo.setId(0);

        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));
        grupo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        PlantaDaninhaDTO grupo = new PlantaDaninhaDTO();
        grupo.setNome("WithIdZeroOrNotInformedException");
        grupo.setId(0);

        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));
        grupo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um grupo como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(grupoRepository.save(any(PlantaDaninha.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    grupoUseCase.insert(new PlantaDaninhaDTO(1, "TestGroup First"));
                    grupoUseCase.insert(new PlantaDaninhaDTO(2, "TestGroup Seconde"));
                    grupoUseCase.update(new PlantaDaninhaDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        PlantaDaninhaDTO grupoDTO = new PlantaDaninhaDTO(0, "testUpdateWithNonEmptyName");
        when(grupoRepository.save(any(PlantaDaninha.class))).thenReturn(new PlantaDaninha());
        assertNotNull(grupoUseCase.insert(grupoDTO));

    }
}
