package br.com.leivas.bancoleivas.exception.custom;

public abstract class BaseCustomException extends RuntimeException {

    public BaseCustomException(Throwable initCause) {
        this.setStackTrace(initCause.getStackTrace());
    }

    public BaseCustomException() {
    }
}
