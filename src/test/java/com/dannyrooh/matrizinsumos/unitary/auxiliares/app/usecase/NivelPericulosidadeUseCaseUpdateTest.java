package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.NivelPericulosidade;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.NivelPericulosidadeRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.NivelPericulosidadeUseCaseImpl;
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
class NivelPericulosidadeUseCaseUpdateTest {

    private NivelPericulosidadeRepository nivelPericulosidadeRepository;
    private NivelPericulosidadeUseCaseImpl nivelPericulosidadeUseCase;

    @BeforeEach
    void setUp() {
        nivelPericulosidadeRepository = mock(NivelPericulosidadeRepository.class);

        nivelPericulosidadeUseCase = new NivelPericulosidadeUseCaseImpl(nivelPericulosidadeRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> nivelPericulosidadeUseCase.update(new NivelPericulosidadeDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> nivelPericulosidadeUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> nivelPericulosidadeUseCase.update(new NivelPericulosidadeDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        NivelPericulosidadeDTO nivelPericulosidade = new NivelPericulosidadeDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> nivelPericulosidadeUseCase.update(nivelPericulosidade));
        nivelPericulosidade.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> nivelPericulosidadeUseCase.update(nivelPericulosidade));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        NivelPericulosidadeDTO nivelPericulosidade = new NivelPericulosidadeDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> nivelPericulosidadeUseCase.update(nivelPericulosidade));
        nivelPericulosidade.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> nivelPericulosidadeUseCase.update(nivelPericulosidade));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um nivelPericulosidade como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(nivelPericulosidadeRepository.save(any(NivelPericulosidade.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    nivelPericulosidadeUseCase.insert(new NivelPericulosidadeDTO(1, "TestGroup First"));
                    nivelPericulosidadeUseCase.insert(new NivelPericulosidadeDTO(2, "TestGroup Seconde"));
                    nivelPericulosidadeUseCase.update(new NivelPericulosidadeDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        NivelPericulosidadeDTO nivelPericulosidadeDTO = new NivelPericulosidadeDTO(0, "testUpdateWithNonEmptyName");
        when(nivelPericulosidadeRepository.save(any(NivelPericulosidade.class))).thenReturn(new NivelPericulosidade());
        assertNotNull(nivelPericulosidadeUseCase.insert(nivelPericulosidadeDTO));

    }
}
