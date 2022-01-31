package br.com.leivas.bancoleivas.exception.custom;

public class TransacaoSemImplementacaoConhecida extends BancoLeivasException {

    public TransacaoSemImplementacaoConhecida(Throwable initCause, String message) {
        super(initCause, message);
    }

}
