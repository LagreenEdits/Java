package Ejercicio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    // Definimos el logger con el nombre de la clase
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("El sistema de logging se ha iniciado correctamente.");
        logger.debug("Esto es un mensaje de depuración (DEBUG).");
        logger.warn("Esto es una advertencia (WARN).");
        logger.error("Esto es un error simulado (ERROR).");

        System.out.println("\n--- Fin de la prueba de logs ---");
    }
}