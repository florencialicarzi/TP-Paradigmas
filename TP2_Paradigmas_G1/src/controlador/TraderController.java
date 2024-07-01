package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Criptomoneda;
import modelo.Historico;
import modelo.Trader;
import modelo.Usuario;
import vista.TraderVista;


public class TraderController {
	private TraderVista TVista;
    private Trader trader;
    static private List<Trader> registrosTrader = new ArrayList<>();
    static private List<Historico> registrosHistorico = new ArrayList<>();
    
    public TraderController(TraderVista TraderVista, Trader trader) {
        this.TVista = TraderVista;
        this.trader = trader;
    }
    
	public void menu() {
        int opcion=0;
        do {
            opcion = TVista.mostrarMenu(); 
            switch (opcion) {
                case 1:
                    // Lógica para Comprar criptomoneda
                	System.out.println("Presione enter para comenzar la compra");
                    trader.Comprar();
                    break;
                case 2:
                    // Lógica para Vender criptomoneda
                	trader.Vender();
                    break;
                case 3:
                    // Lógica para Consultar criptomoneda
                	Usuario.ConsultarCriptomoneda();
                    break;
                case 4:
                    // Lógica para Recomendar criptomoneda
                	trader.Recomendar();
                    break;
                case 5:
                    // Lógica para consultar el mercado
                	Usuario.ConsultarEstadoActualMercado();
                    break;
                case 6:
                    // Lógica para consultar el mercado
                	trader.verHistorico();
                    break;
                case 7:
                    // Salir
                    break;
                default:
                	TVista.mostrarMensaje("Opción no válida.");
                	break;
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
		CSVController.escribirAlFinalArchivo("src/Archivos/Usuarios.csv", insercionCSV);
		
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
	
	public static List<Trader> getRegistrosTrader() {
		return registrosTrader;
	}
	
    private static void ImportCSVHistorico(String archivoCSV) {
	    List<String[]> registrosCSV = CSVController.importarCSVGenerico(archivoCSV);
	    	
	    	
	    for (String[] registro : registrosCSV) {

    		String simbolo = registro[0].trim();
            double valor = Double.parseDouble(registro[1]);
            
            Historico reg = new Historico(simbolo,valor); 
            registrosHistorico.add(reg);
	    }
	}
    
    public static List<Historico> getRegistrosHistoricos(String archivoCSV){
    	ImportCSVHistorico(archivoCSV);
    	return registrosHistorico;
    }
    
	
	public static void agregarTrader(Trader t) {
		registrosTrader.add(t);
	}
	
	public static void mostrarTraders() {
		registrosTrader.forEach(System.out::println);
    }
}
