package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseToxicologia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ClasseToxicologiaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.ClasseToxicologiaUseCaseImpl;
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
class ClasseToxicologiaUseCaseInsertTest {

    private ClasseToxicologiaRepository classeToxicologiaRepository;
    private ClasseToxicologiaUseCaseImpl classeToxicologiaUseCase;

    @BeforeEach
    void setUp() {
        classeToxicologiaRepository = mock(ClasseToxicologiaRepository.class);

        classeToxicologiaUseCase = new ClasseToxicologiaUseCaseImpl(classeToxicologiaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testInsertWithEmptyNameException() throws WithNameEmptyException {
        assertThrows(WithNameEmptyException.class, () -> classeToxicologiaUseCase.insert(new ClasseToxicologiaDTO(1, "")));
    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testInsertIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> classeToxicologiaUseCase.insert(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testInsertWithNameMaxCharacterException() throws WithNameMaxSizeException {
        String longName = "a".repeat(51);
        assertThrows(WithNameMaxSizeException.class, () -> classeToxicologiaUseCase.insert(new ClasseToxicologiaDTO(0, longName)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um classeToxicologia como o nome informado")
    void testInsertWithNameAlreadInformedException() throws WithNameAlreadInformedException {

        when(classeToxicologiaRepository.existsByNomeIgnoreCase("TestGroup")).thenReturn(true);
        ClasseToxicologiaDTO classeToxicologiaDTO = new ClasseToxicologiaDTO(0, "TestGroup");
        assertThrows(WithNameAlreadInformedException.class,
                () -> classeToxicologiaUseCase.insert(classeToxicologiaDTO));
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testInsertWithNonEmptyName() throws ValidationException {
        ClasseToxicologiaDTO classeToxicologiaDTO = new ClasseToxicologiaDTO(0, "TestGroup");
        when(classeToxicologiaRepository.save(any(ClasseToxicologia.class))).thenReturn(new ClasseToxicologia());
        ClasseToxicologiaDTO result = classeToxicologiaUseCase.insert(classeToxicologiaDTO);
        assertNotNull(result);

    }

}
