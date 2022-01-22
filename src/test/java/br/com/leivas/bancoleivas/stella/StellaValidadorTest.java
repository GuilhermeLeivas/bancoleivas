package br.com.leivas.bancoleivas.stella;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StellaValidadorTest {

    @Test
    public void cpfValidadorTest() {
        String cpf = "76626415034";
        boolean isCPFEligible = new CPFValidator().isEligible(cpf);
        assertTrue(isCPFEligible);
    }

    @Test
    public void cnpjValidadorTest() {
        String cnpj = "20139621000168";
        boolean isCNPJEligigle = new CNPJValidator().isEligible(cnpj);
        assertTrue(isCNPJEligigle);
    }
}
