package com.example.ecotrackchem.exception;

/**
 * Se lanza cuando se busca por id una entidad que no existe.
 * El GlobalExceptionHandler la traduce a un HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
