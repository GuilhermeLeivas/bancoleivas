package br.com.leivas.bancoleivas.util.parser;

public interface Parser<IN, OUT> {

    OUT parse(IN input);
}
