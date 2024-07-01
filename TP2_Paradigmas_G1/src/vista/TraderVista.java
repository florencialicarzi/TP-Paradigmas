package vista;

import java.util.Scanner;

public class TraderVista {
	static Scanner scanner = new Scanner(System.in);
	
	public static int mostrarMenu() {
		
        int opcion = 0;
        
            System.out.println("Menú de opciones");
            System.out.println("-----------------------");
            System.out.println("1) Comprar Criptomoneda");
            System.out.println("2) Vender Criptomoneda");
            System.out.println("3) Consultar Criptomoneda");
            System.out.println("4) Recomendar Criptomoneda");
            System.out.println("5) Consultar estado actual del mercado");
            System.out.println("6) Visualizar archivo de transacciones");
            System.out.println("7) Salir");
            System.out.print("Ingrese su opción (1 - 7): ");
            opcion = scanner.nextInt();
       
		return opcion;
    }
	
	public static void mostrarMensaje(String msj) {
		System.out.println(msj);
	}
	
	public static int obtenerInt(String msj) {
		System.out.println(msj);
		return scanner.nextInt();
	}
	
	public static double obtenerDouble(String msj) {
		System.out.println(msj);
		return scanner.nextDouble();
	}
	
	public static String obtenerNombre() {
		System.out.println("Ingrese su nombre:");
		return scanner.nextLine();
	}
	
	public static Integer obtenerNroCuenta() {
		System.out.println("Ingrese su nro de cuenta:");
		return scanner.nextInt();
	}
	
	public static String obtenerNombreBanco() {
		scanner.nextLine();
		System.out.println("Ingrese nombre del Banco:");
		return scanner.nextLine();
	}
	
	public static double obtenerSaldo() {
		System.out.println("Ingrese su saldo:");
		return scanner.nextDouble();
	}
	
	public static String obtenerSimbolo() {
		System.out.println("Obtener simbolo:");
		return scanner.nextLine();
	}
	
	public static int obtenerCantidad() {
		System.out.println("Ingrese cantidad:");
		return scanner.nextInt();
	}

}
