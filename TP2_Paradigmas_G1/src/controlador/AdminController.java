package controlador;

import modelo.Administrador;
import vista.MenuAdministrador;

public class AdminController {
	private MenuAdministrador AdminVista;
    private Administrador admin;
    
    public AdminController(MenuAdministrador AdminVista, Administrador admin) {
        this.AdminVista = AdminVista;
        this.admin = admin;
    }


	public static void menu() {
        int opcion;
        do {
            opcion = MenuAdministrador.mostrarMenu();
            switch (opcion) {
                case 1:
                    // Lógica para crear criptomoneda
                	MenuAdministrador.mostrarMensaje(">>>>crear criptomoneda.");
                    break;
                case 2:
                    // Lógica para modificar criptomoneda
                	MenuAdministrador.mostrarMensaje(">>>>modificar criptomoneda.");
                    break;
                case 3:
                    // Lógica para eliminar criptomoneda
                    break;
                case 4:
                    // Lógica para consultar criptomoneda
                    break;
                case 5:
                    // Lógica para consultar el mercado
                    break;
                case 6:
                    // Salir
                    break;
                default:
                	MenuAdministrador.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 6);
    }
}
