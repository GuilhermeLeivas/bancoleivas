package br.com.leivas.bancoleivas.exception.handler;

import br.com.leivas.bancoleivas.exception.custom.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BancoLeivasExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({CadastroNacionalInvalido.class})
    public ResponseEntity<Object> handleCadastroNacionalInvalido(CadastroNacionalInvalido ex, WebRequest webRequest) {
        Erro erro = new Erro("Cadastro nacional inválido, informe um documento válido!", ex.getCause().toString());
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, webRequest);
    }

    @ExceptionHandler({FalhaAoConverterCadastroNacional.class})
    public ResponseEntity<Object> handleFalhaAoConverterCadastroNacional(FalhaAoConverterCadastroNacional ex, WebRequest webRequest) {
        Erro erro = new Erro("Erro interno ao converter Cadastro nacional, contate os desenvolvedores!", ex.getCause().toString());
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler({ClienteJaCadastradoNoSistema.class})
    public ResponseEntity<Object> handleClienteJaCadastradoNoSistema(ClienteJaCadastradoNoSistema ex, WebRequest webRequest) {
        Erro erro = new Erro("Cliente já possuí cadastro ativo no sistema!", ex.getCause().toString());
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler({ContaInexistenceException.class})
    public ResponseEntity<Object> handleContaInexistenceException(ContaInexistenceException ex, WebRequest webRequest) {
        Erro erro = new Erro(ex.getMessage(), ex.getCause().toString());
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler({SaldoInsuficienteException.class})
    public ResponseEntity<Object> handleSaldoInsuficienteException(SaldoInsuficienteException ex, WebRequest webRequest) {
        Erro erro = new Erro("Saldo insuficiente para completar operação!", ex.getCause().toString());
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
    }

    @ExceptionHandler({FalhaNaGeracaoDeTransacao.class})
    public ResponseEntity<Object> handleFalhaNaGeracaoDeTransacao(FalhaNaGeracaoDeTransacao ex, WebRequest webRequest) {
        Erro erro = new Erro(ex.getMessage(), ex.getCause().toString());
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
    }

    // Facilitador para retornar mensagens de erro no corpo da resposta http.
    private record Erro(String mensagemUsuario,
                        String mensagemDesenvolvedor) {

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }
}
