package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ModeloTransacaoDTO extends BaseDTO {

    @NotNull
    @ApiModelProperty(example = "Código que será utilizado para identificar o tipo de transação, para transferência por exemplo, o valor '201' foi utilizado")
    private Integer codigo;
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Nome de referência para o modelo, como 'Transferência' ")
    private String nome;
    @NotNull
    @ApiModelProperty(example = "Por quem a transação é efetivada, 'CLIENTE' ou 'OPERACIONAL' ")
    private ModeloTransacao.ExecutacaoDaTransacao executacaoDaTransacao;
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Caminho da strategy que executa de fato a transação, como 'br.com.leivas.bancoleivas.strategy.TransferenciaStrategy'")
    private String transacaoStrategy;
}
