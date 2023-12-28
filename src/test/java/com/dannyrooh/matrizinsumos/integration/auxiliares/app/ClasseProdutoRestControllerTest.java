package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.ClasseProdutoRestController;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.AuxiliarUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClasseProdutoRestControllerTest {

    @Mock
    private AuxiliarUseCase<ClasseProdutoDTO, Integer> classeProdutoUseCase;

    @InjectMocks
    private ClasseProdutoRestController classeProdutoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(classeProdutoRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        ClasseProdutoDTO classeProdutoDTO = new ClasseProdutoDTO(1, "Test ClasseProduto");

        when(classeProdutoUseCase.insert(any(ClasseProdutoDTO.class))).thenReturn(classeProdutoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/classeProduto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeProdutoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test ClasseProduto"));
    }

    @Test
    void testUpdate() throws Exception {
        ClasseProdutoDTO classeProdutoDTOToUpdate = new ClasseProdutoDTO(1, "Update ClasseProduto");

        when(classeProdutoUseCase.update(any(ClasseProdutoDTO.class))).thenReturn(classeProdutoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/classeProduto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeProdutoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update ClasseProduto"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(classeProdutoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/classeProduto/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        ClasseProdutoDTO classeProdutoDTO = new ClasseProdutoDTO(idToGet, "Test ClasseProduto");

        when(classeProdutoUseCase.getById(idToGet)).thenReturn(classeProdutoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/classeProduto/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test ClasseProduto"));
    }

    @Test
    void testGetAll() throws Exception {
        List<ClasseProdutoDTO> classeProdutoDTOList = Arrays.asList(
                new ClasseProdutoDTO(1, "ClasseProduto 1"),
                new ClasseProdutoDTO(2, "ClasseProduto 2"),
                new ClasseProdutoDTO(3, "ClasseProduto 3"));

        when(classeProdutoUseCase.getAll()).thenReturn(classeProdutoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/classeProduto/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(classeProdutoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("ClasseProduto 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("ClasseProduto 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("ClasseProduto 3"));
    }
}
