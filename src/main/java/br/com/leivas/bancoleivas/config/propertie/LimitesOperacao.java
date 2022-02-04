package br.com.leivas.bancoleivas.config.propertie;

import java.math.BigDecimal;

public class LimitesOperacao {

    private BigDecimal limiteDeposito;

    public BigDecimal getLimiteDeposito() {
        return limiteDeposito;
    }

    public void setLimiteDeposito(BigDecimal limiteDeposito) {
        this.limiteDeposito = limiteDeposito;
    }
}
