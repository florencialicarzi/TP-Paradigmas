package vista;

import java.util.Scanner;

public class LoginVista {
	private static Scanner scanner = new Scanner(System.in);

    public static String pedirNombreUsuario() {
        System.out.print("Ingrese su nombre de usuario: ");
        return scanner.nextLine();
    }

    public static void mostrarMensaje(String msj) {
        System.out.println(msj);
    }
    
    public static int DeseaRegistrarse() {
    	System.out.println("¿Desea Registrarse como Trader? Si: 1 | No:0");
    	return scanner.nextInt();
    }
}
