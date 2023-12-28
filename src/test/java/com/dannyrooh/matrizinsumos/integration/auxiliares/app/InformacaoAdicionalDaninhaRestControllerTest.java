package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.InformacaoAdicionalDaninhaRestController;
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
class InformacaoAdicionalDaninhaRestControllerTest {

    @Mock
    private AuxiliarUseCase<InformacaoAdicionalDaninhaDTO, Integer> informacaoAdicionalDaninhaUseCase;

    @InjectMocks
    private InformacaoAdicionalDaninhaRestController informacaoAdicionalDaninhaRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(informacaoAdicionalDaninhaRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTO = new InformacaoAdicionalDaninhaDTO(1, "Test InformacaoAdicionalDaninha");

        when(informacaoAdicionalDaninhaUseCase.insert(any(InformacaoAdicionalDaninhaDTO.class))).thenReturn(informacaoAdicionalDaninhaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/informacaoAdicionalDaninha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(informacaoAdicionalDaninhaDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test InformacaoAdicionalDaninha"));
    }

    @Test
    void testUpdate() throws Exception {
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTOToUpdate = new InformacaoAdicionalDaninhaDTO(1, "Update InformacaoAdicionalDaninha");

        when(informacaoAdicionalDaninhaUseCase.update(any(InformacaoAdicionalDaninhaDTO.class))).thenReturn(informacaoAdicionalDaninhaDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/informacaoAdicionalDaninha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(informacaoAdicionalDaninhaDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update InformacaoAdicionalDaninha"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(informacaoAdicionalDaninhaUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/informacaoAdicionalDaninha/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        InformacaoAdicionalDaninhaDTO informacaoAdicionalDaninhaDTO = new InformacaoAdicionalDaninhaDTO(idToGet, "Test InformacaoAdicionalDaninha");

        when(informacaoAdicionalDaninhaUseCase.getById(idToGet)).thenReturn(informacaoAdicionalDaninhaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/informacaoAdicionalDaninha/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test InformacaoAdicionalDaninha"));
    }

    @Test
    void testGetAll() throws Exception {
        List<InformacaoAdicionalDaninhaDTO> informacaoAdicionalDaninhaDTOList = Arrays.asList(
                new InformacaoAdicionalDaninhaDTO(1, "InformacaoAdicionalDaninha 1"),
                new InformacaoAdicionalDaninhaDTO(2, "InformacaoAdicionalDaninha 2"),
                new InformacaoAdicionalDaninhaDTO(3, "InformacaoAdicionalDaninha 3"));

        when(informacaoAdicionalDaninhaUseCase.getAll()).thenReturn(informacaoAdicionalDaninhaDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/informacaoAdicionalDaninha/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(informacaoAdicionalDaninhaDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("InformacaoAdicionalDaninha 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("InformacaoAdicionalDaninha 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("InformacaoAdicionalDaninha 3"));
    }
}
