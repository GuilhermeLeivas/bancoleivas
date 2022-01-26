package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.dto.deserialize.PessoaDTODeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(using = PessoaDTODeserializer.class)
public abstract class PessoaDTO extends BaseDTO {

    private CadastroNacionalDTO cadastroNacional;

}
