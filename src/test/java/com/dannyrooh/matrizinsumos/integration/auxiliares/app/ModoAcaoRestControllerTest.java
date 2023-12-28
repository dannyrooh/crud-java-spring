package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.ModoAcaoRestController;
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
class ModoAcaoRestControllerTest {

    @Mock
    private AuxiliarUseCase<ModoAcaoDTO, Integer> modoAcaoUseCase;

    @InjectMocks
    private ModoAcaoRestController modoAcaoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(modoAcaoRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInsert() throws Exception {
        ModoAcaoDTO modoAcaoDTO = new ModoAcaoDTO(1, "Test ModoAcao");

        when(modoAcaoUseCase.insert(any(ModoAcaoDTO.class))).thenReturn(modoAcaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/modoAcao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modoAcaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test ModoAcao"));
    }

    @Test
    void testUpdate() throws Exception {
        ModoAcaoDTO modoAcaoDTOToUpdate = new ModoAcaoDTO(1, "Update ModoAcao");

        when(modoAcaoUseCase.update(any(ModoAcaoDTO.class))).thenReturn(modoAcaoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/modoAcao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modoAcaoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Update ModoAcao"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(modoAcaoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/modoAcao/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        ModoAcaoDTO modoAcaoDTO = new ModoAcaoDTO(idToGet, "Test ModoAcao");

        when(modoAcaoUseCase.getById(idToGet)).thenReturn(modoAcaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/modoAcao/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test ModoAcao"));
    }

    @Test
    void testGetAll() throws Exception {
        List<ModoAcaoDTO> modoAcaoDTOList = Arrays.asList(
                new ModoAcaoDTO(1, "ModoAcao 1"),
                new ModoAcaoDTO(2, "ModoAcao 2"),
                new ModoAcaoDTO(3, "ModoAcao 3"));

        when(modoAcaoUseCase.getAll()).thenReturn(modoAcaoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/modoAcao/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(modoAcaoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("ModoAcao 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("ModoAcao 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("ModoAcao 3"));
    }
}
