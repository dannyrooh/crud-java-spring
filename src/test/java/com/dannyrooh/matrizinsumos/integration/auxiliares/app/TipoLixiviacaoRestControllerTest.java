package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.TipoLixiviacaoRestController;
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
class TipoLixiviacaoRestControllerTest {

    @Mock
    private AuxiliarUseCase<TipoLixiviacaoDTO, Integer> tipoLixiviacaoUseCase;

    @InjectMocks
    private TipoLixiviacaoRestController tipoLixiviacaoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tipoLixiviacaoRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        TipoLixiviacaoDTO tipoLixiviacaoDTO = new TipoLixiviacaoDTO(1, "Test TipoLixiviacao");

        when(tipoLixiviacaoUseCase.insert(any(TipoLixiviacaoDTO.class))).thenReturn(tipoLixiviacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tipoLixiviacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoLixiviacaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoLixiviacao"));
    }

    @Test
    void testUpdate() throws Exception {
        TipoLixiviacaoDTO tipoLixiviacaoDTOToUpdate = new TipoLixiviacaoDTO(1, "Update TipoLixiviacao");

        when(tipoLixiviacaoUseCase.update(any(TipoLixiviacaoDTO.class))).thenReturn(tipoLixiviacaoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/tipoLixiviacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoLixiviacaoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update TipoLixiviacao"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(tipoLixiviacaoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tipoLixiviacao/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        TipoLixiviacaoDTO tipoLixiviacaoDTO = new TipoLixiviacaoDTO(idToGet, "Test TipoLixiviacao");

        when(tipoLixiviacaoUseCase.getById(idToGet)).thenReturn(tipoLixiviacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoLixiviacao/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoLixiviacao"));
    }

    @Test
    void testGetAll() throws Exception {
        List<TipoLixiviacaoDTO> tipoLixiviacaoDTOList = Arrays.asList(
                new TipoLixiviacaoDTO(1, "TipoLixiviacao 1"),
                new TipoLixiviacaoDTO(2, "TipoLixiviacao 2"),
                new TipoLixiviacaoDTO(3, "TipoLixiviacao 3"));

        when(tipoLixiviacaoUseCase.getAll()).thenReturn(tipoLixiviacaoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoLixiviacao/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(tipoLixiviacaoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("TipoLixiviacao 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("TipoLixiviacao 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("TipoLixiviacao 3"));
    }
}
