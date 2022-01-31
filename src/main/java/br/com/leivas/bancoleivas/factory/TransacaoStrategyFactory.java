package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.exception.custom.TransacaoSemImplementacaoConhecida;
import br.com.leivas.bancoleivas.strategy.ITransacaoStrategy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public final class TransacaoStrategyFactory implements IFactory<String, ITransacaoStrategy> {

    @Override
    public ITransacaoStrategy produce(String strategy) {
        try {
            Class<?> classRef = Class.forName(strategy);
            Constructor<?> commandConstructor = classRef.getConstructor();
            return (ITransacaoStrategy) commandConstructor.newInstance();
        } catch (Exception ex) {
            String message = String.format("Falha ao implementação %s, tente novamente mais tarde!", strategy);
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, message);
            throw new TransacaoSemImplementacaoConhecida(ex, message);
        }
    }
}
