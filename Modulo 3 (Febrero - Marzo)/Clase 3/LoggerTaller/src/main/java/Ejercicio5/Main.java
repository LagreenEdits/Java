package Ejercicio5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        logger.info("--- Iniciando Generador de Tráfico para Análisis ---");

        for (int i = 0; i < 20; i++) {
            int chance = random.nextInt(100);

            if (chance < 60) {
                logger.info("Transacción exitosa: Pedido #{}", (1000 + i));
            } else if (chance < 85) {
                logger.warn("Stock bajo detectado para producto ID: {}", (random.nextInt(500)));
            } else {
                logger.error("FALLO CRÍTICO: Error en pasarela de pago para usuario {}", (50 + i));
            }

            Thread.sleep(200); // Simulando tiempo real
        }

        logger.info("--- Generación finalizada. Proceda al análisis de logs ---");
    }
}