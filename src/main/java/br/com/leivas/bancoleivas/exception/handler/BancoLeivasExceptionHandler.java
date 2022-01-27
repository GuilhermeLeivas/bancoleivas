package br.com.leivas.bancoleivas.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BancoLeivasExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex, Object body, @NonNull HttpHeaders headers,
                                                             @NonNull HttpStatus status, @NonNull WebRequest request) {
        Erro erro = new Erro("Falha interna no servidor, entre em contato com os Desenvolvedores!", ex.getCause().toString());
        return super.handleExceptionInternal(ex, erro, headers, status, request);
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
