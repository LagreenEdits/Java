import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int opcion;
        do {
            MostrarMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:

            }
        } while (opcion != 0);

    }
}

static void MostrarMenu() {
    System.out.println("1. Opción 1");
    System.out.println("2. Opción 2");
    System.out.println("3. Salir");
}
