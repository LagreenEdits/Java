package org.example;

import org.example.exception.*;
import org.example.service.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        TareaService tareaService = new TareaService();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("<=== TaskLand - Gestion basica de tareas by Juan Laverde ===>");

        do {
            System.out.println("""
                    \n--- MENÚ DE OPCIONES ---
                    1. Agregar Tarea
                    2. Listar Tareas
                    3. Marcar Tarea como Completada
                    4. Marcar Tarea como Incompletada
                    5. Eliminar Tarea
                    6. Exportar archivo
                    7. Salir
                    """);

            try {
                String inputOpcion = leerEntrada(scanner, "Seleccione una opción: ");
                opcion = convertirEntero(inputOpcion);

                switch (opcion) {
                    case 1:
                        String nombre = leerEntrada(scanner, "Ingrese nombre: ");
                        String descripcion = leerEntrada(scanner, "Ingrese descripción: ");
                        tareaService.agregarTarea(nombre, descripcion);
                        break;

                    case 2:
                        System.out.println("\n<--- LISTA DE TAREAS --->");
                        tareaService.listarTareas().forEach(System.out::println);
                        break;

                    case 3:
                        String idCompStr = leerEntrada(scanner, "Ingrese el ID de la tarea a completar: ");
                        tareaService.completarTarea(convertirEntero(idCompStr));
                        break;

                    case 4:
                        String idIncompStr = leerEntrada(scanner, "Ingrese el ID de la tarea a marcar como incompleta: ");
                        tareaService.marcarTareaComoIncompleta(convertirEntero(idIncompStr));
                        break;

                    case 5:
                        String idElimStr = leerEntrada(scanner, "Ingrese el ID de la tarea a eliminar: ");
                        tareaService.eliminarTarea(convertirEntero(idElimStr));
                        break;

                    case 6:
                        String nombreArchivo = leerEntrada(scanner, "Ingrese el nombre del archivo (ej: tareas.txt): ");
                        tareaService.exportarTareas(nombreArchivo);
                        break;

                    case 7:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        log.info("Opción no válida.");
                }

            } catch (OpcionInvalidoException e) {
                log.error("Error de entrada: El valor ingresado no es un número válido: {}", e.getMessage());
            } catch (DatosInvalidosException e) {
                log.error("Error de validación: {}", e.getMessage());
            } catch (TareaNoEncontradaException e) {
                log.error("Error de búsqueda: {}", e.getMessage());
            } catch (TareaYaIncompletaException e) {
                log.error("Error: {}", e.getMessage());
            } catch (ExportarTareaException e) {
                System.err.println("Error de exportación.");
                log.error("Fallo al exportar: {}", e.getMessage());
            } catch (Exception e) {
                System.err.println("Error inesperado.");
                log.debug("Fallo crítico: {}", e.getMessage());
            } finally {
                log.debug("Fin del procesamiento de la opción.");
            }

        } while (opcion != 7);
        scanner.close();
    }

    public static String leerEntrada(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static int convertirEntero(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("Error: Por favor, ingrese un número válido.");
            throw new OpcionInvalidoException(input);
        }
    }
}