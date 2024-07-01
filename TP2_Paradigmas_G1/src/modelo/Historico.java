package modelo;

public class Historico {
	private String simbolo;
	private double cant;
	
	public Historico(String simbolo, double cant) {
		this.simbolo = simbolo;
		this.cant = cant;
	}
	
	public Historico(String linea)
	{
		String[] campo = new String[2];
        campo = linea.split(";");
        
        this.simbolo = campo[0];
        this.cant = Double.parseDouble(campo[1]);
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	public double getCant() {
		return cant;
	}
	public void setCant(double cant) {
		this.cant = cant;
	}

	@Override
	public String toString() {
		return (simbolo + ">>>" + cant );
	}
	
	
}
