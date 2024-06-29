package vista;

import java.util.Scanner;

public class Login {
	private static Scanner scanner = new Scanner(System.in);

    public static String pedirNombreUsuario() {
        System.out.print("Ingrese su nombre de usuario: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String msj) {
        System.out.println(msj);
    }
}
