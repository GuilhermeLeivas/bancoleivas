package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class PessoaDTO extends BaseDTO {

    private CadastroNacionalDTO cadastroNacional;
}
