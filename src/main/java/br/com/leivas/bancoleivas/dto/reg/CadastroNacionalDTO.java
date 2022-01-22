package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CadastroNacionalDTO extends BaseDTO {

    private String numero;
    private String emissor;
    private CadastroNacional.TipoCadastroNacional tipo;
}
