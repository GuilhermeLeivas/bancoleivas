import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StellaValidadorTest {

    @Test
    public void cpfValidadorTest() {
        String cpfNaoFormatado = "04539032070";
        String cpfFormatado = "045.390.320-70";
        boolean isCPFNaoFormatadoEligible = new CPFValidator().isEligible(cpfNaoFormatado);
        boolean isCPFFormatadoEligible = new CPFValidator().isEligible(cpfFormatado);
        assertTrue(isCPFNaoFormatadoEligible);
        assertTrue(isCPFFormatadoEligible);
    }

    @Test
    public void cnpjValidadorTest() {
        String cnpjNaoFormatado = "61767596000144";
        String cnpjFormatado = "61.767.596/0001-44";
        boolean isCNPJNaoFormatadoEligible = new CPFValidator().isEligible(cnpjNaoFormatado);
        boolean isCNPJFormatadoEligible = new CNPJValidator().isEligible(cnpjFormatado);
        assertTrue(isCNPJNaoFormatadoEligible);
        assertTrue(isCNPJFormatadoEligible);
    }
}
