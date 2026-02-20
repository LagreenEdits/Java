package Ejercicio5;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Banco miBanco = new Banco();
        miBanco.crearCuenta("123", 1000.0);
        miBanco.crearCuenta("456", 500.0);

        String transaccionId = "TX-999";
        try {
            // Caso 1: Transferencia exitosa
            miBanco.transferir("123", "456", 200.0);

            // Caso 2: Intento de transferir más de lo que hay
            miBanco.transferir("123", "456", 5000.0);

        } catch (CuentaNoEncontradaException | SaldoInsuficienteException | MontoInvalidoException e) {
            System.err.println("TRANSACCIÓN FALLIDA: " + e.getMessage());
        } finally {
            // Requisito: Registrar siempre la transacción
            System.out.println("[AUDITORÍA] Registro de la operación " + transaccionId + " procesado en el log del sistema.");
        }
    }
}
