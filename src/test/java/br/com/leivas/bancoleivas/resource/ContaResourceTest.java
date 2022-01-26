package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.BaseTest;
import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class ContaResourceTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void novaConta() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/conta/nova")
                .accept(MediaType.APPLICATION_JSON)
                .content(this.getDummyData())
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private String getDummyData() throws JsonProcessingException {
        LocalDate dataNascimento = LocalDate.of(1998, 10, 2);
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setNome("Guilherme Leivas");
        pessoaFisicaDTO.setDataNascimento(Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        pessoaFisicaDTO.setNomePai("Carlos Alberto");
        pessoaFisicaDTO.setNomeMae("Susi Leivas");
        pessoaFisicaDTO.setNomeConjugue(null);
        pessoaFisicaDTO.setSexo(PessoaFisica.SexoPessoa.MASCULINO);
        pessoaFisicaDTO.setCadastroNacional(
                CadastroNacionalDTO
                        .builder()
                        .emissor("ssp")
                        .numero("76626415034")
                        .tipo(CadastroNacional.TipoCadastroNacional.CPF)
                        .build()
        );
        return this.objectMapper.writeValueAsString(new ContaDTO(pessoaFisicaDTO));
    }
}