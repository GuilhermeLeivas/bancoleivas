package br.com.leivas.bancoleivas.command;

import br.com.leivas.bancoleivas.exception.custom.SaldoInsuficienteException;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.model.reg.Conta;

public class TransferenciaCommand implements ITransacaoCommand {
    @Override
    public void executeTransacao(Transacao transacao) {
        Conta contaOrigem = transacao.getContaOrigem();
        Conta contaDestino = transacao.getContaDestino();
        if (!contaOrigem.possuiSaldoParaTransacao(transacao)) {
            throw new SaldoInsuficienteException();
        }
        contaOrigem.removeFundos(transacao.getValor());
        contaDestino.adicionaFundos(transacao.getValor());
    }

    @Override
    public void unexecuteTransacao(Transacao transacao) {
        Conta contaOrigem = transacao.getContaOrigem();
        Conta contaDestino = transacao.getContaDestino();
        contaOrigem.adicionaFundos(transacao.getValor());
        contaDestino.removeFundos(transacao.getValor());
    }
}
