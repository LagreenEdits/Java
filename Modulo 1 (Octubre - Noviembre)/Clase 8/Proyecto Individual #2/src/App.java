import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);
    static String nombre = "N/A";
    static double precio = 0.0;
    static int cantidad = 0;

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    mostrarProducto();
                    break;
                case 3:
                    double valorTotal = calcularValorTotal();
                    if (valorTotal != -1) {
                        System.out.printf("Valor total del inventario: $%,.2f%n", valorTotal);
                    }
                    break;
                case 4:
                    mostrarResumen();
                    break;
                case 5:
                    limpiarDatos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    static void mostrarMenu() {
        var opciones = """
                --- Sistema de Gestión de Productos ---

                1. Registrar nuevo producto
                2. Mostrar información del producto actual
                3. Calcular valor total del inventario
                4. Mostrar resumen completo del producto
                5. Limpiar datos del producto actual
                0. Salir
                """;
        System.out.println(opciones);
        System.out.print("Ingrese su opción: ");
    }

    static void registrarProducto() {
        if (nombre.equals("N/A")) {
            leerDatosProducto();
        } else {
            System.out.print("Ya hay un producto registrado. ¿Desea sobreescribir? (s/n): ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                leerDatosProducto();
            } else {
                System.out.println("Operación cancelada. No se modificaron los datos.");
            }
        }
    }

    static void leerDatosProducto() {
        do {
            System.out.print("Ingrese el nombre del producto: ");
            nombre = sc.nextLine();
            if (!ValidarNombre(nombre)) {
                System.out.println("Nombre inválido. No puede estar vacío ni contener solo espacios.");
            }
        } while (!ValidarNombre(nombre));

        System.out.print("Ingrese el precio unitario: ");
        precio = solicitarPrecio();

        System.out.print("Ingrese la cantidad en inventario: ");
        cantidad = solicitarCantidad();

        sc.nextLine(); // Limpiar el buffer
        System.out.println("Producto registrado exitosamente.");
    }

    static boolean ValidarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    static boolean ValidarPrecio(double precio) {
        return precio > 0;
    }

    static boolean ValidarCantidad(int cantidad) {
        return cantidad >= 0;
    }

    static double solicitarPrecio() {
        double valor;
        do {
            System.out.print("Ingrese el precio (mayor que 0): ");
            valor = sc.nextDouble();
            if (!ValidarPrecio(valor)) {
                System.out.println("Precio inválido. Debe ser mayor que 0.");
            }
        } while (!ValidarPrecio(valor));
        return valor;
    }

    static int solicitarCantidad() {
        int valor;
        do {
            System.out.print("Ingrese la cantidad (número entero no negativo): ");
            valor = sc.nextInt();
            if (!ValidarCantidad(valor)) {
                System.out.println("Cantidad inválida. Debe ser un número entero no negativo.");
            }
        } while (!ValidarCantidad(valor));
        return valor;
    }

    static void mostrarProducto() {
        if (nombre.equals("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.");
        } else {
            System.out.println("Nombre del producto: " + nombre);
            System.out.printf("Precio unitario: $%,.2f%n", precio);
            System.out.println("Cantidad en inventario: " + cantidad);
        }
    }

    static double calcularValorTotal() {
        if (nombre.equals("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.");
            return -1;
        }
        return precio * cantidad;
    }

    static void mostrarResumen() {
        if (nombre.equals("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.");
            return;
        }
        System.out.println("--- Resumen del Producto ---");
        System.out.println("Nombre: " + nombre);
        System.out.printf("Precio Unitario: $%,.2f%n", precio);
        System.out.println("Cantidad en Inventario: " + cantidad);
        System.out.printf("Valor Total en Inventario: $%,.2f%n", calcularValorTotal());
        System.out.println("Estado del Stock: " + EstadoStock());
    }

    static String EstadoStock() {
        if (cantidad < 5) {
            return "Stock bajo";
        } else if (cantidad <= 20) {
            return "Stock suficiente";
        } else {
            return "Stock alto";
        }
    }

    static void limpiarDatos() {
        nombre = "N/A";
        precio = 0.0;
        cantidad = 0;
        System.out.println("Los datos del producto actual han sido borrados exitosamente.");
    }
}