# Sistema de Registro de Estudiantes

Este es un miniproyecto desarrollado en **Java** como parte del **Módulo 1** del programa "Java Senior AI". Consiste en una aplicación de consola para la gestión académica básica de un estudiante, permitiendo el registro de notas, cálculo de promedios y determinación del estado académico.

## Funcionalidades

El sistema ofrece un menú interactivo con las siguientes opciones:

1. **Registrar Estudiante:** Permite ingresar el nombre y tres notas. Incluye validación para asegurar que las notas estén en el rango de `0` a `100`.
2. **Mostrar Datos:** Despliega la información básica del estudiante actualmente cargado en memoria.
3. **Calcular Promedio:** Realiza el cálculo matemático de las tres notas ingresadas.
4. **Mostrar Resumen:** Presenta un informe completo que incluye nombre, notas, promedio y estado final (**Aprobado** si el promedio es $\ge 60$, de lo contrario **Reprobado**).
5. **Limpiar Datos:** Reinicia las variables del sistema para permitir un nuevo registro.

## Tecnologías Utilizadas

* **Lenguaje:** Java 17 o superior.
* **Entrada de Datos:** `java.util.Scanner` para la interacción por consola.
* **Lógica:** Estructuras de control (`do-while`, `switch`), métodos estáticos y operadores ternarios.

## Estructura del Código

El archivo principal es `App.java` y está organizado mediante programación modular:

* **`main`**: Orquestador principal que gestiona el ciclo de vida de la aplicación y el flujo de navegación del usuario.
* **`mostrarMenu`**: Interfaz visual que despliega las opciones disponibles mediante el uso de bloques de texto organizados.
* **`registrarEstudiante`**: Controlador lógico que gestiona la creación de registros nuevos y previene la sobreescritura accidental de datos.
* **`leerDatosEstudiante`**: Módulo de captura encargado de recolectar secuencialmente el nombre y las calificaciones del estudiante.
* **`esValidaNota`**: Función de validación booleana que verifica si una calificación se encuentra dentro del rango permitido de 0 a 100.
* **`solicitarNota`**: Mecanismo de entrada persistente que garantiza la integridad de los datos numéricos mediante bucles de validación.
* **`mostrarEstudiante`**: Componente de salida que presenta en consola la información básica almacenada actualmente en el sistema.
* **`calcularPromedio`**: Motor aritmético que procesa las calificaciones registradas para obtener el valor medio del desempeño académico.
* **`mostrarResumen`**: Generador de reportes que consolida toda la información, incluyendo datos, cálculos y el veredicto final.
* **`setEstado`**: Regla de negocio que asigna el estatus de "Aprobado" o "Reprobado" según el umbral de rendimiento establecido.
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
--- Sistema de Registro de Estudiantes ---

1. Registrar datos de un estudiante.
2. Mostrar datos del estudiante actual.
...
Seleccione una opción: 1
Ingrese el nombre del estudiante: Juan Pérez
Ingrese una nota [0-100]: 85
...
Estado: Aprobado

```

---

> **Proyecto #1 desarrollado en el marco de la Ruta de aprendizaje**