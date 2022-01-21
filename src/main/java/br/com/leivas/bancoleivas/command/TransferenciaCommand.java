package br.com.leivas.bancoleivas.command;

import br.com.leivas.bancoleivas.exception.custom.SaldoInsuficienteException;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.model.reg.Conta;

public class TransferenciaCommand implements ITransacaoCommand {
    @Override
    public void execute(Transacao transacao) {
        Conta contaOrigem = transacao.getContaOrigem();
        Conta contaDestino = transacao.getContaDestino();
        if (!contaOrigem.possuiSaldoParaTransacao(transacao)) {
            throw new SaldoInsuficienteException();
        }
        contaOrigem.removeFundosTransacao(transacao);
        contaDestino.adicionaFundosTransacao(transacao);
    }

    @Override
    public void unExecute(Transacao transacao) {
        Conta contaOrigem = transacao.getContaOrigem();
        Conta contaDestino = transacao.getContaDestino();
        contaOrigem.adicionaFundosTransacao(transacao);
        contaDestino.removeFundosTransacao(transacao);
    }
}
