package Usuario;

import App.Registro;

public class Usuario {
	protected String nombre;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public static void ConsultarCriptomoneda(String simbolo)
	{
        Registro.mostrarEstadoCriptomoneda(simbolo);
        Registro.mostrarEstadoMercado(simbolo);
	}
}
