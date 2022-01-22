package br.com.leivas.bancoleivas.util.separator;

public interface Separator<IN, OUT> {

    OUT separate(IN input);
}
