package br.com.leivas.bancoleivas.exception.custom;

public class CadastroNacionalInvalido extends RuntimeException {

    @Override
    public String getMessage() {
        return "Documento informado não é válido!";
    }
}
