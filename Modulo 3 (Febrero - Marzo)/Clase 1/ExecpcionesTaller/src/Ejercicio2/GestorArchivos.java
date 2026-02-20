package Ejercicio2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class GestorArchivos {
    // Método para escribir en un archivo
    public void escribirArchivo(String ruta, String contenido) throws IOException {
        // Try-with-resources: BufferedWriter se cerrará solo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(contenido);
            System.out.println("Archivo escrito con éxito.");
        }
    }

    // Método para leer un archivo
    public void leerArchivo(String ruta) throws ArchivoNoLegibleException, IOException {
        File archivo = new File(ruta);

        // Verificación de negocio antes de intentar leer
        if (archivo.exists() && !archivo.canRead()) {
            throw new ArchivoNoLegibleException("El sistema no tiene permisos para leer: " + ruta);
        }

        // Try-with-resources: BufferedReader se cerrará solo
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            // Re-lanzamos o manejamos específicamente
            throw new FileNotFoundException("El archivo no existe en la ruta: " + ruta);
        }
    }
}
