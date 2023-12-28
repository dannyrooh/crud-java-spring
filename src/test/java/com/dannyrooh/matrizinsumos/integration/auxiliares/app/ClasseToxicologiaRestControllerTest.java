package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.ClasseToxicologiaRestController;
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
class ClasseToxicologiaRestControllerTest {

    @Mock
    private AuxiliarUseCase<ClasseToxicologiaDTO, Integer> classeToxicologiaUseCase;

    @InjectMocks
    private ClasseToxicologiaRestController classeToxicologiaRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(classeToxicologiaRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        ClasseToxicologiaDTO classeToxicologiaDTO = new ClasseToxicologiaDTO(1, "Test ClasseToxicologia");

        when(classeToxicologiaUseCase.insert(any(ClasseToxicologiaDTO.class))).thenReturn(classeToxicologiaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/classeToxicologia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeToxicologiaDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test ClasseToxicologia"));
    }

    @Test
    void testUpdate() throws Exception {
        ClasseToxicologiaDTO classeToxicologiaDTOToUpdate = new ClasseToxicologiaDTO(1, "Update ClasseToxicologia");

        when(classeToxicologiaUseCase.update(any(ClasseToxicologiaDTO.class))).thenReturn(classeToxicologiaDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/classeToxicologia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeToxicologiaDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update ClasseToxicologia"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(classeToxicologiaUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/classeToxicologia/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        ClasseToxicologiaDTO classeToxicologiaDTO = new ClasseToxicologiaDTO(idToGet, "Test ClasseToxicologia");

        when(classeToxicologiaUseCase.getById(idToGet)).thenReturn(classeToxicologiaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/classeToxicologia/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test ClasseToxicologia"));
    }

    @Test
    void testGetAll() throws Exception {
        List<ClasseToxicologiaDTO> classeToxicologiaDTOList = Arrays.asList(
                new ClasseToxicologiaDTO(1, "ClasseToxicologia 1"),
                new ClasseToxicologiaDTO(2, "ClasseToxicologia 2"),
                new ClasseToxicologiaDTO(3, "ClasseToxicologia 3"));

        when(classeToxicologiaUseCase.getAll()).thenReturn(classeToxicologiaDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/classeToxicologia/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(classeToxicologiaDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("ClasseToxicologia 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("ClasseToxicologia 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("ClasseToxicologia 3"));
    }
}
