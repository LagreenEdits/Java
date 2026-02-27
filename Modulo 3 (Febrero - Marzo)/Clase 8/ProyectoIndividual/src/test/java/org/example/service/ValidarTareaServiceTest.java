package org.example.service;

import org.example.exception.DatosInvalidosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidarTareaServiceTest {

    private ValidarTareaService validarTareaService;

    @BeforeEach
    void setUp() {
        validarTareaService = new ValidarTareaService();
    }

    // =========================================================
    // validarNombre()
    // =========================================================

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre es nulo")
    void testValidarNombre_NombreNulo_LanzaDatosInvalidosException() {
        // ARRANGE: Nombre nulo
        String nombre = null;

        // ACT + ASSERT: Debe lanzar DatosInvalidosException
        assertThrows(DatosInvalidosException.class,
                () -> validarTareaService.validarNombre(nombre));
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el nombre está vacío")
    void testValidarNombre_NombreVacio_LanzaDatosInvalidosException() {
        // ARRANGE: Nombre vacío
        String nombre = "";

        // ACT + ASSERT: Debe lanzar DatosInvalidosException
        assertThrows(DatosInvalidosException.class,
                () -> validarTareaService.validarNombre(nombre));
    }

    @Test
    @DisplayName("Debería pasar la validación con un nombre correcto")
    void testValidarNombre_NombreValido_NoLanzaExcepcion() {
        // ARRANGE: Nombre válido
        String nombre = "Juan";

        // ACT + ASSERT: No debe lanzar ninguna excepción
        assertDoesNotThrow(() -> validarTareaService.validarNombre(nombre));
    }

    // =========================================================
    // validarDescripcion()
    // =========================================================

    @Test
    @DisplayName("Debería lanzar excepción cuando la descripción es nula")
    void testValidarDescripcion_DescripcionNula_LanzaDatosInvalidosException() {
        // ARRANGE: Descripción nula
        String descripcion = null;

        // ACT + ASSERT: Debe lanzar DatosInvalidosException
        assertThrows(DatosInvalidosException.class,
                () -> validarTareaService.validarDescripcion(descripcion));
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando la descripción está vacía")
    void testValidarDescripcion_DescripcionVacia_LanzaDatosInvalidosException() {
        // ARRANGE: Descripción vacía
        String descripcion = "";

        // ACT + ASSERT: Debe lanzar DatosInvalidosException
        assertThrows(DatosInvalidosException.class,
                () -> validarTareaService.validarDescripcion(descripcion));
    }

    @Test
    @DisplayName("Debería pasar la validación con una descripción correcta")
    void testValidarDescripcion_DescripcionValida_NoLanzaExcepcion() {
        // ARRANGE: Descripción válida
        String descripcion = "Estudiar para el examen del lunes";

        // ACT + ASSERT: No debe lanzar ninguna excepción
        assertDoesNotThrow(() -> validarTareaService.validarDescripcion(descripcion));
    }
}