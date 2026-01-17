// Uso
public class CuentaBancaria {
    private static double saldo = 5.0;

    public static void retirar(double cantidad) {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente. Se trato de retirar: "+ cantidad +" Saldo actual: " + saldo
            );
        }
        saldo -= cantidad;
    }
}
