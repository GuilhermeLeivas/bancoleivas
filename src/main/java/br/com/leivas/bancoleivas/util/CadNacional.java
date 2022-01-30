package br.com.leivas.bancoleivas.util;

import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.util.separator.CadastroNacionalDigitoSeparator;
import br.com.leivas.bancoleivas.util.separator.CadastroNacionalFilialSeparator;

public class CadNacional implements ICadNacional {

    private final String numeroCompleto;
    private final String numeroBase;
    private final String digito;
    private String filial;

    public CadNacional(CadastroNacional cadastroNacional) {
        CadastroNacionalDigitoSeparator digitoSeparator = new CadastroNacionalDigitoSeparator();
        digitoSeparator.separate(cadastroNacional.getNumeroCompleto());
        this.numeroCompleto = cadastroNacional.getNumeroCompleto();
        this.numeroBase = digitoSeparator.getNumeroBase();
        this.digito = digitoSeparator.getDigito();

        if (cadastroNacional.getTipo() == CadastroNacional.TipoCadastroNacional.CNPJ) {
            CadastroNacionalFilialSeparator filialSeparator = new CadastroNacionalFilialSeparator();
            this.filial = filialSeparator.separate(cadastroNacional.getNumeroCompleto());
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
