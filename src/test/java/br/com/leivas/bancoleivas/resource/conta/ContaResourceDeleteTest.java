package br.com.leivas.bancoleivas.resource.conta;

import br.com.leivas.bancoleivas.exception.custom.ContaInexistenteException;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.resource.BaseMockTest;
import br.com.leivas.bancoleivas.resource.ContaResource;
import br.com.leivas.bancoleivas.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContaResourceDeleteTest extends BaseMockTest {

    @Mock
    private ContaService contaService;
    @InjectMocks
    private ContaResource contaResource;
    @Autowired
    private MessageSource messageSource;
    private MockMvc mockMvc;

    @BeforeEach
    @Override
    public void setup() {
        this.tearDown();
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(contaResource).setControllerAdvice(new BancoLeivasExceptionHandler(messageSource)).build();
    }

    @Override
    public void tearDown() {
        this.contaService = null;
        this.contaResource = null;
        this.mockMvc = null;
    }

    @Test
    public void deletaContaExistente() throws Exception {
        Long fakeNumeroConta = 1L;
        doNothing().when(this.contaService).deletaConta(fakeNumeroConta);
        MockHttpServletResponse response = this.tryDeleteConta(fakeNumeroConta);
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    public void deletaContaInexistente() throws Exception {
        Long fakeNumeroConta = 2L;
        doThrow(new ContaInexistenteException(String.format("Conta %s n??o existe no sistema", fakeNumeroConta)))
                .when(this.contaService).deletaConta(eq(fakeNumeroConta));
        MockHttpServletResponse response = this.tryDeleteConta(fakeNumeroConta);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    private MockHttpServletResponse tryDeleteConta(Long numeroConta) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/conta/{numeroConta}", numeroConta);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }
}
