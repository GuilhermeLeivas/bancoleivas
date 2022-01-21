package br.com.leivas.bancoleivas.strategy;

import br.com.leivas.bancoleivas.exception.custom.SaldoInsuficienteException;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.model.reg.Conta;

public class TransferenciaCommand implements ITransacaoStrategy {
    @Override
    public void executeStrategy(Transacao transacao) {
        Conta contaOrigem = transacao.getContaOrigem();
        Conta contaDestino = transacao.getContaDestino();
        if (!contaOrigem.possuiSaldoParaTransacao(transacao)) {
            throw new SaldoInsuficienteException();
        }
        contaOrigem.removeFundosTransacao(transacao);
        contaDestino.adicionaFundosTransacao(transacao);
    }
}
