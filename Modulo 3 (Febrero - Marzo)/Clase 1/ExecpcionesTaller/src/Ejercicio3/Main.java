package Ejercicio3;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        try {
            // Agregar productos
            Producto p1 = new Producto("P001", "Laptop", 15000.0, 10);
            Producto p2 = new Producto("P002", "Mouse", 500.0, 50);
            inventario.agregarProducto(p1);
            inventario.agregarProducto(p2);

            // Vender productos
            inventario.venderProducto("P001", 8);
            System.out.println("Stock restante "+p1.getStock());
            inventario.venderProducto("P002", 10); // Stock insuficiente
            System.out.println("Stock restante "+p2.getStock());

        } catch (PrecioInvalidoException e) {
            System.out.println("Error de precio: " + e.getMessage());
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error de producto: " + e.getMessage());
        } catch (StockInsuficienteException e) {
            System.out.println("Error de stock: " + e.getMessage());
        }
    }
}
