package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.TipoManifestacaoRestController;
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
class TipoManifestacaoRestControllerTest {

    @Mock
    private AuxiliarUseCase<TipoManifestacaoDTO, Integer> tipoManifestacaoUseCase;

    @InjectMocks
    private TipoManifestacaoRestController tipoManifestacaoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tipoManifestacaoRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        TipoManifestacaoDTO tipoManifestacaoDTO = new TipoManifestacaoDTO(1, "Test TipoManifestacao");

        when(tipoManifestacaoUseCase.insert(any(TipoManifestacaoDTO.class))).thenReturn(tipoManifestacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tipoManifestacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoManifestacaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoManifestacao"));
    }

    @Test
    void testUpdate() throws Exception {
        TipoManifestacaoDTO tipoManifestacaoDTOToUpdate = new TipoManifestacaoDTO(1, "Update TipoManifestacao");

        when(tipoManifestacaoUseCase.update(any(TipoManifestacaoDTO.class))).thenReturn(tipoManifestacaoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/tipoManifestacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoManifestacaoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update TipoManifestacao"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(tipoManifestacaoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tipoManifestacao/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        TipoManifestacaoDTO tipoManifestacaoDTO = new TipoManifestacaoDTO(idToGet, "Test TipoManifestacao");

        when(tipoManifestacaoUseCase.getById(idToGet)).thenReturn(tipoManifestacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoManifestacao/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test TipoManifestacao"));
    }

    @Test
    void testGetAll() throws Exception {
        List<TipoManifestacaoDTO> tipoManifestacaoDTOList = Arrays.asList(
                new TipoManifestacaoDTO(1, "TipoManifestacao 1"),
                new TipoManifestacaoDTO(2, "TipoManifestacao 2"),
                new TipoManifestacaoDTO(3, "TipoManifestacao 3"));

        when(tipoManifestacaoUseCase.getAll()).thenReturn(tipoManifestacaoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipoManifestacao/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(tipoManifestacaoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("TipoManifestacao 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("TipoManifestacao 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("TipoManifestacao 3"));
    }
}
