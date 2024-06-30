package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Trader;
import vista.TraderVista;


public class TraderController {
	private TraderVista TVista;
    private Trader trader;
    static private List<Trader> registrosTrader = new ArrayList<>();
    
    public TraderController(TraderVista TraderVista, Trader trader) {
        this.TVista = TraderVista;
        this.trader = trader;
    }
    
	public void menu() {
        int opcion;
        do {
            opcion = TraderVista.mostrarMenu();
            switch (opcion) {
                case 1:
                    // Lógica para Comprar criptomoneda
                    break;
                case 2:
                    // Lógica para Vender criptomoneda
                    break;
                case 3:
                    // Lógica para Consultar criptomoneda
                    break;
                case 4:
                    // Lógica para Recomendar criptomoneda
                    break;
                case 5:
                    // Lógica para consultar el mercado
                    break;
                case 6:
                    // Lógica para consultar el mercado
                    break;
                case 7:
                    // Salir
                    break;
                default:
                	TraderVista.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 7);
    }
	
	public static Trader  RegistroTrader() {
		
		String nombre = TraderVista.obtenerNombre();
		Integer nroCuenta = TraderVista.obtenerNroCuenta();
		String nombreBanco = TraderVista.obtenerNombreBanco();
		double saldo = TraderVista.obtenerSaldo();
		
		Trader nuevoTrader = new Trader(nombre, nroCuenta, nombreBanco, saldo);
		registrosTrader.add(nuevoTrader);
		String insercionCSV = nombre + ";"+ nroCuenta + ";" + nombreBanco + ";" +saldo;
		ImportCSV.escribirAlFinalArchivo("C:/Users/IvanAbaca/Documents/UNLAM/Materias/03646PARADIGMAS DE PROGRAMACION/TP/TP2_Paradigmas_G1/TP-Paradigmas/TP2_Paradigmas_G1/src/Archivos/Usuarios.csv", insercionCSV);
		
		return nuevoTrader; //Agregar validacion sobre si se pudo crear
    }
	
	public static Trader buscarTrader(String nombre) {
    	for (Trader trader : registrosTrader) {
    		if(trader.getNombre().equalsIgnoreCase(nombre)) {
    			return trader;
    		}
    	} 	
		return null;
    }
	
	public static void agregarTrader(Trader t) {
		registrosTrader.add(t);
	}
	
	public static void mostrarTraders() {
		registrosTrader.forEach(System.out::println);
    }
	
    public static List<Trader> getRegistrosTrader() {
        return registrosTrader;
    }
}
