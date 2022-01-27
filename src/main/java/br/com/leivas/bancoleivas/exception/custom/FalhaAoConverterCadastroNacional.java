package br.com.leivas.bancoleivas.exception.custom;

public class FalhaAoConverterCadastroNacional extends BancoLeivasException {

    public FalhaAoConverterCadastroNacional(Throwable initCause) {
        super(initCause);
    }

    @Override
    public String getMessage() {
        return "Falha ao converter documento!";
    }


}
