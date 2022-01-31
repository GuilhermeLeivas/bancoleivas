package br.com.leivas.bancoleivas.resource.modelo;

import br.com.leivas.bancoleivas.BaseTest;
import br.com.leivas.bancoleivas.dto.fin.ModeloTransacaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
public class ModeloTransacaoResourceCreateTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void criaNovoModeloTransacao() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/modelo/transacao")
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getDummyDataModelo()))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private ModeloTransacaoDTO getDummyDataModelo() {
        return ModeloTransacaoDTO.builder()
                .codigo(201)
                .nome("Transferência")
                .transacaoStrategy("br.com.leivas.bancoleivas.strategy.TransferenciaStrategy")
                .build();
    }
}
