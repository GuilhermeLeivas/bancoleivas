package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class TransacaoDTO extends BaseDTO {

    private UUID numeroContaOrigem;
    private UUID numeroContaDestino;
    private BigDecimal valor;

}
