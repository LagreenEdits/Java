package org.example.service;

import org.example.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TareaServiceTest {

    private TareaService tareaService;

    @BeforeEach
    void setUp() {
        tareaService = new TareaService();
    }

    // =========================================================
    // agregarTarea()
    // =========================================================

    @Test
    @DisplayName("Debería agregar una tarea correctamente con datos válidos")
    void testAgregarTarea_DatosValidos_TareaAgregada() {
        // ARRANGE: Preparar nombre y descripción válidos
        String nombre = "Matemáticas";
        String descripcion = "Tarea para el lunes";

        // ACT: Ejecutar la acción de agregar
        assertDoesNotThrow(() -> tareaService.agregarTarea(nombre, descripcion));

        // ASSERT: La lista debe tener exactamente 1 tarea
        assertEquals(1, tareaService.listarTareas().size());
    }

    @Test
    @DisplayName("Debería lanzar excepción al agregar tarea con nombre vacío")
    void testAgregarTarea_NombreVacio_LanzaDatosInvalidosException() {
        // ARRANGE: Nombre vacío, descripción válida
        String nombre = "";
        String descripcion = "Descripción válida";

        // ACT + ASSERT: Debe lanzar DatosInvalidosException
        assertThrows(DatosInvalidosException.class,
                () -> tareaService.agregarTarea(nombre, descripcion));
    }

    @Test
    @DisplayName("Debería lanzar excepción al agregar tarea con nombre nulo")
    void testAgregarTarea_NombreNulo_LanzaDatosInvalidosException() {
        // ARRANGE: Nombre nulo, descripción válida
        String nombre = null;
        String descripcion = "Descripción válida";

        // ACT + ASSERT: Debe lanzar DatosInvalidosException
        assertThrows(DatosInvalidosException.class,
                () -> tareaService.agregarTarea(nombre, descripcion));
    }

    // =========================================================
    // buscarTareaId()
    // =========================================================

    @Test
    @DisplayName("Debería lanzar excepción al buscar un ID que no existe")
    void testBuscarTareaId_IdNoExistente_LanzaTareaNoEncontradaException() {
        // ARRANGE: El ID 99 no existe en una lista nueva
        int idInexistente = 99;

        // ACT + ASSERT: Debe lanzar TareaNoEncontradaException
        assertThrows(TareaNoEncontradaException.class,
                () -> tareaService.buscarTareaId(idInexistente));
    }

    // =========================================================
    // completarTarea()
    // =========================================================

    @Test
    @DisplayName("Debería marcar una tarea como completada exitosamente")
    void testCompletarTarea_TareaExistente_EstadoCompletadoTrue() {
        // ARRANGE: Agregar tarea y obtener su ID
        tareaService.agregarTarea("Estudiar", "Preparar examen de Java");
        int id = tareaService.listarTareas().get(0).getId();

        // ACT: Completar la tarea
        tareaService.completarTarea(id);

        // ASSERT: El estado debe ser true
        assertTrue(tareaService.listarTareas().get(0).isEstadoCompletado());
    }

    @Test
    @DisplayName("Debería lanzar excepción al intentar completar una tarea ya completada")
    void testCompletarTarea_TareaYaCompletada_LanzaTareaYaCompletadaException() {
        // ARRANGE: Agregar y completar la tarea una primera vez
        tareaService.agregarTarea("Prueba", "Descripción");
        int id = tareaService.listarTareas().get(0).getId();
        tareaService.completarTarea(id);

        // ACT + ASSERT: El segundo intento debe lanzar la excepción
        assertThrows(TareaYaCompletadaException.class,
                () -> tareaService.completarTarea(id));
    }

    // =========================================================
    // marcarTareaComoIncompleta()
    // =========================================================

    @Test
    @DisplayName("Debería marcar una tarea completada como incompleta exitosamente")
    void testMarcarTareaComoIncompleta_TareaCompletada_EstadoCompletadoFalse() {
        // ARRANGE: Agregar tarea y completarla primero
        tareaService.agregarTarea("Ir al gimnasio", "Entrenar pierna");
        int id = tareaService.listarTareas().get(0).getId();
        tareaService.completarTarea(id);

        // ACT: Marcar como incompleta
        tareaService.marcarTareaComoIncompleta(id);

        // ASSERT: El estado debe haber vuelto a false
        assertFalse(tareaService.listarTareas().get(0).isEstadoCompletado());
    }

    @Test
    @DisplayName("Debería lanzar excepción al marcar como incompleta una tarea que ya lo está")
    void testMarcarTareaComoIncompleta_TareaYaIncompleta_LanzaTareaYaIncompletaException() {
        // ARRANGE: Agregar tarea (nace incompleta por defecto)
        tareaService.agregarTarea("Lavar platos", "Usar detergente");
        int id = tareaService.listarTareas().get(0).getId();

        // ACT + ASSERT: Marcar como incompleta algo que ya lo es debe lanzar excepción
        assertThrows(TareaYaIncompletaException.class,
                () -> tareaService.marcarTareaComoIncompleta(id));
    }

    // =========================================================
    // eliminarTarea()
    // =========================================================

    @Test
    @DisplayName("Debería eliminar una tarea existente correctamente")
    void testEliminarTarea_TareaExistente_ListaVacia() {
        // ARRANGE: Agregar una tarea y obtener su ID
        tareaService.agregarTarea("Borrar", "Esta tarea será eliminada");
        int id = tareaService.listarTareas().get(0).getId();

        // ACT: Eliminar la tarea
        tareaService.eliminarTarea(id);

        // ASSERT: La lista debe quedar vacía
        assertTrue(tareaService.listarTareas().isEmpty());
    }

    @Test
    @DisplayName("Debería lanzar excepción al eliminar una tarea con ID inexistente")
    void testEliminarTarea_IdNoExistente_LanzaTareaNoEncontradaException() {
        // ARRANGE: ID que no existe en la lista
        int idInexistente = 500;

        // ACT + ASSERT: Debe lanzar TareaNoEncontradaException
        assertThrows(TareaNoEncontradaException.class,
                () -> tareaService.eliminarTarea(idInexistente));
    }

    // =========================================================
    // exportarTareas()
    // =========================================================

    @Test
    @DisplayName("Debería exportar las tareas y crear el archivo correctamente")
    void testExportarTareas_NombreValido_ArchivoCreado() {
        // ARRANGE: Agregar contenido y definir nombre del archivo
        tareaService.agregarTarea("Test Export", "Contenido para el archivo");
        String nombreArchivo = "test_tareas.txt";

        // ACT: Exportar las tareas
        assertDoesNotThrow(() -> tareaService.exportarTareas(nombreArchivo));

        // ASSERT: El archivo debe existir en disco
        File archivo = new File(nombreArchivo);
        assertTrue(archivo.exists());

        // Limpieza del archivo de prueba
        archivo.delete();
    }

    @Test
    @DisplayName("Debería lanzar excepción al exportar con nombre de archivo vacío")
    void testExportarTareas_NombreVacio_LanzaExportarTareaException() {
        // ARRANGE: Nombre de archivo inválido (vacío)
        String nombreArchivo = "";

        // ACT + ASSERT: Debe lanzar ExportarTareaException
        assertThrows(ExportarTareaException.class,
                () -> tareaService.exportarTareas(nombreArchivo));
    }
}