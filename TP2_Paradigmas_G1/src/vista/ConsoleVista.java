package vista;

import java.util.Scanner;

public class ConsoleVista {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static String obtenerSimbolo() {
		scanner.nextLine();
		System.out.println("Ingrese Simbolo de Criptomoneda:");
		return scanner.nextLine();
	}
	
	public static void pause() {
		System.out.println("Presiona Enter para continuar...");
        scanner.nextLine(); 
	}
	
	public static void pauseDoble() {
		
		System.out.println("Presiona Enter para continuar...");
        scanner.nextLine(); 
        scanner.nextLine(); 
	}
}
