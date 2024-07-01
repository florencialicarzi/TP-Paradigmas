package vista;

import java.util.Scanner;

public class AdminVista implements IVista {
	static Scanner scanner = new Scanner(System.in);
	public int mostrarMenu() {
        
        int opcion = 0;
        
        
        System.out.println("Menú de opciones");
        System.out.println("-----------------------");
        System.out.println("1) Crear Criptomoneda");
        System.out.println("2) Modificar Criptomoneda");
        System.out.println("3) Eliminar Criptomoneda");
        System.out.println("4) Consultar Criptomoneda");
        System.out.println("5) Consultar estado actual del mercado");
        System.out.println("6) Salir");
        System.out.print("Ingrese su opción (1 - 6): ");
        opcion = scanner.nextInt();
        
		return opcion;
    }
	
	public void mostrarMensaje(String msj) {
		System.out.println(msj);
	}
	
	public static double obtenerDouble(String msj) {
		System.out.println(msj);
		return scanner.nextDouble();
	}

}
