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
            opcion = AVista.mostrarMenu();
            switch (opcion) {
                case 1:
                    // Lógica para crear criptomoneda
                	admin.CrearCripto();
                    break;
                case 2:
                    // Lógica para modificar criptomoneda
                	admin.ModificarCripto();
                    break;
                case 3:
                    // Lógica para eliminar criptomoneda
                	admin.EliminarCripto();
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
                	AVista.mostrarMensaje("Opción no válida.");
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
