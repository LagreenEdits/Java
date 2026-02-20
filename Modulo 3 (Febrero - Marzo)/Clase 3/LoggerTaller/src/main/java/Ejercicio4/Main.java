package Ejercicio4;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ServicioPedidos servicio = new ServicioPedidos();

        // Simulamos dos pedidos diferentes
        servicio.procesarPedido("ORD-99", "USER-ALFA");
        System.out.println("---------------------------------------");
        servicio.procesarPedido("ORD-100", "USER-BETA");
    }
}