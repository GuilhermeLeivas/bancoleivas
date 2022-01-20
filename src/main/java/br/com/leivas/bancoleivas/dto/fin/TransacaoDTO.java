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
@NoArgsConstructor
public class TransacaoDTO extends BaseDTO {

    private UUID numeroContaOrigem;
    private UUID numeroContaDestino;
    private BigDecimal valor;
    private UUID numeroProtocolo;

}
