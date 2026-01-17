public class Main {

    public static void main(String[] args) {
        try {
            CuentaBancaria2.retirar(4);
            CuentaBancaria2.retirar(4);
        } catch (SaldoInsuficienteException2 e) {
            throw new RuntimeException(e);
        }
    }
}