package br.com.leivas.bancoleivas.exception.custom;

public class ContaInexistenceException extends BaseCustomException {
    public ContaInexistenceException(Throwable initCause) {
        super(initCause);
    }

    @Override
    public String getMessage() {
        return "Número de conta não consta no sistema";
    }
}
