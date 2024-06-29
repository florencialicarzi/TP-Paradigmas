package modelo;

import java.util.ArrayList;
import java.util.List;

import controlador.ImportCSV;

public class Registro {
	static List<Criptomoneda> registrosCriptomoneda = new ArrayList<>();
	static List<Mercado> registrosMercado = new ArrayList<>();
	static List<Administrador> registrosAdmin = new ArrayList<>();
	static List<Trader> registrosTrader = new ArrayList<>();
	
	public static void mostrarEstadoCriptomonedas() {
		registrosCriptomoneda.forEach(System.out::println);
    }
	
	public static void mostrarEstadoActualMercado() {
    	registrosMercado.forEach(System.out::println);
    }
	
	public static void mostrarAdmins() {
		registrosAdmin.forEach(System.out::println);
    }
	
	public static void mostrarTraders() {
		registrosTrader.forEach(System.out::println);
    }

	//Estos dos no andan todavia
	public static void mostrarEstadoCriptomoneda(String simbolo) {
		for (Criptomoneda registro : registrosCriptomoneda) {
			if(registro.getSimbolo().equalsIgnoreCase(simbolo)) {
				System.out.println(registro.toString());
			}
		}
    }
	
	public static void mostrarEstadoMercado(String simbolo) {
		for (Mercado registro : registrosMercado) {
			if(registro.getSimbolo().equalsIgnoreCase(simbolo)) {
				System.out.println(registro.toString());
			}
		}
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
    
    public static void ImportCSVUsuarios(String archivoCSV) {
    	List<String[]> registrosCSV = ImportCSV.importarCSVGenerico(archivoCSV);
    	
    	
    	for (String[] registro : registrosCSV) {
    		
    		if(registro.length == 2){
    			String nomAdmin = registro[0].trim();
    			String perfil = registro[1].trim();
    			
    			Administrador ad = new Administrador(nomAdmin,perfil);
    			registrosAdmin.add(ad);
    		}
    		else{
    			String nomTrader = registro[0].trim();
    			Integer nrocuenta = Integer.parseInt(registro[1]);
    			String nomBanco = registro[2].trim();
    			double saldo = Double.parseDouble(registro[3]);
    			
    			registrosTrader.add(new Trader(nomTrader,nrocuenta,nomBanco,saldo));
    		}
                        
    	}
    }
}
