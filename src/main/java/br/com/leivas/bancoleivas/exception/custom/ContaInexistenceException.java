package br.com.leivas.bancoleivas.exception.custom;

public class ContaInexistenceException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Número de conta não consta no sistema";
    }
}
