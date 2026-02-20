package Ejercicio2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Calculadora calc = new Calculadora();

        calc.sumar(10, 5);
        calc.restar(10, 3);
        calc.multiplicar(4, 7);

        try {
            calc.dividir(10, 2);
            calc.dividir(10, 0);
        } catch (ArithmeticException e) {
            logger.warn("Operación cancelada: {}", e.getMessage());
        }
    }
}