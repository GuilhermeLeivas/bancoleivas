package br.com.leivas.bancoleivas.factory;

public interface IFactory<IN, OUT> {

    OUT produce(IN input);
}
