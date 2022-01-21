package br.com.leivas.bancoleivas.stella;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StellaValidadorTest {

    @Test
    public void cpfValidadorTest() {
        String cpf = "04539032070";
        boolean isCPFEligible = new CPFValidator().isEligible(cpf);
        assertTrue(isCPFEligible);
    }

    @Test
    public void cnpjValidadorTest() {
        String cnpj = "08644467000186";
        boolean isCNPJEligigle = new CNPJValidator().isEligible(cnpj);
        assertTrue(isCNPJEligigle);
    }
}
