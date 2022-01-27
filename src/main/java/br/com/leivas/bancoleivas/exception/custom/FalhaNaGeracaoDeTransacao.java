package br.com.leivas.bancoleivas.exception.custom;

public class FalhaNaGeracaoDeTransacao extends BaseCustomException {

    public FalhaNaGeracaoDeTransacao(Throwable initCause) {
        super(initCause);
    }

    @Override
    public String getMessage() {
        return "Falha ao tentar criar comando para nova transação.";
    }
}
