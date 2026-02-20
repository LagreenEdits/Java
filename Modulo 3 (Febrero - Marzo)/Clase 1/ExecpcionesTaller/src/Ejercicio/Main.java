package Ejercicio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Suma: " + Calculadora.sumar(1, 2));
            System.out.println("Suma: " + Calculadora.sumar(10, 5));
            System.out.println("Resta: " + Calculadora.restar(7, 5));
            System.out.println("Resta: " + Calculadora.restar(5, 7));
            System.out.println("Multiplicacion: " + Calculadora.multiplicar(4, 6));
            System.out.println("Multiplicacion: " + Calculadora.multiplicar(3, 7));
            System.out.println("División: " + Calculadora.dividir(10, 3));
            System.out.println("División: " + Calculadora.dividir(10, 0));

        } catch (DivisionPorCeroException | NumeroNegativoException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Fin");
        }
    }
}
