package br.com.leivas.bancoleivas.exception.custom;

public abstract class BancoLeivasException extends RuntimeException {

    private String message;

    public BancoLeivasException(Throwable initCause) {
        this.setStackTrace(initCause.getStackTrace());
    }

    public BancoLeivasException(Throwable initCause, String message) {
        this.setStackTrace(initCause.getStackTrace());
        this.message = message;
    }

    public BancoLeivasException(String message) {
        this.message = message;
    }

    public BancoLeivasException() {
    }

    @Override
    public String getMessage() {
        return this.message != null ? this.message : super.getMessage();
    }
}
