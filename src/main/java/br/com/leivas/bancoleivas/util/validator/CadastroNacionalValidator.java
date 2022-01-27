package br.com.leivas.bancoleivas.util.validator;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.leivas.bancoleivas.exception.custom.CadastroNacionalInvalido;

public class CadastroNacionalValidator implements Validator<String> {
    @Override
    public Boolean isValid(String input) {
        try {
            new CPFValidator().assertValid(input);
        } catch (Exception ex) {
            try {
                new CNPJValidator().assertValid(input);
            } catch (Exception e) {
                throw new CadastroNacionalInvalido(e);
            }
        }
        return true;
    }
}
