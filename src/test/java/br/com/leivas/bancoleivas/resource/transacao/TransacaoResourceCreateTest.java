package br.com.leivas.bancoleivas.resource.transacao;

import br.com.leivas.bancoleivas.BaseTest;
import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@Sql(scripts = "fakeData.sql")
public class TransacaoResourceCreateTest extends BaseTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    // Informações da Transação
    Long fakeNumeroContaOrigem = 450L;
    Long fakeNumeroContaDestino = 1000L;
    BigDecimal valorTransacao = new BigDecimal("450.50");
    int codigoTransacao = 200;
    TransacaoDTO transacaoDTO;

    @Test
    public void novaTransacao() throws Exception {
        this.buildTransacaoDTO();
        MockHttpServletResponse response = this.geraNovaTransacao();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private MockHttpServletResponse geraNovaTransacao() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/transacao")
                .content(this.objectMapper.writeValueAsString(this.transacaoDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }

    private void buildTransacaoDTO() {
        this.transacaoDTO = TransacaoDTO.builder()
                .numeroContaOrigem(this.fakeNumeroContaOrigem)
                .numeroContaDestino(this.fakeNumeroContaDestino)
                .valor(this.valorTransacao)
                .codigoTransacao(this.codigoTransacao)
                .build();
    }
}
