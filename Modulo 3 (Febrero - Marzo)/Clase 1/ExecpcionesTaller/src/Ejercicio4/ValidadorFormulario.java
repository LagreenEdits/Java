package Ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class ValidadorFormulario {
    public void validar(String nombre, String telefono, String cp) {
        List<Exception> errores = new ArrayList<>();

        // Validación de Nombre
        if (nombre == null || nombre.trim().length() < 3) {
            errores.add(new NombreInvalidoException("El nombre debe tener al menos 3 caracteres."));
        }

        // Validación de Teléfono (Regex para 10 dígitos)
        if (telefono == null || !telefono.matches("\\d{10}")) {
            errores.add(new TelefonoInvalidoException("El teléfono debe contener exactamente 10 dígitos numéricos."));
        }

        // Validación de Código Postal (Regex para 5 dígitos)
        if (cp == null || !cp.matches("\\d{5}")) {
            errores.add(new CodigoPostalInvalidoException("El código postal debe tener 5 dígitos."));
        }

        // Si la lista no está vacía, procesamos los errores
        if (!errores.isEmpty()) {
            System.out.println("--- Se encontraron errores en el formulario: ---");
            for (Exception e : errores) {
                System.out.println("- " + e.getMessage());
            }
        } else {
            System.out.println("Formulario validado con éxito. Procesando registro...");
        }
    }
}
