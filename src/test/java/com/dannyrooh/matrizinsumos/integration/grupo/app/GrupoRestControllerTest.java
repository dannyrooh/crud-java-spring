package com.dannyrooh.matrizinsumos.integration.grupo.app;

import com.dannyrooh.matrizinsumos.grupo.app.controller.GrupoRestController;
import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.app.usecase.GrupoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(GrupoRestController.class)
class GrupoRestControllerTest {

    @Mock
    private GrupoUseCase grupoUseCase;

    @InjectMocks
    private GrupoRestController grupoRestController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testInsert() throws Exception {
        GrupoDTO grupoDTO = new GrupoDTO(1, "Test Group");

        when(grupoUseCase.insert(any(GrupoDTO.class))).thenReturn(grupoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/grupo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(grupoDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Group"));
    }

    @Test
    void testDelete() throws Exception {
        int idToDelete = 1;

        when(grupoUseCase.delete(anyInt())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/grupo/{id}", idToDelete)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
    }

    @Test
    void testUpdate() throws Exception {
        GrupoDTO grupoDTOToUpdate = new GrupoDTO(1, "Updated Group");

        when(grupoUseCase.update(any(GrupoDTO.class))).thenReturn(grupoDTOToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/grupo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(grupoDTOToUpdate)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Updated Group"));
    }

    @Test
    void testGetById() throws Exception {
        int idToGet = 1;
        GrupoDTO grupoDTO = new GrupoDTO(idToGet, "Test Group");

        when(grupoUseCase.getById(anyInt())).thenReturn(grupoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/grupo/{id}", idToGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idToGet))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Test Group"));
    }

    @Test
    void testGetAll() throws Exception {
        List<GrupoDTO> grupoDTOList = Arrays.asList(
                new GrupoDTO(1, "Group 1"),
                new GrupoDTO(2, "Group 2"),
                new GrupoDTO(3, "Group 3"));

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
