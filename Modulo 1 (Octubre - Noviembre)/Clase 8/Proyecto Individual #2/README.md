# Sistema de Gestión de Productos

Este es un miniproyecto desarrollado en **Java** como parte del **Módulo 1** del programa "Java Senior AI". Consiste en una aplicación de consola para la gestión básica de un producto en inventario, permitiendo el registro de datos, cálculo del valor total y determinación del estado del stock.

## Funcionalidades

El sistema ofrece un menú interactivo con las siguientes opciones:

1. **Registrar Producto:** Permite ingresar el nombre, precio unitario y cantidad en inventario. Incluye validación para asegurar que el precio sea mayor que `0`, la cantidad sea un entero no negativo, y el nombre no esté vacío.
2. **Mostrar Información:** Despliega los datos básicos del producto actualmente cargado en memoria.
3. **Calcular Valor Total:** Calcula el valor total del inventario mediante la fórmula `precioUnitario × cantidad`.
4. **Mostrar Resumen:** Presenta un informe completo que incluye nombre, precio, cantidad, valor total y estado del stock (**Stock bajo** si `cantidad < 5`, **Stock suficiente** si está entre `5` y `20`, **Stock alto** si supera `20`).
5. **Limpiar Datos:** Reinicia las variables del sistema para permitir un nuevo registro.

## Tecnologías Utilizadas

* **Lenguaje:** Java 17 o superior.
* **Entrada de Datos:** `java.util.Scanner` para la interacción por consola.
* **Lógica:** Estructuras de control (`do-while`, `switch`), métodos estáticos y estructuras condicionales anidadas.

## Estructura del Código

El archivo principal es `App.java` y está organizado mediante programación modular:

* **`main`**: Orquestador principal que gestiona el ciclo de vida de la aplicación y el flujo de navegación del usuario. Cierra el `Scanner` antes de finalizar.
* **`mostrarMenu`**: Interfaz visual que despliega las opciones disponibles mediante bloques de texto organizados con *text blocks* de Java.
* **`registrarProducto`**: Controlador lógico que gestiona la creación de registros nuevos y previene la sobreescritura accidental de datos existentes.
* **`leerDatosProducto`**: Módulo de captura encargado de recolectar secuencialmente el nombre, el precio y la cantidad del producto.
* **`esValidoNombre`**: Función de validación booleana que verifica que el nombre del producto no sea nulo, vacío ni contenga solo espacios en blanco.
* **`esValidoPrecio`**: Función de validación booleana que verifica que el precio ingresado sea estrictamente mayor que cero.
* **`esValidaCantidad`**: Función de validación booleana que verifica que la cantidad ingresada sea un número entero no negativo.
* **`solicitarPrecio`**: Mecanismo de entrada persistente que garantiza la integridad del dato numérico de precio mediante bucles de validación.
* **`solicitarCantidad`**: Mecanismo de entrada persistente que garantiza la integridad del dato numérico de cantidad mediante bucles de validación.
* **`mostrarProducto`**: Componente de salida que presenta en consola la información básica almacenada actualmente en el sistema.
* **`calcularValorTotal`**: Motor aritmético que multiplica el precio unitario por la cantidad para obtener el valor total del inventario.
* **`mostrarResumen`**: Generador de reportes que consolida toda la información, incluyendo datos, cálculos y el estado del stock.
* **`getEstadoStock`**: Regla de negocio que determina y retorna la etiqueta de estado del inventario según los umbrales de cantidad establecidos.
* **`limpiarDatos`**: Procedimiento de mantenimiento que restablece todas las variables a su estado inicial para un nuevo registro.

## Cómo ejecutarlo

1. Asegúrate de tener instalado el **JDK (Java Development Kit)**.
2. Clona este repositorio o descarga el archivo `App.java`.
3. Abre una terminal y navega hasta la carpeta del proyecto.
4. Compila el código:
```bash
  javac App.java
```

5. Ejecuta la aplicación:
```bash
  java App
```

## Ejemplo de Uso

```text
--- Sistema de Gestión de Productos ---

1. Registrar nuevo producto
2. Mostrar información del producto actual
3. Calcular valor total del inventario
4. Mostrar resumen completo del producto
5. Limpiar datos del producto actual
0. Salir

Ingrese su opción: 1
Ingrese el nombre del producto: Laptop Lenovo
Ingrese el precio (mayor que 0): 2500000
Ingrese la cantidad (número entero no negativo): 5
Producto registrado exitosamente.

Ingrese su opción: 4
--- Resumen del Producto ---
Nombre: Laptop Lenovo
Precio Unitario: $2,500,000.00
Cantidad en Inventario: 5
Valor Total en Inventario: $12,500,000.00
Estado del Stock: Stock suficiente
```

---

> **Proyecto #2 desarrollado en el marco de la Ruta de aprendizaje**