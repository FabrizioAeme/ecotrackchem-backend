package com.example.ecotrackchem.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.ecotrackchem.exception.ResourceNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 400  Errores de validacion de @Valid en los DTO de entrada
    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo, mensaje);
        });

        return construirRespuesta(HttpStatus.BAD_REQUEST, "Error de validacion", errores);
    }

    // 404 - Recurso no encontrado (por ejemplo, GET/PUT/DELETE con un id inexistente)//
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    // 409 - Conflicto de integridad de datos como codigo/correo duplicado//
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return construirRespuesta(HttpStatus.CONFLICT,
                "El registro entra en conflicto con datos existentes (posible valor duplicado)", null);
    }

    // 401 - Credenciales invalidas en /auth/authenticate//
    @ExceptionHandler({ BadCredentialsException.class, AuthenticationException.class })
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return construirRespuesta(HttpStatus.UNAUTHORIZED, "Credenciales invalidas", null);
    }

    // 500 - Cualquier otro error //
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocurrio un error interno en el servidor", Map.of("detalle", String.valueOf(ex.getMessage())));
    }

    private ResponseEntity<Object> construirRespuesta(HttpStatus status, String error, Map<String, String> detalles) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        if (detalles != null) {
            response.put("details", detalles);
        }
        return new ResponseEntity<>(response, status);
    }
}
