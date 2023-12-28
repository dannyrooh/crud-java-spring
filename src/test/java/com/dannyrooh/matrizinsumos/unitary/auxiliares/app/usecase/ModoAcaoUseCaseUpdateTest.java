package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ModoAcao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ModoAcaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.ModoAcaoUseCaseImpl;
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
class ModoAcaoUseCaseUpdateTest {

    private ModoAcaoRepository modoAcaoRepository;
    private ModoAcaoUseCaseImpl modoAcaoUseCase;

    @BeforeEach
    void setUp() {
        modoAcaoRepository = mock(ModoAcaoRepository.class);

        modoAcaoUseCase = new ModoAcaoUseCaseImpl(modoAcaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> modoAcaoUseCase.update(new ModoAcaoDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> modoAcaoUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> modoAcaoUseCase.update(new ModoAcaoDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        ModoAcaoDTO modoAcao = new ModoAcaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> modoAcaoUseCase.update(modoAcao));
        modoAcao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> modoAcaoUseCase.update(modoAcao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        ModoAcaoDTO modoAcao = new ModoAcaoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> modoAcaoUseCase.update(modoAcao));
        modoAcao.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> modoAcaoUseCase.update(modoAcao));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um modoAcao como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(modoAcaoRepository.save(any(ModoAcao.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    modoAcaoUseCase.insert(new ModoAcaoDTO(1, "TestGroup First"));
                    modoAcaoUseCase.insert(new ModoAcaoDTO(2, "TestGroup Seconde"));
                    modoAcaoUseCase.update(new ModoAcaoDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        ModoAcaoDTO modoAcaoDTO = new ModoAcaoDTO(0, "testUpdateWithNonEmptyName");
        when(modoAcaoRepository.save(any(ModoAcao.class))).thenReturn(new ModoAcao());
        assertNotNull(modoAcaoUseCase.insert(modoAcaoDTO));

    }
}
