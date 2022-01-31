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
    @NotEmpty
    private Integer codigoTransacao;
    @NotNull
    @NotEmpty
    private Long numeroContaOrigem;
    @NotNull
    @NotEmpty
    private Long numeroContaDestino;
    @NotNull
    @NotEmpty
    private BigDecimal valor;
}
