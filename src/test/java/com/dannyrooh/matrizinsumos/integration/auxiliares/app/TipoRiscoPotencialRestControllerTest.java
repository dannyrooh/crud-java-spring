package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.TipoRiscoPotencialRestController;
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
class TipoRiscoPotencialRestControllerTest {

    @Mock
    private AuxiliarUseCase<TipoRiscoPotencialDTO, Integer> tipoRiscoPotencialUseCase;

    @InjectMocks
    private TipoRiscoPotencialRestController tipoRiscoPotencialRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tipoRiscoPotencialRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        TipoRiscoPotencialDTO tipoRiscoPotencialDTO = new TipoRiscoPotencialDTO(1, "Test TipoRiscoPotencial");

        when(tipoRiscoPotencialUseCase.insert(any(TipoRiscoPotencialDTO.class))).thenReturn(tipoRiscoPotencialDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tipoRiscoPotencial")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoRiscoPotencialDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoRiscoPotencial"));
    }

    @Test
    void testUpdate() throws Exception {
        TipoRiscoPotencialDTO tipoRiscoPotencialDTOToUpdate = new TipoRiscoPotencialDTO(1, "Update TipoRiscoPotencial");

        when(tipoRiscoPotencialUseCase.update(any(TipoRiscoPotencialDTO.class))).thenReturn(tipoRiscoPotencialDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/tipoRiscoPotencial")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoRiscoPotencialDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update TipoRiscoPotencial"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(tipoRiscoPotencialUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tipoRiscoPotencial/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        TipoRiscoPotencialDTO tipoRiscoPotencialDTO = new TipoRiscoPotencialDTO(idToGet, "Test TipoRiscoPotencial");

        when(tipoRiscoPotencialUseCase.getById(idToGet)).thenReturn(tipoRiscoPotencialDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoRiscoPotencial/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoRiscoPotencial"));
    }

    @Test
    void testGetAll() throws Exception {
        List<TipoRiscoPotencialDTO> tipoRiscoPotencialDTOList = Arrays.asList(
                new TipoRiscoPotencialDTO(1, "TipoRiscoPotencial 1"),
                new TipoRiscoPotencialDTO(2, "TipoRiscoPotencial 2"),
                new TipoRiscoPotencialDTO(3, "TipoRiscoPotencial 3"));

        when(tipoRiscoPotencialUseCase.getAll()).thenReturn(tipoRiscoPotencialDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoRiscoPotencial/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(tipoRiscoPotencialDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("TipoRiscoPotencial 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("TipoRiscoPotencial 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("TipoRiscoPotencial 3"));
    }
}
