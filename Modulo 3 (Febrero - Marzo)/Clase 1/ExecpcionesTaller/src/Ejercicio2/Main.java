package Ejercicio2;

import java.io.FileNotFoundException;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        GestorArchivos gestor = new GestorArchivos();
        String nombreArchivo = "archivoCreado.txt";

        try {
            // 1. Intentamos escribir
            gestor.escribirArchivo(nombreArchivo, "Hola, este es un contenido para el curso Java Senior AI.");

            // 2. Intentamos leer
            gestor.leerArchivo(nombreArchivo);

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el archivo -> " + e.getMessage());
        } catch (ArchivoNoLegibleException e) {
            System.err.println("Error de Permisos: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de Entrada/Salida: " + e.getMessage());
        } finally {
            System.out.println("Proceso de gestión de archivos finalizado.");
        }
    }
}
