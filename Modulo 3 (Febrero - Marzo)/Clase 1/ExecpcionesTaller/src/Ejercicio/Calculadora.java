package Ejercicio;

public class Calculadora {
    public static double sumar(double a, double b) throws NumeroNegativoException {
        validarNumero(a);
        validarNumero(b);
        return a + b;
    }

    public static double restar(double a, double b) throws NumeroNegativoException {
        validarNumero(a);
        validarNumero(b);
        return a - b;
    }

    public static double multiplicar(double a, double b) throws NumeroNegativoException {
        validarNumero(a);
        validarNumero(b);
        return a * b;
    }

    public static double dividir(double a, double b)
            throws DivisionPorCeroException, NumeroNegativoException {
        validarNumero(a);
        validarNumero(b);
        if (b == 0) {
            throw new DivisionPorCeroException("No se puede dividir por cero");
        }
        return a / b;
    }

    private static void validarNumero(double numero) throws NumeroNegativoException {
        if (numero < 0) {
            throw new NumeroNegativoException("El número no puede ser negativo: " + numero);
        }
    }
}
