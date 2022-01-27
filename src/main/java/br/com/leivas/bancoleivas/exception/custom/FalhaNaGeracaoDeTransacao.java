package br.com.leivas.bancoleivas.exception.custom;

public class FalhaNaGeracaoDeTransacao extends BancoLeivasException {

    public FalhaNaGeracaoDeTransacao(Throwable initCause, String message) {
        super(initCause, message);
    }

}
