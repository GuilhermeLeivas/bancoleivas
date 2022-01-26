package br.com.leivas.bancoleivas.exception.custom;

public class FalhaAoConverterCadastroNacional extends RuntimeException {

    @Override
    public String getMessage() {
        return "Falha ao converter documento!";
    }


}
