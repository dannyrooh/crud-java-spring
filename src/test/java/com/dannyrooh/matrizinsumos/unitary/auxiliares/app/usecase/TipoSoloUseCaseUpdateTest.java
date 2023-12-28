package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoSoloUseCaseImpl;
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
class TipoSoloUseCaseUpdateTest {

    private TipoSoloRepository tipoSoloRepository;
    private TipoSoloUseCaseImpl tipoSoloUseCase;

    @BeforeEach
    void setUp() {
        tipoSoloRepository = mock(TipoSoloRepository.class);

        tipoSoloUseCase = new TipoSoloUseCaseImpl(tipoSoloRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> tipoSoloUseCase.update(new TipoSoloDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoSoloUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> tipoSoloUseCase.update(new TipoSoloDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        TipoSoloDTO tipoSolo = new TipoSoloDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoSoloUseCase.update(tipoSolo));
        tipoSolo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoSoloUseCase.update(tipoSolo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        TipoSoloDTO tipoSolo = new TipoSoloDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoSoloUseCase.update(tipoSolo));
        tipoSolo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoSoloUseCase.update(tipoSolo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoSolo como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(tipoSoloRepository.save(any(TipoSolo.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    tipoSoloUseCase.insert(new TipoSoloDTO(1, "TestGroup First"));
                    tipoSoloUseCase.insert(new TipoSoloDTO(2, "TestGroup Seconde"));
                    tipoSoloUseCase.update(new TipoSoloDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        TipoSoloDTO tipoSoloDTO = new TipoSoloDTO(0, "testUpdateWithNonEmptyName");
        when(tipoSoloRepository.save(any(TipoSolo.class))).thenReturn(new TipoSolo());
        assertNotNull(tipoSoloUseCase.insert(tipoSoloDTO));

    }
}
