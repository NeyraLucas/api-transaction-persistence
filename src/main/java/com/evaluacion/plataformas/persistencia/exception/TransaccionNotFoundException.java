package com.evaluacion.plataformas.persistencia.exception;

public class TransaccionNotFoundException extends RuntimeException {

    public TransaccionNotFoundException(String message) {
        super(message);
    }
}
