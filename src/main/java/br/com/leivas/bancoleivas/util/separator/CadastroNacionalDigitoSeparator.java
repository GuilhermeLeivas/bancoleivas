package br.com.leivas.bancoleivas.util.separator;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CadastroNacionalDigitoSeparator implements Separator<String, String[]> {

    private String numeroBase;
    private String digito;

    public CadastroNacionalDigitoSeparator(String numero) {
        this.separate(numero);
    }

    @Override
    public String[] separate(String numero) {
        String[] result = new String[2];
        int finalAteDigitos = numero.length() - 2;
        String digitosVerificadores = numero.substring(finalAteDigitos);
        String base = numero.substring(0, (finalAteDigitos));
        result[0] = base;
        result[1] = digitosVerificadores;
        this.numeroBase = base;
        this.digito = digitosVerificadores;
        return result;
    }
}
