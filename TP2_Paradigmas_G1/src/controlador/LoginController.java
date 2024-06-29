package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Administrador;
import modelo.Trader;
import modelo.Usuario;
import vista.Login;

public class LoginController {
    private Login loginView;
    static List<Administrador> registrosAdmin = new ArrayList<>();
	static List<Trader> registrosTrader = new ArrayList<>();

    public LoginController(Login loginView) {
        this.loginView = loginView;
    }

    public void iniciar() {
        
		String nombreUsuario = Login.pedirNombreUsuario();
		
			
    }

    private Usuario buscarUsuario(String nombre) {
    	for (Administrador admin : registrosAdmin) {
    		if(admin.getNombre().equalsIgnoreCase(nombre)) {
    			return admin;
    		}
    	}
    	
    	for (Trader trader : registrosTrader) {
    		if(trader.getNombre().equalsIgnoreCase(nombre)) {
    			return trader;
    		}
    	}
    	
		return null;
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
