package br.com.leivas.bancoleivas.exception.custom;

public class TransacaoNaoEncontradaException extends RuntimeException {

    public TransacaoNaoEncontradaException(String message) {
        super(message);
    }
}
