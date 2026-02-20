package Ejercicio5;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    // Usamos un Map para simular nuestra base de datos de cuentas
    private Map<String, Double> cuentas = new HashMap<>();

    public void crearCuenta(String numero, double saldoInicial) {
        cuentas.put(numero, saldoInicial);
    }

    public void depositar(String numero, double monto) throws CuentaNoEncontradaException, MontoInvalidoException {
        if (monto <= 0)
            throw new MontoInvalidoException("El monto debe ser positivo.");
        if (!cuentas.containsKey(numero))
            throw new CuentaNoEncontradaException("Cuenta " + numero + " no existe.");
        cuentas.put(numero, cuentas.get(numero) + monto);
    }

    public void retirar(String numero, double monto) throws CuentaNoEncontradaException, SaldoInsuficienteException, MontoInvalidoException {
        if (monto <= 0) throw new MontoInvalidoException("Monto inválido para retiro.");
        if (!cuentas.containsKey(numero)) throw new CuentaNoEncontradaException("Cuenta no encontrada.");

        double saldoActual = cuentas.get(numero);
        if (monto > saldoActual) throw new SaldoInsuficienteException("Fondos insuficientes en la cuenta " + numero);

        cuentas.put(numero, saldoActual - monto);
    }

    // El método estrella: Transferencia entre cuentas
    public void transferir(String origen, String destino, double monto)
            throws CuentaNoEncontradaException, SaldoInsuficienteException, MontoInvalidoException {
        System.out.println("Iniciando transferencia de $" + monto + " desde " + origen + " a " + destino);

        // Un Senior realiza los pasos de forma atómica
        this.retirar(origen, monto); // Si falla aquí, se lanza la excepción y no llega al depósito
        this.depositar(destino, monto);

        System.out.println("Transferencia completada con éxito.");
    }
}
