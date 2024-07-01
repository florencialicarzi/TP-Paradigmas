package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Mercado {
	
	private String simbolo;
	private double capacidad;
	private double volumen24hs;
	private double variacion7d;

	public Mercado(String simbolo, double capacidad, double volumen24Horas, double variacion7Dias) {
		this.simbolo = simbolo;
		this.capacidad = capacidad;
		this.volumen24hs = volumen24Horas;
		this.variacion7d = variacion7Dias;
	}

	public Mercado(String linea) {
		String[] campo = new String[4];
		campo = linea.split(";");

		this.simbolo = campo[0];
		this.capacidad = Double.parseDouble(campo[1]);
		this.volumen24hs = Double.parseDouble(campo[2]);
		this.variacion7d = Double.parseDouble(campo[3]);
	}

	public void actualizarMercadoCompra(double cantidad, double porcentaje) {
		this.capacidad -= cantidad;
		this.volumen24hs += porcentaje;
		this.variacion7d += porcentaje;
	}

	public static Mercado buscarMercado(String simbolo) {

		String mercado;
		String archMercadoCSV = "Archivos/Mercado.csv";
		InputStream is = Criptomoneda.class.getClassLoader().getResourceAsStream(archMercadoCSV);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while ((mercado = br.readLine()) != null)
				if (simbolo.equals(mercado.substring(0, mercado.indexOf(";"))))
					return new Mercado(mercado);
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}
		return null;
	}

	public boolean actualizarRegMercado() {

		String mercado;

		File archTempCSV = new File("src/Archivos/MercaTemp.csv");
		File archMercadoCSV = new File("src/Archivos/Mercado.csv");

		try (BufferedReader br = new BufferedReader(new FileReader(archMercadoCSV));
				BufferedWriter bw = new BufferedWriter(new FileWriter(archTempCSV))) {

			while ((mercado = br.readLine()) != null) {
				if (simbolo.equals(mercado.substring(0, mercado.indexOf(";"))))
					mercado = this.simbolo + ";" + this.capacidad + ";" + this.volumen24hs + ";" + this.variacion7d;
				bw.write(mercado);
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}

		// ELIMINAR ANTIGUO
		if (!archMercadoCSV.delete()) {
			throw new RuntimeException("No se pudo eliminar el archivo original");
		}

		// RENOMBRAR TEMP
		if (!archTempCSV.renameTo(archMercadoCSV)) {
			throw new RuntimeException("No se pudo renombrar el archivo temporal");
		}

		return true;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public double getCapacidad() {
		return capacidad;
	}

	@Override
	public String toString() {
		return simbolo + ">>> Capacidad:" + capacidad + "		Volumen en las ultimas 24 horas:" + volumen24hs * 100
				+ "%" + "		Variacion en los ultimos 7 dias:" + variacion7d * 100 + "%" + ' ';
	}
}
