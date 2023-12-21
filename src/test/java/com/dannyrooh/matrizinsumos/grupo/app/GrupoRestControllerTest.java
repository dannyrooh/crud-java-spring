package com.dannyrooh.matrizinsumos.grupo.app;

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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    // Adicione testes similares para os métodos update, delete, getById, getAll,
    // conforme necessário
}
