package br.com.leivas.bancoleivas.exception.custom;

public class CadastroNacionalInvalido extends BancoLeivasException {

    public CadastroNacionalInvalido(Throwable initCause) {
        super(initCause);
    }

    @Override
    public String getMessage() {
        return "Documento informado não é válido!";
    }
}
