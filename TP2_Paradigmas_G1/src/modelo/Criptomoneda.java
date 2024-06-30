package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import controlador.LoginController;
import controlador.TraderController;

public class Criptomoneda {	
	private String nombre;
	private String Simbolo;
	private double valorUSD;
	
	public Criptomoneda(String nombre, String simbolo, double valorUSD) { //Como el administrados esta en el mismo Package puede acceder
		this.nombre = nombre;
		Simbolo = simbolo;
		this.valorUSD = valorUSD;
	}
	
	public Criptomoneda(String linea)
	{
		String[] campo = new String[3];
		campo = linea.split(";");
		
		System.out.println(linea);
		
		this.nombre = campo[0];
		this.Simbolo = campo[1];
		this.valorUSD = Double.parseDouble(campo[2]);
	}
	
	public static Criptomoneda buscarCripto(String simbolo) {

		String criptomoneda;
		String archCriptoCSV = "Archivos/Criptomonedas.csv";
		
		InputStream is = Criptomoneda.class.getClassLoader().getResourceAsStream(archCriptoCSV);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is)))
		{
			while ((criptomoneda = br.readLine()) != null)
			
				if(simbolo.equals(criptomoneda.substring(criptomoneda.indexOf(";")+1, criptomoneda.lastIndexOf(";"))))
					return new Criptomoneda(criptomoneda);
		}
		catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}
		return null;
	}

	public static int obtenerTotalCripto(String simbolo) {
		
		LoginController.cargarUsuarios();
		List<Trader> usuarios = TraderController.getRegistrosTrader();
		String criptomoneda;
		int cantidad = 0;

		for(Trader trader : usuarios)
		{
			String archCriptoCSV = "src/Archivos/" + trader.nombre + "_Historico.csv";
			
			File inputFile = new File(archCriptoCSV);
			
			if(inputFile.exists())
			try (BufferedReader br = new BufferedReader(new FileReader(archCriptoCSV)))
			{	
				while ((criptomoneda = br.readLine()) != null)
				{
					if(simbolo.equals(criptomoneda.substring(0, criptomoneda.indexOf(";"))))
						cantidad += Integer.parseInt(criptomoneda.substring(criptomoneda.indexOf(";")+1));
				}
			}
			catch (Exception e) {
				throw new RuntimeException("Error al leer el archivo");
			}
		}
		return cantidad;
	}
	
	public String getSimbolo() {
		return Simbolo;
	}


	public void setSimbolo(String simbolo) {
		Simbolo = simbolo;
	}
	
	public double getValorUSD() {
		return valorUSD;
	}

	public void incValorDiezPorciento() {
		this.valorUSD *= 1.1;
	}
	
	

	@Override
	public String toString() {
		return "Nombre:" + nombre + "		Simbolo:" + Simbolo + "		valorUSD:" + valorUSD + "";
	}
}
