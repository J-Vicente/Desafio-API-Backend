package com.nadic.desafiobackend.exceptions;


public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(String message) {
        super(message);
    }
}

