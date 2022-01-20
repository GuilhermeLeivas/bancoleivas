package br.com.leivas.bancoleivas.command;

public interface ICommand<T> {

    void execute(T t);

    void unExecute(T t);
}
