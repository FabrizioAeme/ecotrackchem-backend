package com.example.ecotrackchem.exception;

// Http 404 - Recurso no encontrado//
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
