package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Criptomoneda;
import modelo.Mercado;
import vista.ConsoleVista;
import vista.TraderVista;

public class CriptoMercadoController {
	static String pathCripto = "C:/Users/Florencia/Documents/Facultad/PLAN2023/3646-ParadigmasDeProgramacion/TP2_Paradigmas_G1/src/Archivos/Criptomonedas.csv";
	static String pathMercado = "C:/Users/Florencia/Documents/Facultad/PLAN2023/3646-ParadigmasDeProgramacion/TP2_Paradigmas_G1/src/Archivos/Mercado.csv";
	
	static List<Criptomoneda> registrosCriptomoneda = new ArrayList<>();
	static List<Mercado> registrosMercado = new ArrayList<>();
	
	
	public static void mostrarEstadoActualMercado() {
		
		ImportCSVMercado(pathMercado);
    	registrosMercado.forEach(System.out::println);
    	registrosMercado.clear();
    	ConsoleVista.pauseDoble();
    }
	
	public static void consultarCriptomoneda()
	{
		inicializarListas();
		String simbolo = ConsoleVista.obtenerSimbolo();
		if(mostrarEstadoCriptomoneda(simbolo)) {
			TraderVista.mostrarMensaje("Datos del Mercado:");
			mostrarEstadoMercado(simbolo);
		}
		else {
			TraderVista.mostrarMensaje("No se encontró la criptomoneda indicada");
		}
		registrosCriptomoneda.clear();
		registrosMercado.clear();
		ConsoleVista.pause();
	}
	
	
	private static boolean mostrarEstadoCriptomoneda(String simbolo) {
		for (Criptomoneda registro : registrosCriptomoneda) {
			if(registro.getSimbolo().equalsIgnoreCase(simbolo)) {
				System.out.println(registro.toString());
				return true;
			}
		}
		return false;
    }
	
	private static void mostrarEstadoMercado(String simbolo) {
		for (Mercado registro : registrosMercado) {
			if(registro.getSimbolo().equalsIgnoreCase(simbolo)) {
				System.out.println(registro.toString());
			}
		}
    }

	private static void inicializarListas() {
		ImportCSVCriptomoneda(pathCripto);
		ImportCSVMercado(pathMercado);
	}
	
	private static void ImportCSVCriptomoneda(String archivoCSV) {
	    List<String[]> registrosCSV = CSVController.importarCSVGenerico(archivoCSV);
	    	
	    	
	    for (String[] registro : registrosCSV) {
	    		
    		String nombre = registro[0].trim();
    		String simbolo = registro[1].trim();
            double valor = Double.parseDouble(registro[2]);
            
            Criptomoneda reg = new Criptomoneda(nombre,simbolo,valor); 
            registrosCriptomoneda.add(reg);
	    }
	}
	
    private static void ImportCSVMercado(String archivoCSV) {
    	List<String[]> registrosCSV = CSVController.importarCSVGenerico(archivoCSV);
    	
    	
    	for (String[] registro : registrosCSV) {
    		
    		String simbolo = registro[0].trim();
            double capacidad = Double.parseDouble(registro[1]);
            double volumen24hs = Double.parseDouble(registro[2]);
            double variacion7d = Double.parseDouble(registro[3]);
            
            Mercado reg = new Mercado(simbolo, capacidad, volumen24hs, variacion7d);
            registrosMercado.add(reg);
    	}
    }
    
    
}