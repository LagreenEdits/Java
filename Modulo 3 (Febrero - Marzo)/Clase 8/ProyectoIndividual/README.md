# 📋 TaskLand – Gestor de tareas basicas

> Proyecto Integrador de Programación Java  
> **Autor:** Juan David Laverde Ochoa
> **Tecnologías:** Java · SLF4J · Log4j2 · JUnit 5 · Maven

---

## 1. Descripción General

**TaskLand** es una aplicación de consola desarrollada en Java que permite administrar una lista de tareas pendientes desde la línea de comandos, aplicando buenas prácticas de programación profesional:

- ✅ Manejo adecuado de errores y excepciones
- ✅ Registro de eventos mediante logging profesional (SLF4J + Log4j2)
- ✅ Pruebas unitarias con JUnit 5

### Funcionalidades

- Agregar una tarea con ID automático, nombre, descripción y estado (pendiente/completada)
- Listar todas las tareas registradas
- Marcar una tarea como completada
- Marcar una tarea como incompleta
- Eliminar una tarea por ID
- Exportar la lista de tareas a un archivo `.txt`
- Salir del sistema correctamente

---

## 2. Arquitectura y Estructura del Proyecto

```
src/
  main/java/org/example/
    model/           → Clase Tarea (entidad de datos)
    service/         → TareaService, ValidarTareaService (lógica de negocio)
    exception/       → Excepciones personalizadas
    Main.java        → Punto de entrada, menú de consola
  main/resources/
    log4j2.xml       → Configuración de logging
  test/java/org/example/service/
    TareaServiceTest.java
    TareaMainTest.java
    ValidarTareaServiceTest.java
```

### Descripción de Capas

| Capa          | Clase / Archivo            | Responsabilidad                                                                                                    |
| ------------- | -------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| Modelo        | `Tarea.java`               | Entidad con ID, nombre, descripción y estado                                                                       |
| Servicio      | `TareaService.java`        | Lógica de negocio: agregar tarea, listar tarea, completar tarea, tarea incompleta, eliminar tarea y exportar tarea |
| Validación    | `ValidarTareaService.java` | Centraliza validaciones de datos de entrada                                                                        |
| Excepciones   | `exception/*`              | Excepciones personalizadas para errores de negocio                                                                 |
| Entrada       | `Main.java`                | Menú interactivo, manejo global de excepciones y logging                                                           |
| Configuración | `log4j2.xml`               | Define appenders y niveles de log por paquete                                                                      |

---

## 3. Modelo de Datos

### Clase `Tarea`

La clase `Tarea` es la entidad principal. Usa un contador estático para generar IDs únicos y autoincrementales.

| Atributo              | Tipo      | Descripción                                      |
| --------------------- | --------- | ------------------------------------------------ |
| `contadorId` (static) | `int`     | Contador global para generar IDs automáticamente |
| `id`                  | `int`     | Identificador único de la tarea                  |
| `nombre`              | `String`  | Nombre de la tarea                               |
| `descripcion`         | `String`  | Descripción detallada de la tarea                |
| `estadoCompletado`    | `boolean` | `false` = pendiente, `true` = completada         |

---

## 4. Capa de Servicios

### `TareaService`

Gestiona una lista interna (`ArrayList`) de tareas y expone los métodos del sistema.

| Método                        | Parámetros            | Descripción                                | Excepción lanzada                                          |
| ----------------------------- | --------------------- | ------------------------------------------ | ---------------------------------------------------------- |
| `agregarTarea()`              | nombre, descripcion   | Valida datos y agrega una nueva tarea      | `DatosInvalidosException`                                  |
| `listarTareas()`              | —                     | Devuelve una copia de la lista de tareas   | —                                                          |
| `completarTarea()`            | id: int               | Marca la tarea como completada             | `TareaNoEncontradaException`, `TareaYaCompletadaException` |
| `marcarTareaComoIncompleta()` | id: int               | Revierte el estado de completado a `false` | `TareaNoEncontradaException`, `TareaYaIncompletaException` |
| `eliminarTarea()`             | id: int               | Elimina la tarea de la lista               | `TareaNoEncontradaException`                               |
| `buscarTareaId()`             | id: int               | Busca y retorna una tarea por ID           | `TareaNoEncontradaException`                               |
| `exportarTareas()`            | nombreArchivo: String | Escribe las tareas en un archivo `.txt`    | `ExportarTareaException` (checked)                         |

### `ValidarTareaService`

Centraliza las validaciones. Todos sus métodos son estáticos.

| Método                            | Validación                  | Excepción lanzada         |
| --------------------------------- | --------------------------- | ------------------------- |
| `validarNombre(nombre)`           | No debe ser `null` ni vacío | `DatosInvalidosException` |
| `validarDescripcion(descripcion)` | No debe ser `null` ni vacía | `DatosInvalidosException` |

---

## 5. Manejo de Excepciones

### Excepciones Personalizadas

| Clase                        | Tipo                  | Cuándo se lanza                                              |
| ---------------------------- | --------------------- | ------------------------------------------------------------ |
| `TareaNoEncontradaException` | `RuntimeException`    | Al buscar, completar o eliminar una tarea con ID inexistente |
| `TareaYaCompletadaException` | `RuntimeException`    | Al intentar completar una tarea ya completada                |
| `TareaYaIncompletaException` | `RuntimeException`    | Al intentar marcar como incompleta una tarea que ya lo está  |
| `DatosInvalidosException`    | `RuntimeException`    | Cuando nombre o descripción son nulos o vacíos               |
| `OpcionInvalidoException`    | `RuntimeException`    | Cuando el usuario ingresa texto no numérico en el menú       |
| `ExportarTareaException`     | `Exception` (checked) | Cuando ocurre un error de I/O al exportar las tareas         |

### Flujo en `Main.java`

```java
try {
    // Leer opción y ejecutar operación
} catch (OpcionInvalidoException e)    { log.error(...); }
  catch (DatosInvalidosException e)    { log.error(...); }
  catch (TareaNoEncontradaException e) { log.error(...); }
  catch (TareaYaIncompletaException e) { log.error(...); }
  catch (ExportarTareaException e)     { log.error(...); }
  catch (Exception e)                  { log.debug(...); }
} finally {
    log.debug("Fin del procesamiento de la opción.");
}
```

---

## 6. Logging con SLF4J y Log4j2

SLF4J actúa como fachada (API) y Log4j2 como implementación, desacoplando el código de la herramienta de logging.

### Appenders configurados (`log4j2.xml`)

| Appender           | Archivo destino                | Nivel mínimo |
| ------------------ | ------------------------------ | ------------ |
| `Console`          | Salida estándar                | `INFO`       |
| `TareaServiceFile` | `logs/tareaService.log`        | `DEBUG`      |
| `ValidacionFile`   | `logs/validarTareaService.log` | `DEBUG`      |

> Los archivos rotan diariamente y tienen un tamaño máximo de 10 MB (5 MB para validación), con retención de hasta 30 archivos.

### Uso de niveles de log

| Nivel   | Dónde se usa                       | Ejemplo                                              |
| ------- | ---------------------------------- | ---------------------------------------------------- |
| `INFO`  | Operaciones exitosas               | `"Tarea agregada exitosamente: ID {}"`               |
| `DEBUG` | Detalles internos y fin de ciclo   | `"Consultando lista completa. Total: {}"`            |
| `WARN`  | Situaciones inusuales no críticas  | `"Intento de completar tarea ya completada. ID: {}"` |
| `ERROR` | Validaciones fallidas, excepciones | `"Error: Tarea no encontrada con ID: {}"`            |

---

## 7. Pruebas Unitarias con JUnit 5

Se utiliza `@BeforeEach` para inicializar el servicio antes de cada prueba, garantizando aislamiento total entre casos de prueba.

### `TareaServiceTest`

| #   | Test                                     | Qué valida                              | Resultado esperado                         |
| --- | ---------------------------------------- | --------------------------------------- | ------------------------------------------ |
| 1   | `testAgregarTarea_Correcto`              | Agregar tarea con datos válidos         | No lanza excepción, lista tiene 1 elemento |
| 2   | `testAgregarTarea_DatosInvalidos`        | Agregar con nombre nulo o vacío         | `DatosInvalidosException`                  |
| 3   | `testBuscarTarea_NoExistente`            | Buscar ID inexistente                   | `TareaNoEncontradaException`               |
| 4   | `testCompletarTarea_Correcto`            | Completar tarea existente               | `estadoCompletado = true`                  |
| 5   | `testCompletarTarea_YaCompletada`        | Completar tarea ya completada           | `TareaYaCompletadaException`               |
| 6   | `testMarcarIncompleta_Correcto`          | Revertir tarea completada a incompleta  | `estadoCompletado = false`                 |
| 7   | `testMarcarIncompleta_YaIncompleta`      | Marcar como incompleta una que ya lo es | `TareaYaIncompletaException`               |
| 8   | `testEliminarTarea_Correcto`             | Eliminar tarea existente                | Lista queda vacía                          |
| 9   | `testEliminarTarea_NoExistente`          | Eliminar ID inexistente                 | `TareaNoEncontradaException`               |
| 10  | `testExportarTareas_Correcto`            | Exportar genera archivo en disco        | Archivo existe en el sistema               |
| 11  | `testExportarTareas_ErrorNombreInvalido` | Exportar con nombre vacío               | `ExportarTareaException`                   |

### `ValidarTareaServiceTest`

| #   | Test                                      | Entrada  | Resultado esperado        |
| --- | ----------------------------------------- | -------- | ------------------------- |
| 1   | `testvalidarNombreTarea_incorrecto_null`  | `null`   | `DatosInvalidosException` |
| 2   | `testvalidarNombreTarea_incorrecto_vacio` | `""`     | `DatosInvalidosException` |
| 3   | `testvalidarNombreTarea_correcto_nombre`  | `"Juan"` | No lanza excepción        |

### `TareaMainTest`

| #   | Test                             | Qué valida                              | Resultado esperado                            |
| --- | -------------------------------- | --------------------------------------- | --------------------------------------------- |
| 1   | `testLeerEntrada_Correcto`       | Lectura de texto desde Scanner simulado | Texto capturado coincide con la entrada       |
| 2   | `testConvertirEntero_Correcto`   | Conversión de `"123"` a entero          | Retorna `123`                                 |
| 3   | `testConvertirEntero_Incorrecto` | Entrada no numérica `"abc"`             | `OpcionInvalidoException` con mensaje `"abc"` |
| 4   | `testConvertirEntero_Vacio`      | Entrada vacía `""`                      | `OpcionInvalidoException` con mensaje `""`    |

---

## 8. Dependencias Maven

```xml
<!-- SLF4J API -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>

<!-- Log4j2 -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j2-impl</artifactId>
    <version>2.20.0</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.20.0</version>
</dependency>

<!-- JUnit 5 -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

---

## 9. Ejecución del Programa

Al iniciar la aplicación se muestra el siguiente menú:

```
<=== AppTareas - Gestion basica de tareas by Juan David ===>

--- MENÚ DE OPCIONES ---
1. Agregar Tarea
2. Listar Tareas
3. Marcar Tarea como Completada
4. Marcar Tarea como Incompletada
5. Eliminar Tarea
6. Exportar archivo
7. Salir
```

---

## 10. Criterios de Evaluación

| Criterio                       | Peso | Implementación                                                                                                                   |
| ------------------------------ | ---- | -------------------------------------------------------------------------------------------------------------------------------- |
| Manejo correcto de excepciones | 30%  | 6 excepciones personalizadas (5 unchecked + 1 checked), bloques `try-catch-finally`, excepciones diferenciadas por tipo de error |
| Implementación de logging      | 25%  | SLF4J + Log4j2, niveles INFO/DEBUG/WARN/ERROR, archivos de log separados por servicio con rotación                               |
| Pruebas unitarias funcionales  | 25%  | 14 pruebas en 3 clases de test, cobertura de casos de éxito y fallo, uso de `@BeforeEach` y `@DisplayName`                       |
| Funcionalidad general          | 20%  | 7 opciones de menú funcionales, ciclo `do-while` con salida controlada, exportación de archivo como funcionalidad extra          |
