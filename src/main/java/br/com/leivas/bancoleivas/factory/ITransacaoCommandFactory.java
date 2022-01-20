package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.command.ITransacaoCommand;
import br.com.leivas.bancoleivas.exception.custom.FalhaNaGeracaoDeTransacao;
import br.com.leivas.bancoleivas.exception.custom.TransacaoNaoEncontradaException;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ITransacaoCommandFactory implements IFactory<Integer, ITransacaoCommand> {

    private enum IdentificadorTransacao {
        TRANSFERENCIA(200, "TransferenciaCommand"),
        DEPOSITO(201, "DepositoCommand");
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
    public ITransacaoCommand produce(Integer codigo) {
        IdentificadorTransacao transacaoCandidata = IdentificadorTransacao.identificadorTransacaoPorCodigo(codigo);
        String comandoDaTransacao = transacaoCandidata.getTransacaoCommand();
        try {
            Class<?> classRef = Class.forName(comandoDaTransacao);
            Constructor<?> commandConstructor = classRef.getConstructor();
            return (ITransacaoCommand) commandConstructor.newInstance();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, "Falha ao produzir comando para transação");
            throw new FalhaNaGeracaoDeTransacao();
        }
    }
}
