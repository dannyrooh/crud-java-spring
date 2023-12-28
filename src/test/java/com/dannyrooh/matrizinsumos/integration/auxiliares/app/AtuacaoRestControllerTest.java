package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.AtuacaoRestController;
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
class AtuacaoRestControllerTest {

    @Mock
    private AuxiliarUseCase<AtuacaoDTO, Integer> atuacaoUseCase;

    @InjectMocks
    private AtuacaoRestController atuacaoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(atuacaoRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        AtuacaoDTO atuacaoDTO = new AtuacaoDTO(1, "Test Atuacao");

        when(atuacaoUseCase.insert(any(AtuacaoDTO.class))).thenReturn(atuacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/atuacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atuacaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Atuacao"));
    }

    @Test
    void testUpdate() throws Exception {
        AtuacaoDTO atuacaoDTOToUpdate = new AtuacaoDTO(1, "Update Atuacao");

        when(atuacaoUseCase.update(any(AtuacaoDTO.class))).thenReturn(atuacaoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/atuacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atuacaoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update Atuacao"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(atuacaoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/atuacao/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        AtuacaoDTO atuacaoDTO = new AtuacaoDTO(idToGet, "Test Atuacao");

        when(atuacaoUseCase.getById(idToGet)).thenReturn(atuacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/atuacao/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Atuacao"));
    }

    @Test
    void testGetAll() throws Exception {
        List<AtuacaoDTO> atuacaoDTOList = Arrays.asList(
                new AtuacaoDTO(1, "Atuacao 1"),
                new AtuacaoDTO(2, "Atuacao 2"),
                new AtuacaoDTO(3, "Atuacao 3"));

        when(atuacaoUseCase.getAll()).thenReturn(atuacaoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/atuacao/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(atuacaoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Atuacao 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Atuacao 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("Atuacao 3"));
    }
}
