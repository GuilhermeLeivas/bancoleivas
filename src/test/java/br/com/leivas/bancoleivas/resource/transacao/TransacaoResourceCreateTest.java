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

@AutoConfigureMockMvc(addFilters = false)
@Sql(scripts = "classpath:fakeData.sql")
public class TransacaoResourceCreateTest extends BaseTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    // Informações da Transação
    Long fakeNumeroContaOrigem = 450L;
    Long fakeNumeroContaDestino = 1000L;
    BigDecimal valorTransacao = new BigDecimal("450.50");
    TransacaoDTO transacaoDTO;

    @Test
    public void novaTransacaoCliente() throws Exception {
        this.buildTransacaoDTO(201);
        MockHttpServletResponse response = this.geraNovaTransacao("/transacao/cliente/nova");
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void novaTransacaoOperacional() throws Exception {
        this.buildTransacaoDTO(200);
        MockHttpServletResponse response = this.geraNovaTransacao("/transacao/operacional/nova");
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private MockHttpServletResponse geraNovaTransacao(String resource) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(resource)
                .content(this.objectMapper.writeValueAsString(this.transacaoDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }

    private void buildTransacaoDTO(Integer codigo) {
        this.transacaoDTO = TransacaoDTO.builder()
                .numeroContaOrigem(this.fakeNumeroContaOrigem)
                .numeroContaDestino(this.fakeNumeroContaDestino)
                .valor(this.valorTransacao)
                .codigoTransacao(codigo)
                .build();
    }
}
