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
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.ClasseToxicologiaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ClasseToxicologiaUseCaseUpdateTest {

    private ClasseToxicologiaRepository grupoRepository;
    private ClasseToxicologiaUseCaseImpl grupoUseCase;
    private ClasseToxicologiaUseCaseValidateImpl grupoUseCaseValidateImpl;

    @BeforeEach
    void setUp() {
        grupoRepository = mock(ClasseToxicologiaRepository.class);
        grupoUseCaseValidateImpl = new ClasseToxicologiaUseCaseValidateImpl();
        grupoUseCase = new ClasseToxicologiaUseCaseImpl(grupoRepository, grupoUseCaseValidateImpl);
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameEmptyException quando o nome estiver em branco ou for null")
    void testUpdateWithEmptyNameException() throws ValidationException {
        assertAll(
                () -> assertThrows(WithNameEmptyException.class, () -> grupoUseCase.update(new ClasseToxicologiaDTO(1, ""))),
                () -> assertThrows(WithNameEmptyException.class, () -> grupoUseCase.update(null)));
    }

    @Test
    @DisplayName("Deve gerar a exception WithNameMaxSizeException quando o nome for superior a 50 caracteres")
    void testUpdateWithNameMaxCharacterException() throws ValidationException {
        assertThrows(WithNameMaxSizeException.class,
                () -> grupoUseCase.update(new ClasseToxicologiaDTO(1, "a".repeat(51))));
    }

    @Test
    @DisplayName("Deve gerar a exception WithIdZeroOrNotInformedException quando o id for menor que zero")
    void testUpdateWithIdZeroOrNotInformedException() throws ValidationException {

        ClasseToxicologiaDTO grupo = new ClasseToxicologiaDTO();
        grupo.setNome("WithIdZeroOrNotInformedException");
        grupo.setId(0);

        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));
        grupo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithIdNotFoundException quando o id não existe na base de dados")
    void testUpdateWithIdNotFoundException() throws ValidationException {

        ClasseToxicologiaDTO grupo = new ClasseToxicologiaDTO();
        grupo.setNome("WithIdZeroOrNotInformedException");
        grupo.setId(0);

        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));
        grupo.setId(-1);
        assertThrows(WithIdZeroOrNotInformedException.class, () -> grupoUseCase.update(grupo));

    }

    @Test
    @DisplayName("Deve gerar a exception WithNameAlreadInformedException caso já exista um grupo como o nome informado")
    void testUpdateWithNameAlreadInformedException() throws ValidationException {

        when(grupoRepository.save(any(ClasseToxicologia.class))).thenThrow(WithNameAlreadInformedException.class);
        assertThrows(WithNameAlreadInformedException.class,
                () -> {
                    grupoUseCase.insert(new ClasseToxicologiaDTO(1, "TestGroup First"));
                    grupoUseCase.insert(new ClasseToxicologiaDTO(2, "TestGroup Seconde"));
                    grupoUseCase.update(new ClasseToxicologiaDTO(1, "TestGroup Second"));
                });
    }

    @Test
    @DisplayName("Deve inserir o objeto no banco de dados e retornar com o código gerado")
    void testUpdateWithNonEmptyName() throws ValidationException {
        ClasseToxicologiaDTO grupoDTO = new ClasseToxicologiaDTO(0, "testUpdateWithNonEmptyName");
        when(grupoRepository.save(any(ClasseToxicologia.class))).thenReturn(new ClasseToxicologia());
        assertNotNull(grupoUseCase.insert(grupoDTO));

    }
}
