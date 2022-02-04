package br.com.leivas.bancoleivas.strategy;

import br.com.leivas.bancoleivas.config.propertie.BancoLeivasProperties;
import br.com.leivas.bancoleivas.exception.custom.ValorParaOperacaoNaoPermitido;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.util.SpringContext;
import br.com.leivas.bancoleivas.util.currency.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.math.BigDecimal;

@Configurable
public class DepositoStrategy implements ITransacaoStrategy {

    @Override
    public void executeStrategy(Transacao transacao) {
        BancoLeivasProperties properties = SpringContext.getBean(BancoLeivasProperties.class);
        BigDecimal valor = transacao.getValor();
        BigDecimal limiteDeposito = properties.getLimite().getLimiteDeposito();
        if (BigDecimalUtil.valorEhMaiorQue(valor, limiteDeposito)) {
            throw new ValorParaOperacaoNaoPermitido(String.format("Valor para depósito Acima de %s não permitido", limiteDeposito.toString()));
        }
        Conta contaDestino = transacao.getContaDestino();
        contaDestino.adicionaFundosTransacao(transacao);
    }
}
