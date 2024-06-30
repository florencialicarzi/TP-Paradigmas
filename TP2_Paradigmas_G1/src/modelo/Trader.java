package modelo;

import java.util.ArrayList;
import java.util.Scanner;


import controlador.CSVController;

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
	
	public void Comprar(){
		
	}
	
	public void Vender() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el simbolo:");
		String simbolo = scanner.nextLine();
		double monto;
		
		String archivoCSV = "C:/Users/Florencia/Documents/Facultad/PLAN2023/3646-ParadigmasDeProgramacion/TP2_Paradigmas_G1/src/Archivos/flicarzi_historico.csv";
		String MercadoCSV = "C:/Users/Florencia/Documents/Facultad/PLAN2023/3646-ParadigmasDeProgramacion/TP2_Paradigmas_G1/src/Archivos/Mercado.csv";
		
		String[] regHistorico = CSVController.buscarRegistroCSV(archivoCSV, simbolo, 0);
		String[] regMercado;
		
		if(regHistorico != null){
			System.out.println(simbolo + " disponibles para la venta: " + regHistorico[1]+ "\nIngrese el monto a vender:");
			monto = scanner.nextDouble();
			if(monto<0 || (monto > Double.parseDouble(regHistorico[1]))) {
				System.out.println("Monto no valido");
				return;
			}
			regMercado = CSVController.buscarRegistroCSV(archivoCSV, simbolo, 0);		
		}
		else {
			System.out.println("No posee esta criptomoneda");
		}
    }
	
	@Override
	public String toString() {
		return "Trader [Nombre="+ super.nombre+ ", " + "cuentaBancaria=" + cuentaBancaria+ ", " + ", nombreBanco=" + nombreBanco+ ", " + ", saldoActual="
				+ saldoActual + "]";
	}
	
	public void Recomendar(){
		
	}
	
	public void verHistorico(){
		
	}

	
}
