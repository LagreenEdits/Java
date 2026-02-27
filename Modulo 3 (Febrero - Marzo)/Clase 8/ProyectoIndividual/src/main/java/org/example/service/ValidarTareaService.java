package org.example.service;

import org.example.exception.DatosInvalidosException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ValidarTareaService {

    private static final Logger log = LoggerFactory.getLogger(ValidarTareaService.class.getName());

    public static void validarNombre(String nombre){
        if (nombre == null || nombre.isEmpty()){
            log.error("Error de validación: El nombre está vacío o es nulo.");
            throw new DatosInvalidosException("El nombre no puede ser nulo o estar vacio, Por favor escribe un nombre valido");
        }
    }

    public static void validarDescripcion(String descripcion){
        if (descripcion == null || descripcion.isEmpty()){
            log.error("Error de validación: La descripción está vacía o es nula.");
            throw new DatosInvalidosException("La descripción no puede ser nulo o estar vacio, Por favor escribe una descripcion valida");
        }
    }
}
