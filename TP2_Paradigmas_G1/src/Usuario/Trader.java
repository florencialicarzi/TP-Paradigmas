package Usuario;

import java.util.ArrayList;
import Mercado.Criptomoneda;

public class Trader extends Usuario {
	private Integer cuentaBancaria;
	private String nombreBanco;
	private double saldoActual;
	private ArrayList<Criptomoneda> billetera = new ArrayList<Criptomoneda>();
	
	public Trader(String nombre, Integer cuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.cuentaBancaria = cuentaBancaria;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}

	@Override
	public String toString() {
		return "Trader [Nombre="+ super.nombre+ ", " + "cuentaBancaria=" + cuentaBancaria+ ", " + ", nombreBanco=" + nombreBanco+ ", " + ", saldoActual="
				+ saldoActual + "]";
	}
	

	
}
