package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Administrador;
import modelo.Usuario;
import vista.AdminVista;

public class AdminController {
	private AdminVista AVista;
    private Administrador admin;
    static private List<Administrador> registrosAdmin = new ArrayList<>();
    
    public AdminController(AdminVista AdminVista, Administrador admin) {
        this.AVista = AdminVista;
        this.admin = admin;
    }


	public void menu() {
        int opcion;
        do {
            opcion = AdminVista.mostrarMenu();
            switch (opcion) {
                case 1:
                    // Lógica para crear criptomoneda
                	AdminVista.mostrarMensaje(">>>>crear criptomoneda.");
                    break;
                case 2:
                    // Lógica para modificar criptomoneda
                	AdminVista.mostrarMensaje(">>>>modificar criptomoneda.");
                    break;
                case 3:
                    // Lógica para eliminar criptomoneda
                    break;
                case 4:
                    // Lógica para consultar criptomoneda
                	Usuario.ConsultarCriptomoneda();
                    break;
                case 5:
                    // Lógica para consultar el mercado
                	Usuario.ConsultarEstadoActualMercado();
                    break;
                case 6:
                    // Salir
                    break;
                default:
                	AdminVista.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 6);
    }
	
	public static Administrador buscarAdmin(String nombre) {
    	for (Administrador admin : registrosAdmin) {
    		if(admin.getNombre().equalsIgnoreCase(nombre)) {
    			return admin;
    		}
    	}
    	return null;
    }
	
	public static void agregarAdmin(Administrador a) {
		registrosAdmin.add(a);
	}
	
	public static void mostrarAdmins() {
		registrosAdmin.forEach(System.out::println);
    }
	
}
