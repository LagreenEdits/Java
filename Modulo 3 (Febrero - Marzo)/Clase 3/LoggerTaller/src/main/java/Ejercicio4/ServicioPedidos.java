package Ejercicio4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.time.LocalDateTime;

public class ServicioPedidos {
    private static final Logger logger = LoggerFactory.getLogger(ServicioPedidos.class);

    public void procesarPedido(String orderId, String customerId) {
        try {
            // REQUISITO: Almacenar valores en MDC
            MDC.put("orderId", orderId);
            MDC.put("customerId", customerId);
            MDC.put("timestamp", LocalDateTime.now().toString());

            // Todos estos logs incluirán automáticamente los datos del MDC si configuramos el XML
            logger.info("Iniciando validación del pedido.");

            Thread.sleep(500); // Simulando trabajo
            logger.info("Verificando stock en almacén...");

            Thread.sleep(500);
            logger.info("Pago autorizado exitosamente.");

        } catch (InterruptedException e) {
            logger.error("Error en el procesamiento", e);
        } finally {
            // REQUISITO: Limpiar MDC al finalizar (Obligatorio para un Senior)
            MDC.clear();
            logger.info("Contexto MDC limpio. Fin del proceso.");
        }
    }
}