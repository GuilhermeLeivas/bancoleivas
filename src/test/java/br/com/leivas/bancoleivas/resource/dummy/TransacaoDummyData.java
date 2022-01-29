package br.com.leivas.bancoleivas.resource.dummy;

import br.com.leivas.bancoleivas.model.fin.NumeroProtocolo;

import java.util.Random;

public class TransacaoDummyData {


    public static Long numeroProtocolo() {
        return new Random().nextLong();
    }

    public static NumeroProtocolo numeroProtocoloEntity() {
        return new NumeroProtocolo(numeroProtocolo());
    }
}
