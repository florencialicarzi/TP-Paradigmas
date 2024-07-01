package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import controlador.LoginController;
import controlador.TraderController;

public class Criptomoneda {
	private String nombre;
	private String Simbolo;
	private double valorUSD;

	public Criptomoneda(String nombre, String simbolo, double valorUSD) { 
																			
		this.nombre = nombre;
		Simbolo = simbolo;
		this.valorUSD = valorUSD;
	}

	public Criptomoneda(String linea) {
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

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while ((criptomoneda = br.readLine()) != null)

				if (simbolo
						.equals(criptomoneda.substring(criptomoneda.indexOf(";") + 1, criptomoneda.lastIndexOf(";"))))
					return new Criptomoneda(criptomoneda);
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}
		return null;
	}

	public static int obtenerTotalCripto(String simbolo) {

		LoginController.cargarUsuarios();
		List<Trader> usuarios = TraderController.getRegistrosTrader();
		String criptomoneda;
		int cantidad = 0;

		for (Trader trader : usuarios) {
			String archCriptoCSV = "src/Archivos/" + trader.nombre + "_Historico.csv";

			File inputFile = new File(archCriptoCSV);

			if (inputFile.exists())
				try (BufferedReader br = new BufferedReader(new FileReader(archCriptoCSV))) {
					while ((criptomoneda = br.readLine()) != null) {
						if (simbolo.equals(criptomoneda.substring(0, criptomoneda.indexOf(";"))))
							cantidad += Integer.parseInt(criptomoneda.substring(criptomoneda.indexOf(";") + 1));
					}
				} catch (Exception e) {
					throw new RuntimeException("Error al leer el archivo");
				}
		}
		return cantidad;
	}

	public int cargarComprasCripto(double cantidad) {

		String criptomoneda;
		double miles = 0;

		String archTempCSV = "src/Archivos/LogTemp.csv";
		String archCriptoCSV = "src/Archivos/LogCompras.csv";

		File inputFile = new File(archCriptoCSV);
		File tempFile = new File(archTempCSV);

		try {
			if (!inputFile.exists())
				inputFile.createNewFile();
		} catch (Exception e) {
			throw new RuntimeException("El archivo no existia y no se pudo crear.");
		}

		try (BufferedReader br = new BufferedReader(new FileReader(archCriptoCSV));
				BufferedWriter bw = new BufferedWriter(new FileWriter(archTempCSV))) {
			br.readLine();
			while ((criptomoneda = br.readLine()) != null) {
				if (Simbolo.equals(criptomoneda.substring(0, criptomoneda.indexOf(";")))) {
					cantidad += Double.parseDouble(criptomoneda.substring(criptomoneda.indexOf(";") + 1));
					criptomoneda = Simbolo + ";" + (cantidad % 1000);
					miles = (cantidad - cantidad % 1000) / 1000;
				}
				bw.write(criptomoneda);
				bw.newLine();
				bw.flush();
			}
			if (criptomoneda == null) {
				criptomoneda = this.Simbolo + ";" + cantidad % 1000;
				bw.write("simbolo;cant");
				bw.write(criptomoneda);
				miles = (cantidad - cantidad % 1000) / 1000;
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}

		// ELIMINAR ANTIGUO
		if (!inputFile.delete()) {
			throw new RuntimeException("No se pudo eliminar el archivo original");
		}

		// RENOMBRAR TEMP
		if (!tempFile.renameTo(inputFile)) {
			throw new RuntimeException("No se pudo renombrar el archivo temporal");
		}

		return (int) miles;
	}

	public boolean actualizarRegCripto() {

		String criptomoneda;

		File archTempCSV = new File("src/Archivos/CriptoTemp.csv");
		File archCriptoCSV = new File("src/Archivos/Criptomonedas.csv");

		try (BufferedReader br = new BufferedReader(new FileReader(archCriptoCSV));
				BufferedWriter bw = new BufferedWriter(new FileWriter(archTempCSV))) {

			while ((criptomoneda = br.readLine()) != null) {
				if (Simbolo
						.equals(criptomoneda.substring(criptomoneda.indexOf(";") + 1, criptomoneda.lastIndexOf(";"))))
					criptomoneda = this.nombre + ";" + this.Simbolo + ";" + this.valorUSD;
				bw.write(criptomoneda);
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}

		// ELIMINAR ANTIGUO
		if (!archCriptoCSV.delete()) {
			throw new RuntimeException("No se pudo eliminar el archivo original");
		}

		// RENOMBRAR TEMP
		if (!archTempCSV.renameTo(archCriptoCSV)) {
			throw new RuntimeException("No se pudo renombrar el archivo temporal");
		}

		return true;
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

	public void aumentoPrecio(int aumento) {
		this.valorUSD += valorUSD * aumento / 10;
	}

	@Override
	public String toString() {
		return "Nombre:" + nombre + "		Simbolo:" + Simbolo + "		valorUSD:" + valorUSD + "";
	}
}
