package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.dto.deserialize.PessoaDTODeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO extends BaseDTO {

    @JsonDeserialize(using = PessoaDTODeserializer.class)
    private PessoaDTO pessoa;
}
