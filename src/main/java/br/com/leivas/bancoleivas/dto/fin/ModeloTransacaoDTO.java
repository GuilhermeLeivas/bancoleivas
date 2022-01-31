package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer codigo;
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @JsonIgnore
    private ModeloTransacao.ExecutacaoDaTransacao executacaoDaTransacao;
    @NotNull
    @NotEmpty
    private String transacaoStrategy;
}
