package br.com.leivas.bancoleivas.util.separator;

import br.com.leivas.bancoleivas.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadastroNacionalDigitoSeparatorTest extends BaseTest {

    @Test
    public void separateCPF() {
        String cpf = "76626415034";
        String[] result = new CadastroNacionalDigitoSeparator().separate(cpf);
        String numeroBase = result[0];
        String digitosVerificadores = result[1];
        assertEquals(numeroBase, "766264150");
        assertEquals(digitosVerificadores, "34");
    }

    @Test
    public void separateCNPJ() {
        String cnpj = "20139621000168";
        String[] result = new CadastroNacionalDigitoSeparator().separate(cnpj);
        String numeroBase = result[0];
        String digitosVerificadores = result[1];
        assertEquals(numeroBase, "201396210001");
        assertEquals(digitosVerificadores, "68");
    }
}