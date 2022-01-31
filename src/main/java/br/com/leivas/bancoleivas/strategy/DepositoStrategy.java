package br.com.leivas.bancoleivas.strategy;

import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.model.reg.Conta;

public class DepositoStrategy implements ITransacaoStrategy {

    @Override
    public void executeStrategy(Transacao transacao) {
        Conta contaDestino = transacao.getContaDestino();
        contaDestino.adicionaFundosTransacao(transacao);
    }
}
