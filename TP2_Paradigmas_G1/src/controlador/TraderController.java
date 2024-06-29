package controlador;

import modelo.Trader;
import vista.MenuTrader;

public class TraderController {
	private MenuTrader TraderVista;
    private Trader trader;
    
    public TraderController(MenuTrader TraderVista, Trader trader) {
        this.TraderVista = TraderVista;
        this.trader = trader;
    }


	public static void menu() {
        int opcion;
        do {
            opcion = MenuTrader.mostrarMenu();
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
                	MenuTrader.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 7);
    }
}
