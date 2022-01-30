package br.com.leivas.bancoleivas.resource.conta;

import br.com.leivas.bancoleivas.BaseTest;
import br.com.leivas.bancoleivas.resource.dummy.ContaDummyData;
import br.com.leivas.bancoleivas.resource.dummy.PessoaDummyData;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class ContaResourceCreateTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void novaContaFisica() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/conta/fisica/nova")
                .accept(MediaType.APPLICATION_JSON)
                .content(this.getDummyDataPessoaFisica())
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void novaContaJuridica() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/conta/juridica/nova")
                .accept(MediaType.APPLICATION_JSON)
                .content(this.getDummyDataPessoaJuridica())
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private String getDummyDataPessoaFisica() throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(PessoaDummyData.pessoaFisicaDTO("Guilherme Leivas"));
    }

    private String getDummyDataPessoaJuridica() throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(PessoaDummyData.pessoaJuridicaDTO("Susi de F Leivas"));
    }
}