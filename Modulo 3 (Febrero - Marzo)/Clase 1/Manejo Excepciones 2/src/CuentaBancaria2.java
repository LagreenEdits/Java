// Uso
public class CuentaBancaria2 {
    private static double saldo = 5.0;

    public static void retirar(double cantidad) throws SaldoInsuficienteException2 {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException2(
                    "Saldo insuficiente. Se trato de retirar: "+ cantidad +" Saldo actual: " + saldo
            );
        }
        saldo -= cantidad;
    }
}
