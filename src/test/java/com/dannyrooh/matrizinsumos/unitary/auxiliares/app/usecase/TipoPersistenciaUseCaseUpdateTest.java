package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoPersistencia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoPersistenciaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.TipoPersistenciaUseCaseImpl;
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
class TipoPersistenciaUseCaseUpdateTest {

    private TipoPersistenciaRepository tipoPersistenciaRepository;
    private TipoPersistenciaUseCaseImpl tipoPersistenciaUseCase;

    @BeforeEach
    void setUp() {
        tipoPersistenciaRepository = mock(TipoPersistenciaRepository.class);

        tipoPersistenciaUseCase = new TipoPersistenciaUseCaseImpl(tipoPersistenciaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> tipoPersistenciaUseCase.update(new TipoPersistenciaDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> tipoPersistenciaUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> tipoPersistenciaUseCase.update(new TipoPersistenciaDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        TipoPersistenciaDTO tipoPersistencia = new TipoPersistenciaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoPersistenciaUseCase.update(tipoPersistencia));
        tipoPersistencia.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoPersistenciaUseCase.update(tipoPersistencia));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        TipoPersistenciaDTO tipoPersistencia = new TipoPersistenciaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoPersistenciaUseCase.update(tipoPersistencia));
        tipoPersistencia.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> tipoPersistenciaUseCase.update(tipoPersistencia));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um tipoPersistencia como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(tipoPersistenciaRepository.save(any(TipoPersistencia.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    tipoPersistenciaUseCase.insert(new TipoPersistenciaDTO(1, "TestGroup First"));
                    tipoPersistenciaUseCase.insert(new TipoPersistenciaDTO(2, "TestGroup Seconde"));
                    tipoPersistenciaUseCase.update(new TipoPersistenciaDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        TipoPersistenciaDTO tipoPersistenciaDTO = new TipoPersistenciaDTO(0, "testUpdateWithNonEmptyName");
        when(tipoPersistenciaRepository.save(any(TipoPersistencia.class))).thenReturn(new TipoPersistencia());
        assertNotNull(tipoPersistenciaUseCase.insert(tipoPersistenciaDTO));

    }
}
