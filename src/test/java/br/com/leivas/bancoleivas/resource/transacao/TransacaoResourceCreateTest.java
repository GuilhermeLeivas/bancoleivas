package br.com.leivas.bancoleivas.resource.transacao;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.resource.BaseMockTest;
import br.com.leivas.bancoleivas.resource.TransacaoResource;
import br.com.leivas.bancoleivas.resource.dummy.ContaDummyData;
import br.com.leivas.bancoleivas.resource.dummy.TransacaoDummyData;
import br.com.leivas.bancoleivas.service.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransacaoResourceCreateTest extends BaseMockTest {

    @Mock
    private TransacaoService transacaoService;
    @InjectMocks
    private TransacaoResource transacaoResource;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    // Informações da Transação
    Long fakeNumeroContaOrigem = 350L;
    Conta contaOrigem = ContaDummyData.getEntityA(this.fakeNumeroContaOrigem);
    Long fakeNumeroContaDestino = 1000L;
    Conta contaDestino = ContaDummyData.getEntityB(this.fakeNumeroContaDestino);
    BigDecimal valorTransacao = new BigDecimal("450.50");
    int codigoTransacao = 200;
    TransacaoDTO transacaoDTO;


    @BeforeEach
    @Override
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.transacaoResource).setControllerAdvice(new BancoLeivasExceptionHandler()).build();
    }

    @Override
    public void tearDown() {

    }

    @Test
    public void novaTransacao() throws Exception {
        this.buildTransacaoDTO();
        this.setupTransacaoServiceBehavior();
        MockHttpServletResponse response = this.geraNovaTransacao();
        Logger.getLogger("TESTE JSON").log(Level.INFO, response.getContentAsString());
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

    private void setupTransacaoServiceBehavior() {
        doAnswer(invocation -> this.efetivaTransacao())
                .doReturn(this.efetivaTransacao())
                .when(this.transacaoService.novaTransacao(any(TransacaoDTO.class)));
    }

    private Transacao efetivaTransacao() {
        Transacao transacaoEfetivada = new Transacao().fromDTO(this.transacaoDTO);
        transacaoEfetivada.setContaOrigem(this.contaOrigem);
        transacaoEfetivada.setContaOrigem(this.contaDestino);
        transacaoEfetivada.adicionaNumeroProtocolo(TransacaoDummyData.numeroProtocoloEntity());
        transacaoEfetivada.executaTransacao();
        return transacaoEfetivada;
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
