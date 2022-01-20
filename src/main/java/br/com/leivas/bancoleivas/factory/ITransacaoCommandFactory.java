package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.command.ITransacaoCommand;

public class ITransacaoCommandFactory implements IFactory<Integer, ITransacaoCommand> {

    @Override
    public ITransacaoCommand produce(Integer codigo) {
        return null;
    }
}
