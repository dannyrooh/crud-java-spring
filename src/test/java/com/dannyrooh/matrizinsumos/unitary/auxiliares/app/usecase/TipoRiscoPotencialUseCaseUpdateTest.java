package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoRiscoPotencial;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoRiscoPotencialRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoRiscoPotencialUseCaseImpl;
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
class TipoRiscoPotencialUseCaseUpdateTest {

    private TipoRiscoPotencialRepository tipoRiscoPotencialRepository;
    private TipoRiscoPotencialUseCaseImpl tipoRiscoPotencialUseCase;

    @BeforeEach
    void setUp() {
        tipoRiscoPotencialRepository = mock(TipoRiscoPotencialRepository.class);

        tipoRiscoPotencialUseCase = new TipoRiscoPotencialUseCaseImpl(tipoRiscoPotencialRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> tipoRiscoPotencialUseCase.update(new TipoRiscoPotencialDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoRiscoPotencialUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> tipoRiscoPotencialUseCase.update(new TipoRiscoPotencialDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        TipoRiscoPotencialDTO tipoRiscoPotencial = new TipoRiscoPotencialDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoRiscoPotencialUseCase.update(tipoRiscoPotencial));
        tipoRiscoPotencial.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoRiscoPotencialUseCase.update(tipoRiscoPotencial));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        TipoRiscoPotencialDTO tipoRiscoPotencial = new TipoRiscoPotencialDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoRiscoPotencialUseCase.update(tipoRiscoPotencial));
        tipoRiscoPotencial.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoRiscoPotencialUseCase.update(tipoRiscoPotencial));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoRiscoPotencial como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(tipoRiscoPotencialRepository.save(any(TipoRiscoPotencial.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    tipoRiscoPotencialUseCase.insert(new TipoRiscoPotencialDTO(1, "TestGroup First"));
                    tipoRiscoPotencialUseCase.insert(new TipoRiscoPotencialDTO(2, "TestGroup Seconde"));
                    tipoRiscoPotencialUseCase.update(new TipoRiscoPotencialDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        TipoRiscoPotencialDTO tipoRiscoPotencialDTO = new TipoRiscoPotencialDTO(0, "testUpdateWithNonEmptyName");
        when(tipoRiscoPotencialRepository.save(any(TipoRiscoPotencial.class))).thenReturn(new TipoRiscoPotencial());
        assertNotNull(tipoRiscoPotencialUseCase.insert(tipoRiscoPotencialDTO));

    }
}
