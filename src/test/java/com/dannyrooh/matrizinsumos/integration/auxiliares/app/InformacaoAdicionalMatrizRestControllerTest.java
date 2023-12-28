package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.InformacaoAdicionalMatrizRestController;
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
class InformacaoAdicionalMatrizRestControllerTest {

    @Mock
    private AuxiliarUseCase<InformacaoAdicionalMatrizDTO, Integer> informacaoAdicionalMatrizUseCase;

    @InjectMocks
    private InformacaoAdicionalMatrizRestController informacaoAdicionalMatrizRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(informacaoAdicionalMatrizRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTO = new InformacaoAdicionalMatrizDTO(1, "Test InformacaoAdicionalMatriz");

        when(informacaoAdicionalMatrizUseCase.insert(any(InformacaoAdicionalMatrizDTO.class))).thenReturn(informacaoAdicionalMatrizDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/informacaoAdicionalMatriz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(informacaoAdicionalMatrizDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test InformacaoAdicionalMatriz"));
    }

    @Test
    void testUpdate() throws Exception {
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTOToUpdate = new InformacaoAdicionalMatrizDTO(1, "Update InformacaoAdicionalMatriz");

        when(informacaoAdicionalMatrizUseCase.update(any(InformacaoAdicionalMatrizDTO.class))).thenReturn(informacaoAdicionalMatrizDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/informacaoAdicionalMatriz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(informacaoAdicionalMatrizDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update InformacaoAdicionalMatriz"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(informacaoAdicionalMatrizUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/informacaoAdicionalMatriz/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        InformacaoAdicionalMatrizDTO informacaoAdicionalMatrizDTO = new InformacaoAdicionalMatrizDTO(idToGet, "Test InformacaoAdicionalMatriz");

        when(informacaoAdicionalMatrizUseCase.getById(idToGet)).thenReturn(informacaoAdicionalMatrizDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/informacaoAdicionalMatriz/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test InformacaoAdicionalMatriz"));
    }

    @Test
    void testGetAll() throws Exception {
        List<InformacaoAdicionalMatrizDTO> informacaoAdicionalMatrizDTOList = Arrays.asList(
                new InformacaoAdicionalMatrizDTO(1, "InformacaoAdicionalMatriz 1"),
                new InformacaoAdicionalMatrizDTO(2, "InformacaoAdicionalMatriz 2"),
                new InformacaoAdicionalMatrizDTO(3, "InformacaoAdicionalMatriz 3"));

        when(informacaoAdicionalMatrizUseCase.getAll()).thenReturn(informacaoAdicionalMatrizDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/informacaoAdicionalMatriz/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(informacaoAdicionalMatrizDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("InformacaoAdicionalMatriz 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("InformacaoAdicionalMatriz 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("InformacaoAdicionalMatriz 3"));
    }
}
