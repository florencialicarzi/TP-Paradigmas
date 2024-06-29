package vista;

import java.util.Scanner;
import controlador.TraderController;

public class MenuTrader {
	
	public static int mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
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

}
