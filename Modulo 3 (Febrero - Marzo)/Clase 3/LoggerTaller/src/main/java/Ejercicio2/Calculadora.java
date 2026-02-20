package Ejercicio2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculadora {
    private static final Logger logger = LoggerFactory.getLogger(Calculadora.class);

    public double sumar(double a, double b) {
        logger.debug("Sumando: {} + {}", a, b);
        double resultado = a + b;
        logger.info("Suma realizada: {} + {} = {}", a, b, resultado);
        return resultado;
    }

    public double restar(double a, double b) {
        logger.debug("Restando: {} - {}", a, b);
        double resultado = a - b;
        logger.info("Resta realizada: {} - {} = {}", a, b, resultado);
        return resultado;
    }

    public double multiplicar(double a, double b) {
        logger.debug("Multiplicando: {} * {}", a, b);
        double resultado = a * b;
        logger.info("Multiplicación realizada: {} * {} = {}", a, b, resultado);
        return resultado;
    }

    public double dividir(double a, double b) {
        logger.debug("Dividiendo: {} / {}", a, b);

        if (b == 0) {
            logger.error("Intento de división por cero: {} / {}", a, b);
            throw new ArithmeticException("No se puede dividir por cero");
        }

        double resultado = a / b;
        logger.info("División realizada: {} / {} = {}", a, b, resultado);
        return resultado;
    }
}