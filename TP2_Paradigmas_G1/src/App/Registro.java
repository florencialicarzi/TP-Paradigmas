package App;

import java.util.ArrayList;
import java.util.List;

import Mercado.Criptomoneda;
import Mercado.Mercado;

public class Registro {
	static List<Criptomoneda> registrosCriptomoneda = new ArrayList<>();
	static List<Mercado> registrosMercado = new ArrayList<>();
	
	
	public static void mostrarEstadoCriptomoneda() {
		registrosCriptomoneda.forEach(System.out::println);
    }
	
	public static void mostrarEstadoMercado() {
    	registrosMercado.forEach(System.out::println);
    }
    
	public static void ImportCSVCriptomoneda(String archivoCSV) {
	    List<String[]> registrosCSV = ImportCSV.importarCSVGenerico(archivoCSV);
	    	
	    	
	    for (String[] registro : registrosCSV) {
	    		
    		String nombre = registro[0].trim();
    		String simbolo = registro[1].trim();
            double valor = Double.parseDouble(registro[2]);
            
            Criptomoneda reg = new Criptomoneda(nombre,simbolo,valor); //Tal vez pueda hacer otra clase 
            registrosCriptomoneda.add(reg);
	    }
	}
	
    public static void ImportCSVMercado(String archivoCSV) {
    	List<String[]> registrosCSV = ImportCSV.importarCSVGenerico(archivoCSV);
    	
    	
    	for (String[] registro : registrosCSV) {
    		
    		String simbolo = registro[0].trim();
            double capacidad = Double.parseDouble(registro[1]);
            double volumen24hs = Double.parseDouble(registro[2]);
            double variacion7d = Double.parseDouble(registro[3]);
            
            Mercado reg = new Mercado(simbolo, capacidad, volumen24hs, variacion7d); //Tal vez pueda hacer otra clase que sea Datos del Mercado
            registrosMercado.add(reg);
    	}
    }
}
