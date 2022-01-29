package br.com.leivas.bancoleivas.resource.pessoa;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.exception.custom.PessoaNaoPossuiCadastroNoSistema;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import br.com.leivas.bancoleivas.resource.BaseMockTest;
import br.com.leivas.bancoleivas.resource.PessoaResource;
import br.com.leivas.bancoleivas.resource.dummy.PessoaDummyData;
import br.com.leivas.bancoleivas.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class PessoaResourceUpdateTest extends BaseMockTest {

    @Mock
    private PessoaService pessoaService;
    @InjectMocks
    private PessoaResource pessoaResource;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    @Override
    public void setup() {
        this.tearDown();
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pessoaResource).setControllerAdvice(new BancoLeivasExceptionHandler()).build();
    }

    @Test
    public void atualizaPessoaExistente() throws Exception {
        String novoNome = "Guilherme Leivas dos Santos";
        PessoaDTO pessoaDTOComAtualizacao = PessoaDummyData.pessoaFisicaDTO(novoNome);
        // when
        this.whenPessoaExistir(pessoaDTOComAtualizacao);
        // then
        MockHttpServletResponse response = this.atualizaPessoa(pessoaDTOComAtualizacao);
        String jsonRetornado = response.getContentAsString();
        Pessoa pessoaRetornada = this.pessoaFromJson(jsonRetornado);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(novoNome, pessoaRetornada.nomeReferencia());
    }

    @Test
    public void atualizaPessoaInexistente() throws Exception {
        String novoNome = "Guilherme Leivas dos Santos";
        PessoaDTO pessoaDTOComAtualizacao = PessoaDummyData.pessoaFisicaDTO(novoNome);
        // when
        this.whenPessoaNaoExistir(novoNome);
        // then
        MockHttpServletResponse response = this.atualizaPessoa(pessoaDTOComAtualizacao);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    private void whenPessoaExistir(PessoaDTO pessoaDTOComAtualizacao) {
        when(this.pessoaService.atualizaPessoa(eq(pessoaDTOComAtualizacao)))
                .thenReturn(PessoaDummyData.pessoaFisicaFromDTO(pessoaDTOComAtualizacao));
    }

    private void whenPessoaNaoExistir(String novoNome) {
        when(this.pessoaService.atualizaPessoa(any()))
                .thenThrow(new PessoaNaoPossuiCadastroNoSistema(String.format("Pessoa %s não se encontra no sistema", novoNome)));
    }

    private MockHttpServletResponse atualizaPessoa(PessoaDTO pessoaDTO) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/pessoa")
                .content(this.objectMapper.writeValueAsString(pessoaDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }

    private Pessoa pessoaFromJson(String json) throws JsonProcessingException {
        return this.objectMapper.readValue(json, PessoaFisica.class);
    }

    @Override
    public void tearDown() {
        this.pessoaService = null;
        this.pessoaResource = null;
        this.mockMvc = null;
    }
}
