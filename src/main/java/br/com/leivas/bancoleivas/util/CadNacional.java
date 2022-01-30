package br.com.leivas.bancoleivas.util;

import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.util.separator.CadastroNacionalDigitoSeparator;
import br.com.leivas.bancoleivas.util.separator.CadastroNacionalFilialSeparator;

import static br.com.leivas.bancoleivas.model.reg.CadastroNacional.TipoCadastroNacional.CNPJ;

public class CadNacional implements ICadNacional {

    private final String numeroCompleto;
    private String numeroBase;
    private final String digito;
    private String filial;

    public CadNacional(String numeroCompleto, CadastroNacional.TipoCadastroNacional tipoCadastroNacional) {
        CadastroNacionalDigitoSeparator digitoSeparator = new CadastroNacionalDigitoSeparator();
        digitoSeparator.separate(numeroCompleto);
        this.numeroCompleto = numeroCompleto;
        this.numeroBase = digitoSeparator.getNumeroBase();
        this.digito = digitoSeparator.getDigito();

        if (tipoCadastroNacional == CNPJ) {
            CadastroNacionalFilialSeparator filialSeparator = new CadastroNacionalFilialSeparator();
            this.filial = filialSeparator.separate(numeroCompleto);
            this.numeroBase = this.numeroBase.substring(0, (this.numeroBase.length() - this.filial.length()));
        }
    }

    @Override
    public String completo() {
        return this.numeroCompleto;
    }

    @Override
    public String numero() {
        return this.numeroBase;
    }

    @Override
    public String filial() {
        return this.filial != null ? filial : "";
    }

    @Override
    public String digito() {
        return this.digito;
    }
}
