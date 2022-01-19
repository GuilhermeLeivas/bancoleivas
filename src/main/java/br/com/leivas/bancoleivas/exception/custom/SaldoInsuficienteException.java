package br.com.leivas.bancoleivas.exception.custom;

public class SaldoInsuficienteException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Saldo infuficiente para realizar transação";
    }
}
