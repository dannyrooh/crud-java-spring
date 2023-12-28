package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseToxicologia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ClasseToxicologiaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.ClasseToxicologiaUseCaseImpl;
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
class ClasseToxicologiaUseCaseUpdateTest {

    private ClasseToxicologiaRepository classeToxicologiaRepository;
    private ClasseToxicologiaUseCaseImpl classeToxicologiaUseCase;

    @BeforeEach
    void setUp() {
        classeToxicologiaRepository = mock(ClasseToxicologiaRepository.class);

        classeToxicologiaUseCase = new ClasseToxicologiaUseCaseImpl(classeToxicologiaRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> classeToxicologiaUseCase.update(new ClasseToxicologiaDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> classeToxicologiaUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> classeToxicologiaUseCase.update(new ClasseToxicologiaDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        ClasseToxicologiaDTO classeToxicologia = new ClasseToxicologiaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeToxicologiaUseCase.update(classeToxicologia));
        classeToxicologia.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeToxicologiaUseCase.update(classeToxicologia));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        ClasseToxicologiaDTO classeToxicologia = new ClasseToxicologiaDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeToxicologiaUseCase.update(classeToxicologia));
        classeToxicologia.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeToxicologiaUseCase.update(classeToxicologia));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um classeToxicologia como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(classeToxicologiaRepository.save(any(ClasseToxicologia.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    classeToxicologiaUseCase.insert(new ClasseToxicologiaDTO(1, "TestGroup First"));
                    classeToxicologiaUseCase.insert(new ClasseToxicologiaDTO(2, "TestGroup Seconde"));
                    classeToxicologiaUseCase.update(new ClasseToxicologiaDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        ClasseToxicologiaDTO classeToxicologiaDTO = new ClasseToxicologiaDTO(0, "testUpdateWithNonEmptyName");
        when(classeToxicologiaRepository.save(any(ClasseToxicologia.class))).thenReturn(new ClasseToxicologia());
        assertNotNull(classeToxicologiaUseCase.insert(classeToxicologiaDTO));

    }
}
