package org.example.service;

import org.example.exception.ExportarTareaException;
import org.example.exception.TareaYaIncompletaException;
import org.example.model.Tarea;
import org.example.exception.TareaNoEncontradaException;
import org.example.exception.TareaYaCompletadaException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TareaService {

    private static final Logger log = LoggerFactory.getLogger(TareaService.class);

    private final List<Tarea> listaTareas = new ArrayList<>();

    public void agregarTarea(String nombre, String descripcion) {
        log.debug("[agregarTarea] Inicio - nombre='{}', descripcion='{}'", nombre, descripcion);

        ValidarTareaService.validarNombre(nombre);
        ValidarTareaService.validarDescripcion(descripcion);
        log.debug("[agregarTarea] Validaciones pasadas correctamente.");

        Tarea nuevaTarea = new Tarea(nombre, descripcion);
        listaTareas.add(nuevaTarea);

        log.info("[agregarTarea] Tarea agregada exitosamente - ID={}",
                nuevaTarea.getId());
    }

    public List<Tarea> listarTareas() {
        log.debug("[listarTareas] Inicio - consultando lista completa.");
        log.debug("[listarTareas] Total de tareas encontradas: {}", listaTareas.size());
        return new ArrayList<>(listaTareas);
    }

    public void completarTarea(int id) {
        log.debug("[completarTarea] Inicio - buscando tarea con ID={}", id);

        Tarea tarea = buscarTareaId(id);
        log.debug("[completarTarea] Tarea encontrada - ID={}, nombre='{}', estadoActual={}",
                tarea.getId(), tarea.getNombre(), tarea.isEstadoCompletado());

        if (tarea.isEstadoCompletado()) {
            log.warn("[completarTarea] Operación rechazada - la tarea ID={} ya estaba completada.", id);
            throw new TareaYaCompletadaException("Intento de completar una tarea ya completada. ID: " + id);
        }

        tarea.setEstadoCompletado(true);
        log.info("[completarTarea] Tarea ID={} marcada como completada exitosamente.", id);
    }

    public void marcarTareaComoIncompleta(int id) {
        log.debug("[marcarTareaComoIncompleta] Inicio - buscando tarea con ID={}", id);

        Tarea tarea = buscarTareaId(id);
        log.debug("[marcarTareaComoIncompleta] Tarea encontrada - ID={}, nombre='{}', estadoActual={}",
                tarea.getId(), tarea.getNombre(), tarea.isEstadoCompletado());

        if (!tarea.isEstadoCompletado()) {
            log.warn("[marcarTareaComoIncompleta] Operación rechazada - la tarea ID={} ya estaba incompleta.", id);
            throw new TareaYaIncompletaException("La tarea con ID " + id + " ya se encuentra incompleta.");
        }

        tarea.setEstadoCompletado(false);
        log.info("[marcarTareaComoIncompleta] Tarea ID={} marcada como incompleta exitosamente.", id);
    }

    public void eliminarTarea(int id) {
        log.debug("[eliminarTarea] Inicio - buscando tarea con ID={}", id);

        Tarea tarea = buscarTareaId(id);
        log.debug("[eliminarTarea] Tarea encontrada - ID={}, nombre='{}'. Procediendo a eliminar.",
                tarea.getId(), tarea.getNombre());

        listaTareas.remove(tarea);
        log.warn("[eliminarTarea] Tarea ID={} eliminada del sistema. Tareas restantes: {}",
                id, listaTareas.size());
    }

    public void exportarTareas(String nombreArchivo) throws ExportarTareaException {
        log.debug("[exportarTareas] Inicio - nombreArchivo='{}', totalTareas={}",
                nombreArchivo, listaTareas.size());

        if (nombreArchivo == null || nombreArchivo.isEmpty()) {
            log.error("[exportarTareas] Nombre de archivo inválido: '{}'", nombreArchivo);
            throw new ExportarTareaException("El nombre del archivo no puede ser nulo o vacío.");
        }

        log.info("[exportarTareas] Iniciando exportación a archivo: '{}'", nombreArchivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("=== REPORTE DE TAREAS ===\n");
            for (Tarea tarea : listaTareas) {
                writer.write(tarea.toString());
                writer.newLine();
                log.debug("[exportarTareas] Tarea escrita - ID={}, nombre='{}'",
                        tarea.getId(), tarea.getNombre());
            }
            log.info("[exportarTareas] Exportación completada exitosamente. {} tarea(s) exportada(s) a '{}'.",
                    listaTareas.size(), nombreArchivo);

        } catch (IOException e) {
            log.error("[exportarTareas] Falla de I/O al exportar '{}': {}", nombreArchivo, e.getMessage());
            throw new ExportarTareaException("No se pudo exportar la lista de tareas a " + nombreArchivo);
        }
    }

    public Tarea buscarTareaId(int id) {
        log.debug("[buscarTareaId] Buscando tarea con ID={} entre {} tarea(s).", id, listaTareas.size());

        return listaTareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    log.error("[buscarTareaId] Tarea no encontrada con ID={}", id);
                    return new TareaNoEncontradaException("Error: Tarea no encontrada con ID: " + id);
                });
    }
}