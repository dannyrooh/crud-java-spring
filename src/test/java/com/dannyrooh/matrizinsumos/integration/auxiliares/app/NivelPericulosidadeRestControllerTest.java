package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.NivelPericulosidadeRestController;
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
class NivelPericulosidadeRestControllerTest {

    @Mock
    private AuxiliarUseCase<NivelPericulosidadeDTO, Integer> nivelPericulosidadeUseCase;

    @InjectMocks
    private NivelPericulosidadeRestController nivelPericulosidadeRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(nivelPericulosidadeRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        NivelPericulosidadeDTO nivelPericulosidadeDTO = new NivelPericulosidadeDTO(1, "Test NivelPericulosidade");

        when(nivelPericulosidadeUseCase.insert(any(NivelPericulosidadeDTO.class))).thenReturn(nivelPericulosidadeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/nivelPericulosidade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nivelPericulosidadeDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test NivelPericulosidade"));
    }

    @Test
    void testUpdate() throws Exception {
        NivelPericulosidadeDTO nivelPericulosidadeDTOToUpdate = new NivelPericulosidadeDTO(1, "Update NivelPericulosidade");

        when(nivelPericulosidadeUseCase.update(any(NivelPericulosidadeDTO.class))).thenReturn(nivelPericulosidadeDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/nivelPericulosidade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nivelPericulosidadeDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update NivelPericulosidade"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(nivelPericulosidadeUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/nivelPericulosidade/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        NivelPericulosidadeDTO nivelPericulosidadeDTO = new NivelPericulosidadeDTO(idToGet, "Test NivelPericulosidade");

        when(nivelPericulosidadeUseCase.getById(idToGet)).thenReturn(nivelPericulosidadeDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/nivelPericulosidade/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test NivelPericulosidade"));
    }

    @Test
    void testGetAll() throws Exception {
        List<NivelPericulosidadeDTO> nivelPericulosidadeDTOList = Arrays.asList(
                new NivelPericulosidadeDTO(1, "NivelPericulosidade 1"),
                new NivelPericulosidadeDTO(2, "NivelPericulosidade 2"),
                new NivelPericulosidadeDTO(3, "NivelPericulosidade 3"));

        when(nivelPericulosidadeUseCase.getAll()).thenReturn(nivelPericulosidadeDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/nivelPericulosidade/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(nivelPericulosidadeDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("NivelPericulosidade 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("NivelPericulosidade 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("NivelPericulosidade 3"));
    }
}
