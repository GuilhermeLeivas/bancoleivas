package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeloTransacaoDTO extends BaseDTO {

    private Integer codigo;
    private String nome;
    private String transacaoStrategy;
}
