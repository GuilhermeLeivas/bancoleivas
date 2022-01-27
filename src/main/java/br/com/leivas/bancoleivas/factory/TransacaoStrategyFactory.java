package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.strategy.ITransacaoStrategy;
import br.com.leivas.bancoleivas.exception.custom.FalhaNaGeracaoDeTransacao;
import br.com.leivas.bancoleivas.exception.custom.TransacaoNaoEncontradaException;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class TransacaoStrategyFactory implements IFactory<Integer, ITransacaoStrategy> {

    private enum IdentificadorTransacao {
        TRANSFERENCIA(200, "TransferenciaStrategy"),
        DEPOSITO(201, "DepositoStrategy");
        private final Integer codigo;
        private final String transacaoCommand;

        public static IdentificadorTransacao identificadorTransacaoPorCodigo(Integer codigo) {
            Optional<IdentificadorTransacao> possivelTransacao = Arrays
                    .stream(IdentificadorTransacao.values())
                    .filter(identificador -> identificador.codigo.equals(codigo))
                    .findFirst();
            if (possivelTransacao.isEmpty()) {
                throw new TransacaoNaoEncontradaException();
            }
            return possivelTransacao.get();
        }

        private IdentificadorTransacao(Integer codigo, String transacaoCommand) {
            this.codigo = codigo;
            this.transacaoCommand = transacaoCommand;
        }

        public String getTransacaoCommand() {
            return transacaoCommand;
        }
    }

    @Override
    public ITransacaoStrategy produce(Integer codigo) {
        IdentificadorTransacao transacaoCandidata = IdentificadorTransacao.identificadorTransacaoPorCodigo(codigo);
        String comandoDaTransacao = transacaoCandidata.getTransacaoCommand();
        try {
            Class<?> classRef = Class.forName(comandoDaTransacao);
            Constructor<?> commandConstructor = classRef.getConstructor();
            return (ITransacaoStrategy) commandConstructor.newInstance();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, "Falha ao produzir comando para transação");
            throw new FalhaNaGeracaoDeTransacao(ex);
        }
    }
}
