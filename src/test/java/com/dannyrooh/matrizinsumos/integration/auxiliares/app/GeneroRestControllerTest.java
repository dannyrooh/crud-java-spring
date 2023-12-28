package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GeneroDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.GeneroRestController;
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
class GeneroRestControllerTest {

    @Mock
    private AuxiliarUseCase<GeneroDTO, Integer> generoUseCase;

    @InjectMocks
    private GeneroRestController generoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(generoRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        GeneroDTO generoDTO = new GeneroDTO(1, "Test Genero");

        when(generoUseCase.insert(any(GeneroDTO.class))).thenReturn(generoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/genero")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(generoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Genero"));
    }

    @Test
    void testUpdate() throws Exception {
        GeneroDTO generoDTOToUpdate = new GeneroDTO(1, "Update Genero");

        when(generoUseCase.update(any(GeneroDTO.class))).thenReturn(generoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/genero")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(generoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update Genero"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(generoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/genero/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        GeneroDTO generoDTO = new GeneroDTO(idToGet, "Test Genero");

        when(generoUseCase.getById(idToGet)).thenReturn(generoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/genero/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Genero"));
    }

    @Test
    void testGetAll() throws Exception {
        List<GeneroDTO> generoDTOList = Arrays.asList(
                new GeneroDTO(1, "Genero 1"),
                new GeneroDTO(2, "Genero 2"),
                new GeneroDTO(3, "Genero 3"));

        when(generoUseCase.getAll()).thenReturn(generoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/genero/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(generoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Genero 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Genero 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("Genero 3"));
    }
}
