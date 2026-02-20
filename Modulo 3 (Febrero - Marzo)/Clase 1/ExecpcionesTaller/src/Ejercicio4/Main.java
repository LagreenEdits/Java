package Ejercicio4;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ValidadorFormulario validador = new ValidadorFormulario();

        System.out.println("Prueba 1: Datos incorrectos");
        // Nombre corto, teléfono corto, CP largo
        validador.validar("Jo", "12345", "080801");

        System.out.println("\nPrueba 2: Datos correctos");
        validador.validar("Juan Perez", "1234567890", "28001");
    }
}
