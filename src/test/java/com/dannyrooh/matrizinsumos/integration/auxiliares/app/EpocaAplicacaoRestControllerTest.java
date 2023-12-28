package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.EpocaAplicacaoRestController;
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
class EpocaAplicacaoRestControllerTest {

    @Mock
    private AuxiliarUseCase<EpocaAplicacaoDTO, Integer> epocaAplicacaoUseCase;

    @InjectMocks
    private EpocaAplicacaoRestController epocaAplicacaoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(epocaAplicacaoRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        EpocaAplicacaoDTO epocaAplicacaoDTO = new EpocaAplicacaoDTO(1, "Test EpocaAplicacao");

        when(epocaAplicacaoUseCase.insert(any(EpocaAplicacaoDTO.class))).thenReturn(epocaAplicacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/epocaAplicacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(epocaAplicacaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test EpocaAplicacao"));
    }

    @Test
    void testUpdate() throws Exception {
        EpocaAplicacaoDTO epocaAplicacaoDTOToUpdate = new EpocaAplicacaoDTO(1, "Update EpocaAplicacao");

        when(epocaAplicacaoUseCase.update(any(EpocaAplicacaoDTO.class))).thenReturn(epocaAplicacaoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/epocaAplicacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(epocaAplicacaoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update EpocaAplicacao"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(epocaAplicacaoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/epocaAplicacao/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        EpocaAplicacaoDTO epocaAplicacaoDTO = new EpocaAplicacaoDTO(idToGet, "Test EpocaAplicacao");

        when(epocaAplicacaoUseCase.getById(idToGet)).thenReturn(epocaAplicacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/epocaAplicacao/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test EpocaAplicacao"));
    }

    @Test
    void testGetAll() throws Exception {
        List<EpocaAplicacaoDTO> epocaAplicacaoDTOList = Arrays.asList(
                new EpocaAplicacaoDTO(1, "EpocaAplicacao 1"),
                new EpocaAplicacaoDTO(2, "EpocaAplicacao 2"),
                new EpocaAplicacaoDTO(3, "EpocaAplicacao 3"));

        when(epocaAplicacaoUseCase.getAll()).thenReturn(epocaAplicacaoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/epocaAplicacao/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(epocaAplicacaoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("EpocaAplicacao 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("EpocaAplicacao 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("EpocaAplicacao 3"));
    }
}
