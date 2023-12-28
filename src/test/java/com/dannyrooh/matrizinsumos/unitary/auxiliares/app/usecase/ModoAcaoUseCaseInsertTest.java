package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ModoAcao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ModoAcaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.ModoAcaoUseCaseImpl;
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
class ModoAcaoUseCaseInsertTest {

    private ModoAcaoRepository modoAcaoRepository;
    private ModoAcaoUseCaseImpl modoAcaoUseCase;

    @BeforeEach
    void setUp() {
        modoAcaoRepository = mock(ModoAcaoRepository.class);

        modoAcaoUseCase = new ModoAcaoUseCaseImpl(modoAcaoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> modoAcaoUseCase.insert(new ModoAcaoDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> modoAcaoUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> modoAcaoUseCase.insert(new ModoAcaoDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um modoAcao como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(modoAcaoRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        ModoAcaoDTO modoAcaoDTO = new ModoAcaoDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> modoAcaoUseCase.insert(modoAcaoDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        ModoAcaoDTO modoAcaoDTO = new ModoAcaoDTO(0, "TestGroup");
        when(modoAcaoRepository.save(any(ModoAcao.class))).thenReturn(new ModoAcao());
        ModoAcaoDTO result = modoAcaoUseCase.insert(modoAcaoDTO);
        assertNotNull(result);

    }

}
