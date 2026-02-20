package Ejercicio3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Iniciando aplicación con sistema de rotación...");

        for (int i = 1; i <= 5; i++) {
            logger.debug("Procesando lote de datos número: {}", i);

            if (i % 2 == 0) {
                logger.info("Lote {} procesado con éxito.", i);
            } else {
                // Simulamos un error para ver cómo se guarda en el archivo de errores
                logger.error("Error crítico procesando el lote {}: Datos corruptos simulados.", i);
            }
        }

        logger.info("Simulación finalizada. Revisa la carpeta /logs en la raíz de tu proyecto.");
    }
}