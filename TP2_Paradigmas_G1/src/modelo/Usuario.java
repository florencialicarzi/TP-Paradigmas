package modelo;

import controlador.CriptoMercadoController;

public class Usuario {
	protected String nombre;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
	}
			
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public static void ConsultarCriptomoneda(){
        CriptoMercadoController.consultarCriptomoneda();
	}
	
	public static void ConsultarEstadoActualMercado() {
		CriptoMercadoController.mostrarEstadoActualMercado();
	}
}
