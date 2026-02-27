package org.example.service;

import org.example.Main;
import org.example.exception.OpcionInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TareaMainTest {

    private Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    // =========================================================
    // leerEntrada()
    // =========================================================

    @Test
    @DisplayName("Debería capturar correctamente el texto ingresado por el usuario")
    void testLeerEntrada_EntradaValida_RetornaTextoCapturado() {
        // ARRANGE: Simular entrada del usuario con un Scanner
        String inputSimulado = "Tarea de Matemáticas\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(inputSimulado.getBytes()));

        // ACT: Leer la entrada simulada
        String resultado = Main.leerEntrada(sc, "");

        // ASSERT: El texto capturado debe coincidir con la entrada simulada
        assertEquals("Tarea de Matemáticas", resultado);
    }

    // =========================================================
    // convertirEntero()
    // =========================================================

    @Test
    @DisplayName("Debería convertir un String numérico a int exitosamente")
    void testConvertirEntero_StringNumerico_RetornaEntero() {
        // ARRANGE: String que representa un número válido
        String input = "123";

        // ACT: Convertir el String a entero
        int resultado = Main.convertirEntero(input);

        // ASSERT: El resultado debe ser el entero 123
        assertEquals(123, resultado);
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el String no es un número")
    void testConvertirEntero_StringNoNumerico_LanzaOpcionInvalidoException() {
        // ARRANGE: String con texto que no puede convertirse a entero
        String input = "abc";

        // ACT: Intentar convertir el String inválido
        OpcionInvalidoException exception = assertThrows(OpcionInvalidoException.class,
                () -> Main.convertirEntero(input));

        // ASSERT: El mensaje de la excepción debe contener el valor inválido ingresado
        assertEquals("abc", exception.getMessage());
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el String está vacío")
    void testConvertirEntero_StringVacio_LanzaOpcionInvalidoException() {
        // ARRANGE: String vacío
        String input = "";

        // ACT + ASSERT: Debe lanzar OpcionInvalidoException
        assertThrows(OpcionInvalidoException.class,
                () -> Main.convertirEntero(input));
    }
}