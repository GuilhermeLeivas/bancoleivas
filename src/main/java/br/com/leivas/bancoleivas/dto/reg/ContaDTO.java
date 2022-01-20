package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class ContaDTO extends BaseDTO {

    private PessoaDTO pessoa;
}
