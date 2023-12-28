package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.TipoPersistenciaRestController;
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
class TipoPersistenciaRestControllerTest {

    @Mock
    private AuxiliarUseCase<TipoPersistenciaDTO, Integer> tipoPersistenciaUseCase;

    @InjectMocks
    private TipoPersistenciaRestController tipoPersistenciaRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tipoPersistenciaRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        TipoPersistenciaDTO tipoPersistenciaDTO = new TipoPersistenciaDTO(1, "Test TipoPersistencia");

        when(tipoPersistenciaUseCase.insert(any(TipoPersistenciaDTO.class))).thenReturn(tipoPersistenciaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tipoPersistencia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoPersistenciaDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoPersistencia"));
    }

    @Test
    void testUpdate() throws Exception {
        TipoPersistenciaDTO tipoPersistenciaDTOToUpdate = new TipoPersistenciaDTO(1, "Update TipoPersistencia");

        when(tipoPersistenciaUseCase.update(any(TipoPersistenciaDTO.class))).thenReturn(tipoPersistenciaDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/tipoPersistencia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoPersistenciaDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update TipoPersistencia"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(tipoPersistenciaUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tipoPersistencia/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        TipoPersistenciaDTO tipoPersistenciaDTO = new TipoPersistenciaDTO(idToGet, "Test TipoPersistencia");

        when(tipoPersistenciaUseCase.getById(idToGet)).thenReturn(tipoPersistenciaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoPersistencia/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoPersistencia"));
    }

    @Test
    void testGetAll() throws Exception {
        List<TipoPersistenciaDTO> tipoPersistenciaDTOList = Arrays.asList(
                new TipoPersistenciaDTO(1, "TipoPersistencia 1"),
                new TipoPersistenciaDTO(2, "TipoPersistencia 2"),
                new TipoPersistenciaDTO(3, "TipoPersistencia 3"));

        when(tipoPersistenciaUseCase.getAll()).thenReturn(tipoPersistenciaDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoPersistencia/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(tipoPersistenciaDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("TipoPersistencia 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("TipoPersistencia 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("TipoPersistencia 3"));
    }
}
