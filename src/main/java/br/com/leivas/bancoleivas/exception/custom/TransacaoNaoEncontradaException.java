package br.com.leivas.bancoleivas.exception.custom;

public class TransacaoNaoEncontradaException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Transação informada não existe";
    }
}
