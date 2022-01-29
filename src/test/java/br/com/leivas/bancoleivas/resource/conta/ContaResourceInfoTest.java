package br.com.leivas.bancoleivas.resource.conta;

import br.com.leivas.bancoleivas.exception.custom.ContaInexistenteException;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.resource.BaseMockTest;
import br.com.leivas.bancoleivas.resource.ContaResource;
import br.com.leivas.bancoleivas.resource.dummy.ContaDummyData;
import br.com.leivas.bancoleivas.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContaResourceInfoTest extends BaseMockTest {

    @Mock
    private ContaService contaService;
    @InjectMocks
    private ContaResource contaResource;
    private MockMvc mockMvc;

    @BeforeEach
    @Override
    public void setup() {
        this.tearDown();
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(contaResource).setControllerAdvice(new BancoLeivasExceptionHandler()).build();
    }

    @Override
    public void tearDown() {
        this.contaService = null;
        this.contaResource = null;
        this.mockMvc = null;
    }

    @Test
    public void contaExistenteInfo() throws Exception {
        Long fakeNumeroConta = 1L;
        when(this.contaService.contaInfo(eq(fakeNumeroConta))).thenReturn(ContaDummyData.getEntityA(fakeNumeroConta));
        MockHttpServletResponse response = this.contaInfo(fakeNumeroConta);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void contaInexistenteInfo() throws Exception {
        Long fakeNumeroConta = 2L;
        when(this.contaService.contaInfo(eq(fakeNumeroConta)))
                .thenThrow(new ContaInexistenteException(String.format("Conta %s não existe no sistema", fakeNumeroConta)));
        MockHttpServletResponse response = this.contaInfo(fakeNumeroConta);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    private MockHttpServletResponse contaInfo(Long numeroConta) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/conta/info/{numeroConta}", numeroConta)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }
}
