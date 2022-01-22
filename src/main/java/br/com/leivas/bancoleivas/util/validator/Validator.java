package br.com.leivas.bancoleivas.util.validator;

public interface Validator<I> {

    Boolean isValid(I input);
}
