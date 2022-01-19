package br.com.leivas.bancoleivas.command;

import br.com.leivas.bancoleivas.model.fin.Transacao;

public interface ITransacaoCommand {

    void executeTransacao(Transacao transacao);

    void unexecuteTransacao(Transacao transacao);
}
