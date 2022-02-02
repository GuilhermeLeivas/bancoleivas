package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class TransacaoDTO extends BaseDTO {
    @NotNull
    @ApiModelProperty(example = "Para transação operacional '200' que é depósito / Para transação cliente '201' que é transferência ")
    private Integer codigoTransacao;
    @NotNull
    @ApiModelProperty(example = "1000")
    private Long numeroContaOrigem;
    @NotNull
    @ApiModelProperty(example = "450")
    private Long numeroContaDestino;
    @NotNull
    @ApiModelProperty(example = "100.0")
    private BigDecimal valor;
}
