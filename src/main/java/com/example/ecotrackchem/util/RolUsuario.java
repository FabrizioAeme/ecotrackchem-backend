package com.example.ecotrackchem.util;

/**
 * Rol del usuario dentro de la plataforma. No se exige logica de
 * autorizacion por rol en esta entrega (T1); el campo queda modelado
 * para habilitar el control de acceso en una etapa posterior.
 */
public enum RolUsuario {
    ADMINISTRADOR,
    OPERADOR_LABORATORIO,
    AUDITOR_AMBIENTAL
}
