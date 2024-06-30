package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.ImportCSV;

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

	
	public void vender() {
        /*
        Para el caso de la venta, se debe seleccionar el símbolo de la criptomoneda y el
        sistema debe exhibir la cantidad máxima que puede vender. Dicha cantidad se debe
        obtener del archivo histórico del usuario. Luego, el usuario debe ingresar la cantidad
        a vender, si dicha cantidad es superior a la indicada se debe emitir un mensaje de
        error y continuar con la ejecución del menú.
        ○ En el caso de que se confirme la venta, se debe aumentar la capacidad en el
        archivo de mercados.csv de dicha criptomoneda y disminuir un 7% el valor
        los parámetros volúmen en las últimas 24 horas y variación en los últimos 7
        días. Luego de realizada la operación se debe actualizar el archivo histórico
        del usuario modificando la cantidad (disminuir la cantidad) y actualizar el saldo
        del usuario. 
        */
		Scanner scanner = new Scanner(System.in);
		String simbolo = scanner.nextLine();
		
		String archivoCSVHistorico = "C:/Users/Florencia/Desktop/TP_Paradigmas/Archivos/flicarzi_historico.csv";        
		List<String[]> registrosCSV = ImportCSV.importarCSVGenerico(archivoCSVHistorico);
    }
	
	@Override
	public String toString() {
		return "Trader [Nombre="+ super.nombre+ ", " + "cuentaBancaria=" + cuentaBancaria+ ", " + ", nombreBanco=" + nombreBanco+ ", " + ", saldoActual="
				+ saldoActual + "]";
	}
	

	
}
