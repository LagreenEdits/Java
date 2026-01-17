public class Main {

    public static void main(String[] args) {
        try {
            CuentaBancaria3.retirar(4);
            CuentaBancaria3.retirar(4);
        } catch (ValidacionException e) {
            e.printStackTrace();
        }
    }
}