package com.dannyrooh.matrizinsumos.integration.auxiliares.app;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.NivelPericulosidadeUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.entrypoint.controller.NivelPericulosidadeRestController;
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
class NivelPericulosidadeRestControllerTest {

    @Mock
    private NivelPericulosidadeUseCase grupoUseCase;

    @InjectMocks
    private NivelPericulosidadeRestController grupoRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(grupoRestController).build();
        objectMapper = new ObjectMapper();        
    }

    @Test
    void testInsert() throws Exception {
        NivelPericulosidadeDTO grupoDTO = new NivelPericulosidadeDTO(1, "Test Group");

        when(grupoUseCase.insert(any(NivelPericulosidadeDTO.class))).thenReturn(grupoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/grupo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(grupoDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Group"));
    }

    @Test
    void testUpdate() throws Exception {
        NivelPericulosidadeDTO grupoDTOToUpdate = new NivelPericulosidadeDTO(1, "Updated Group");

        when(grupoUseCase.update(any(NivelPericulosidadeDTO.class))).thenReturn(grupoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/grupo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(grupoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Updated Group"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(grupoUseCase.delete(idToDelete)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/grupo/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        NivelPericulosidadeDTO grupoDTO = new NivelPericulosidadeDTO(idToGet, "Test Group");

        when(grupoUseCase.getById(idToGet)).thenReturn(grupoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/grupo/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Group"));
    }

    @Test
    void testGetAll() throws Exception {
        List<NivelPericulosidadeDTO> grupoDTOList = Arrays.asList(
                new NivelPericulosidadeDTO(1, "Group 1"),
                new NivelPericulosidadeDTO(2, "Group 2"),
                new NivelPericulosidadeDTO(3, "Group 3"));

        when(grupoUseCase.getAll()).thenReturn(grupoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/grupo/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(grupoDTOList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Group 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Group 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome").value("Group 3"));
    }
}
