package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroNacionalDTO extends BaseDTO {

    private String numero;
    private String emissor;
    private CadastroNacional.TipoCadastroNacional tipo;
}
