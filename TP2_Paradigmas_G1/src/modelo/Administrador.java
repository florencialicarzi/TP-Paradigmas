package modelo;

public class Administrador extends Usuario{

	private String perfil;
	
	public Administrador(String nombre, String perfil) {
		super(nombre);
		this.perfil = perfil;
	}

	public void CrearCripto(){
		
	}
	
	public void ModificarCripto() {
		
	}
	
	public void EliminarCripto() {
			
	}

	public void ConsultarCripto() {
		
	}
	
	@Override
	public String toString() {
		return "Administrador [Nombre=" + super.nombre+ ", " + "perfil=" + perfil + "]";
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
