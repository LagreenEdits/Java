package Ejercicio3;

import java.util.Map;
import java.util.HashMap;

public class Inventario {

    private Map<String, Producto> productos;

    public Inventario() {
        this.productos = new HashMap<>();
    }

    public void agregarProducto(Producto producto) {
        productos.put(producto.getCodigo(), producto);
    }

    public Producto buscarProducto(String codigo) throws ProductoNoEncontradoException {
        Producto producto = productos.get(codigo);
        if (producto == null) {
            throw new ProductoNoEncontradoException(
                    "Producto con código " + codigo + " no encontrado"
            );
        }
        return producto;
    }

    public void venderProducto(String codigo, int cantidad) throws ProductoNoEncontradoException, StockInsuficienteException {
        Producto producto = buscarProducto(codigo);

        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException(
                    String.format("Stock insuficiente. Disponible: %d, Solicitado: %d",
                            producto.getStock(),
                            cantidad)
            );
        }

        producto.setStock(producto.getStock() - cantidad);
        System.out.println("Venta realizada: " + cantidad + " unidades de " + producto.getNombre());
    }
}
