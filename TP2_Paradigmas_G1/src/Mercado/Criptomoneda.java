package Mercado;

public class Criptomoneda {	
	private String nombre;
	private String Simbolo;
	private double valorUSD;
	
	public Criptomoneda(String nombre, String simbolo, double valorUSD) { //Como el administrados esta en el mismo Package puede acceder
		this.nombre = nombre;
		Simbolo = simbolo;
		this.valorUSD = valorUSD;
	}
	

	public String getSimbolo() {
		return Simbolo;
	}


	public void setSimbolo(String simbolo) {
		Simbolo = simbolo;
	}


	@Override
	public String toString() {
		return "Nombre:" + nombre + "		Simbolo:" + Simbolo + "		valorUSD:" + valorUSD + "";
	}
}
