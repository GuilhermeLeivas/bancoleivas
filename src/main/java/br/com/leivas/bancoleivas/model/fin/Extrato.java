package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.model.reg.Conta;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Builder
public class Extrato implements Serializable {
    private Conta conta;
    private Set<Transacao> transacoes;
    private Date dtInicial;
    private Date dtFinal;
}
