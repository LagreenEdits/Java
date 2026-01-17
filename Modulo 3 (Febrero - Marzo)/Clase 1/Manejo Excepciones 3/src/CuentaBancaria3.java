import java.time.LocalDateTime;

// Uso
public class CuentaBancaria3 {
    private static double saldo = 5.0;

    public static void retirar(double cantidad) throws ValidacionException {
        if (cantidad > saldo) {
            throw new ValidacionException( "Saldo", LocalDateTime.now(),
                    "Saldo insuficiente."
            );
        }
        saldo -= cantidad;
    }
}
