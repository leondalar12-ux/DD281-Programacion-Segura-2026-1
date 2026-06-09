import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int intentos = 0;

        while (intentos < 3) {
            System.out.print("Usuario: ");
            String usuario = sc.nextLine();

            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            boolean valida = true;

            if (pass.length() < 8) {
                System.out.println("Debe tener al menos 8 caracteres.");
                valida = false;
            }
            if (!pass.matches(".*\\d.*")) {
                System.out.println("Debe contener al menos un número.");
                valida = false;
            }
            if (!pass.matches(".*[A-Z].*")) {
                System.out.println("Debe contener al menos una mayúscula.");
                valida = false;
            }

            if (valida) {
                System.out.println("Acceso concedido. Bienvenido, " + usuario);
                return;
            }

            intentos++;
        }

        System.out.println("Cuenta bloqueada por 3 intentos fallidos.");
        sc.close();
    }
}