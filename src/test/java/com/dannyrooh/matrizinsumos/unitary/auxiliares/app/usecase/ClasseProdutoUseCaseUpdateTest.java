package com.dannyrooh.matrizinsumos.unitary.auxiliares.app.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseProduto;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ClasseProdutoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl.ClasseProdutoUseCaseImpl;
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
class ClasseProdutoUseCaseUpdateTest {

    private ClasseProdutoRepository classeProdutoRepository;
    private ClasseProdutoUseCaseImpl classeProdutoUseCase;

    @BeforeEach
    void setUp() {
        classeProdutoRepository = mock(ClasseProdutoRepository.class);

        classeProdutoUseCase = new ClasseProdutoUseCaseImpl(classeProdutoRepository);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertThrows(WithNameEmptyException.class, () -> classeProdutoUseCase.update(new ClasseProdutoDTO(1, "")));

    }

    @Test
    @DisplayName("Deve gerar a exception IllegalArgumentException quando a classe for null")
    void testUpdateIllegalArgumentException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> classeProdutoUseCase.update(null));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> classeProdutoUseCase.update(new ClasseProdutoDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        ClasseProdutoDTO classeProduto = new ClasseProdutoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeProdutoUseCase.update(classeProduto));
        classeProduto.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeProdutoUseCase.update(classeProduto));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        ClasseProdutoDTO classeProduto = new ClasseProdutoDTO(0, "WithIdZeroOrNotInformedException");

        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeProdutoUseCase.update(classeProduto));
        classeProduto.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> classeProdutoUseCase.update(classeProduto));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um classeProduto como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(classeProdutoRepository.save(any(ClasseProduto.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    classeProdutoUseCase.insert(new ClasseProdutoDTO(1, "TestGroup First"));
                    classeProdutoUseCase.insert(new ClasseProdutoDTO(2, "TestGroup Seconde"));
                    classeProdutoUseCase.update(new ClasseProdutoDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        ClasseProdutoDTO classeProdutoDTO = new ClasseProdutoDTO(0, "testUpdateWithNonEmptyName");
        when(classeProdutoRepository.save(any(ClasseProduto.class))).thenReturn(new ClasseProduto());
        assertNotNull(classeProdutoUseCase.insert(classeProdutoDTO));

    }
}
