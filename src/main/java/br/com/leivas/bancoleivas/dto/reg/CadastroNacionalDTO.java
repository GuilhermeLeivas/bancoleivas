package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.model.reg.ICadNacional;
import br.com.leivas.bancoleivas.util.separator.CadastroNacionalDigitoSeparator;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroNacionalDTO extends BaseDTO implements ICadNacional {

    private String numero;
    private String emissor;
    private CadastroNacional.TipoCadastroNacional tipo;

    @Override
    public String numero() {
        return this.numero;
    }

    @Override
    public String digito() {
        CadastroNacionalDigitoSeparator separator = new CadastroNacionalDigitoSeparator();
        separator.separate(this.numero);
        return separator.getDigito();
    }
}
