package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoInfluenciaSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoInfluenciaSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoInfluenciaSoloUseCaseImpl;
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
class TipoInfluenciaSoloUseCaseUpdateTest {

    private TipoInfluenciaSoloRepository tipoInfluenciaSoloRepository;
    private TipoInfluenciaSoloUseCaseImpl tipoInfluenciaSoloUseCase;

    @BeforeEach
    void setUp() {
        tipoInfluenciaSoloRepository = mock(TipoInfluenciaSoloRepository.class);

        tipoInfluenciaSoloUseCase = new TipoInfluenciaSoloUseCaseImpl(tipoInfluenciaSoloRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> tipoInfluenciaSoloUseCase.update(new TipoInfluenciaSoloDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoInfluenciaSoloUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> tipoInfluenciaSoloUseCase.update(new TipoInfluenciaSoloDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        TipoInfluenciaSoloDTO tipoInfluenciaSolo = new TipoInfluenciaSoloDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoInfluenciaSoloUseCase.update(tipoInfluenciaSolo));
        tipoInfluenciaSolo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoInfluenciaSoloUseCase.update(tipoInfluenciaSolo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        TipoInfluenciaSoloDTO tipoInfluenciaSolo = new TipoInfluenciaSoloDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoInfluenciaSoloUseCase.update(tipoInfluenciaSolo));
        tipoInfluenciaSolo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoInfluenciaSoloUseCase.update(tipoInfluenciaSolo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoInfluenciaSolo como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(tipoInfluenciaSoloRepository.save(any(TipoInfluenciaSolo.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    tipoInfluenciaSoloUseCase.insert(new TipoInfluenciaSoloDTO(1, "TestGroup First"));
                    tipoInfluenciaSoloUseCase.insert(new TipoInfluenciaSoloDTO(2, "TestGroup Seconde"));
                    tipoInfluenciaSoloUseCase.update(new TipoInfluenciaSoloDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTO = new TipoInfluenciaSoloDTO(0, "testUpdateWithNonEmptyName");
        when(tipoInfluenciaSoloRepository.save(any(TipoInfluenciaSolo.class))).thenReturn(new TipoInfluenciaSolo());
        assertNotNull(tipoInfluenciaSoloUseCase.insert(tipoInfluenciaSoloDTO));

    }
}
