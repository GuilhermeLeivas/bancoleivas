package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO extends BaseDTO {
    private Integer codigoTransacao;
    private Long numeroContaOrigem;
    private Long numeroContaDestino;
    private BigDecimal valor;
}
