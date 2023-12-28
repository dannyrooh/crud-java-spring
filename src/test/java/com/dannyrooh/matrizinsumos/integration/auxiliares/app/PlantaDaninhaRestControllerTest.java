package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.PlantaDaninhaRestController;
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
class PlantaDaninhaRestControllerTest {

    @Mock
    private AuxiliarUseCase<PlantaDaninhaDTO, Integer> plantaDaninhaUseCase;

    @InjectMocks
    private PlantaDaninhaRestController plantaDaninhaRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(plantaDaninhaRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        PlantaDaninhaDTO plantaDaninhaDTO = new PlantaDaninhaDTO(1, "Test PlantaDaninha");

        when(plantaDaninhaUseCase.insert(any(PlantaDaninhaDTO.class))).thenReturn(plantaDaninhaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/plantaDaninha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(plantaDaninhaDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test PlantaDaninha"));
    }

    @Test
    void testUpdate() throws Exception {
        PlantaDaninhaDTO plantaDaninhaDTOToUpdate = new PlantaDaninhaDTO(1, "Update PlantaDaninha");

        when(plantaDaninhaUseCase.update(any(PlantaDaninhaDTO.class))).thenReturn(plantaDaninhaDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/plantaDaninha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(plantaDaninhaDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update PlantaDaninha"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(plantaDaninhaUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/plantaDaninha/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        PlantaDaninhaDTO plantaDaninhaDTO = new PlantaDaninhaDTO(idToGet, "Test PlantaDaninha");

        when(plantaDaninhaUseCase.getById(idToGet)).thenReturn(plantaDaninhaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/plantaDaninha/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test PlantaDaninha"));
    }

    @Test
    void testGetAll() throws Exception {
        List<PlantaDaninhaDTO> plantaDaninhaDTOList = Arrays.asList(
                new PlantaDaninhaDTO(1, "PlantaDaninha 1"),
                new PlantaDaninhaDTO(2, "PlantaDaninha 2"),
                new PlantaDaninhaDTO(3, "PlantaDaninha 3"));

        when(plantaDaninhaUseCase.getAll()).thenReturn(plantaDaninhaDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/plantaDaninha/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(plantaDaninhaDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("PlantaDaninha 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("PlantaDaninha 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("PlantaDaninha 3"));
    }
}
