package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
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
public class TransacaoDTO extends BaseDTO {
    @NotNull
    private Integer codigoTransacao;
    @NotNull
    private Long numeroContaOrigem;
    @NotNull
    private Long numeroContaDestino;
    @NotNull
    private BigDecimal valor;
}
