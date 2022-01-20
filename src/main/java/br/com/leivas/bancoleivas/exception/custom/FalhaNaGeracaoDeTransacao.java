package br.com.leivas.bancoleivas.exception.custom;

public class FalhaNaGeracaoDeTransacao extends RuntimeException {

    @Override
    public String getMessage() {
        return "Falha ao tentar criar comando para nova transação.";
    }
}
