package br.com.leivas.bancoleivas.exception.custom;

public class ContaInexistenceException extends BancoLeivasException {
    public ContaInexistenceException(Throwable initCause, String message) {
        super(initCause, message);
    }

    public ContaInexistenceException(String message) {
        super(message);
    }
}
