package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.TipoInfluenciaSoloRestController;
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
class TipoInfluenciaSoloRestControllerTest {

    @Mock
    private AuxiliarUseCase<TipoInfluenciaSoloDTO, Integer> tipoInfluenciaSoloUseCase;

    @InjectMocks
    private TipoInfluenciaSoloRestController tipoInfluenciaSoloRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tipoInfluenciaSoloRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTO = new TipoInfluenciaSoloDTO(1, "Test TipoInfluenciaSolo");

        when(tipoInfluenciaSoloUseCase.insert(any(TipoInfluenciaSoloDTO.class))).thenReturn(tipoInfluenciaSoloDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tipoInfluenciaSolo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoInfluenciaSoloDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoInfluenciaSolo"));
    }

    @Test
    void testUpdate() throws Exception {
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTOToUpdate = new TipoInfluenciaSoloDTO(1, "Update TipoInfluenciaSolo");

        when(tipoInfluenciaSoloUseCase.update(any(TipoInfluenciaSoloDTO.class))).thenReturn(tipoInfluenciaSoloDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/tipoInfluenciaSolo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoInfluenciaSoloDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update TipoInfluenciaSolo"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(tipoInfluenciaSoloUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tipoInfluenciaSolo/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        TipoInfluenciaSoloDTO tipoInfluenciaSoloDTO = new TipoInfluenciaSoloDTO(idToGet, "Test TipoInfluenciaSolo");

        when(tipoInfluenciaSoloUseCase.getById(idToGet)).thenReturn(tipoInfluenciaSoloDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoInfluenciaSolo/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoInfluenciaSolo"));
    }

    @Test
    void testGetAll() throws Exception {
        List<TipoInfluenciaSoloDTO> tipoInfluenciaSoloDTOList = Arrays.asList(
                new TipoInfluenciaSoloDTO(1, "TipoInfluenciaSolo 1"),
                new TipoInfluenciaSoloDTO(2, "TipoInfluenciaSolo 2"),
                new TipoInfluenciaSoloDTO(3, "TipoInfluenciaSolo 3"));

        when(tipoInfluenciaSoloUseCase.getAll()).thenReturn(tipoInfluenciaSoloDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoInfluenciaSolo/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(tipoInfluenciaSoloDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("TipoInfluenciaSolo 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("TipoInfluenciaSolo 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("TipoInfluenciaSolo 3"));
    }
}
