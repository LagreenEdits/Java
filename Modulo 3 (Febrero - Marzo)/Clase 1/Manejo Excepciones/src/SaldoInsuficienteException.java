//Excepción personalizada no verificada
public class SaldoInsuficienteException extends RuntimeException {

    //Constructor
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

