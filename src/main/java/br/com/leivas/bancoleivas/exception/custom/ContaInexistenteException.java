package br.com.leivas.bancoleivas.exception.custom;

public class ContaInexistenteException extends BancoLeivasException {
    public ContaInexistenteException(Throwable initCause, String message) {
        super(initCause, message);
    }

    public ContaInexistenteException(String message) {
        super(message);
    }
}
