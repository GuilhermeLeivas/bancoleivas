package br.com.leivas.bancoleivas.util.currency;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public static boolean valorEhMaiorQue(BigDecimal valor, BigDecimal segundoValor) {
        return valor.compareTo(segundoValor) > 0;
    }
}
