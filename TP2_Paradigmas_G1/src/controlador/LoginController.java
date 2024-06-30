package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Administrador;
import modelo.Trader;

import vista.Login;
import vista.AdminVista;
import vista.TraderVista;

import controlador.ImportCSV;

public class LoginController {
    
	


    public static void iniciar() {
        
    	cargarUsuarios();
    	
		String nombreUsuario = Login.pedirNombreUsuario();
		Administrador admin = AdminController.buscarAdmin(nombreUsuario);
		Trader trader = TraderController.buscarTrader(nombreUsuario);
		
		if(admin != null) {
			new AdminController(new AdminVista(), admin).menu();
		}
		else if(trader != null) {
			new TraderController(new TraderVista(), trader).menu();
		}
		else{
			Login.mostrarMensaje("No se encontró el usuario indicado");
			int res = Login.DeseaRegistrarse();
			if(res == 1) {
				Trader nuevoTrader = TraderController.RegistroTrader();
				
				Login.mostrarMensaje("Trader creado");
			}
		}
		
		System.out.println("Finalizado");
		//registrar nuevo trader
    }
    
    private static void cargarUsuarios() { 
       String pathUsuarios = "C:/Users/Florencia/Documents/Facultad/PLAN2023/3646-ParadigmasDeProgramacion/TP2_Paradigmas_G1/src/Archivos/Usuarios.csv";
       ImportCSVUsuarios(pathUsuarios);
    }

    private static void ImportCSVUsuarios(String archivoCSV) {
    	List<String[]> registrosCSV = ImportCSV.importarCSVGenerico(archivoCSV);
    	
    	
    	for (String[] registro : registrosCSV) {
    		
    		if(registro.length == 2){
    			String nomAdmin = registro[0].trim();
    			String perfil = registro[1].trim();
    			
    			Administrador ad = new Administrador(nomAdmin,perfil);
    			AdminController.agregarAdmin(ad);
    		}
    		else{
    			String nomTrader = registro[0].trim();
    			Integer nrocuenta = Integer.parseInt(registro[1]);
    			String nomBanco = registro[2].trim();
    			double saldo = Double.parseDouble(registro[3]);
    			
    			TraderController.agregarTrader(new Trader(nomTrader,nrocuenta,nomBanco,saldo));
    		}
                        
    	}
    }
    
}
