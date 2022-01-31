package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeloTransacaoDTO extends BaseDTO {

    @NotNull
    @NotEmpty
    private Integer codigo;
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    private String transacaoStrategy;
}
