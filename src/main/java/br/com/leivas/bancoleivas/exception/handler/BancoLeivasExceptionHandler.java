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

    // *********************** HANDLERS DE EXCEPTIONS LIGADAS A CADASTRO NACIONAL ***********************
    @ExceptionHandler({CadastroNacionalInvalido.class})
    public ResponseEntity<Object> handleCadastroNacionalInvalido(CadastroNacionalInvalido ex, WebRequest webRequest) {
        Erro erro = new Erro("Cadastro nacional inválido, informe um documento válido!", this.getCause(ex));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, webRequest);
    }

    @ExceptionHandler({FalhaAoConverterCadastroNacional.class})
    public ResponseEntity<Object> handleFalhaAoConverterCadastroNacional(FalhaAoConverterCadastroNacional ex, WebRequest webRequest) {
        Erro erro = new Erro("Erro interno ao converter Cadastro nacional, contate os desenvolvedores!", this.getCause(ex));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    // *********************** HANDLERS DE EXCEPTIONS LIGADAS A PESSOA ***********************
    @ExceptionHandler({ClienteJaCadastradoNoSistema.class})
    public ResponseEntity<Object> handleClienteJaCadastradoNoSistema(ClienteJaCadastradoNoSistema ex, WebRequest webRequest) {
        Erro erro = new Erro(ex.getMessage(), this.getCause(ex));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    // *********************** HANDLERS DE EXCEPTIONS LIGADAS A CONTA ***********************
    @ExceptionHandler({ContaInexistenteException.class})
    public ResponseEntity<Object> handleContaInexistenceException(ContaInexistenteException ex, WebRequest webRequest) {
        Erro erro = new Erro(ex.getMessage(), this.getCause(ex));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler({SaldoInsuficienteException.class})
    public ResponseEntity<Object> handleSaldoInsuficienteException(SaldoInsuficienteException ex, WebRequest webRequest) {
        Erro erro = new Erro("Saldo insuficiente para completar operação!", this.getCause(ex));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
    }

    // *********************** HANDLERS DE EXCEPTIONS LIGADAS A TRANSAÇÃO ***********************
    @ExceptionHandler({FalhaNaGeracaoDeTransacao.class})
    public ResponseEntity<Object> handleFalhaNaGeracaoDeTransacao(FalhaNaGeracaoDeTransacao ex, WebRequest webRequest) {
        Erro erro = new Erro(ex.getMessage(), this.getCause(ex));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
    }

    private String getCause(Throwable ex) {
        return ex.getCause() != null ? ex.getCause().toString() : ex.toString();
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
