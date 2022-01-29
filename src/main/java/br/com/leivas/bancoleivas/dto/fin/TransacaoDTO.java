package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
public class TransacaoDTO extends BaseDTO {
    private Integer codigoTransacao;
    private Long numeroContaOrigem;
    private Long numeroContaDestino;
    private Long numeroProtocolo;
    private BigDecimal valor;

}
