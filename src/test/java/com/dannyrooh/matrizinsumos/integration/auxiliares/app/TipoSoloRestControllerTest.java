package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.TipoSoloRestController;
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
class TipoSoloRestControllerTest {

    @Mock
    private AuxiliarUseCase<TipoSoloDTO, Integer> tipoSoloUseCase;

    @InjectMocks
    private TipoSoloRestController tipoSoloRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tipoSoloRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        TipoSoloDTO tipoSoloDTO = new TipoSoloDTO(1, "Test TipoSolo");

        when(tipoSoloUseCase.insert(any(TipoSoloDTO.class))).thenReturn(tipoSoloDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tipoSolo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoSoloDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoSolo"));
    }

    @Test
    void testUpdate() throws Exception {
        TipoSoloDTO tipoSoloDTOToUpdate = new TipoSoloDTO(1, "Update TipoSolo");

        when(tipoSoloUseCase.update(any(TipoSoloDTO.class))).thenReturn(tipoSoloDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/tipoSolo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoSoloDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update TipoSolo"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(tipoSoloUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tipoSolo/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        TipoSoloDTO tipoSoloDTO = new TipoSoloDTO(idToGet, "Test TipoSolo");

        when(tipoSoloUseCase.getById(idToGet)).thenReturn(tipoSoloDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoSolo/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoSolo"));
    }

    @Test
    void testGetAll() throws Exception {
        List<TipoSoloDTO> tipoSoloDTOList = Arrays.asList(
                new TipoSoloDTO(1, "TipoSolo 1"),
                new TipoSoloDTO(2, "TipoSolo 2"),
                new TipoSoloDTO(3, "TipoSolo 3"));

        when(tipoSoloUseCase.getAll()).thenReturn(tipoSoloDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoSolo/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(tipoSoloDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("TipoSolo 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("TipoSolo 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("TipoSolo 3"));
    }
}
