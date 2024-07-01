package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Administrador extends Usuario {

	private String perfil;

	public Administrador(String nombre, String perfil) {
		super(nombre);
		this.perfil = perfil;
	}

	public boolean CrearCripto() { // 1% en alza para una nueva criptomoneda en el archivo de mercado (cuando
		// genero nueva divisa).

		String archCriptoCSV = "src/Archivos/Criptomonedas.csv";
		String archMercadoCSV = "src/Archivos/Mercado.csv";
		FileWriter fileCripto = null;
		FileWriter fileMercado = null;
		PrintWriter printerWriterCripto = null;
		PrintWriter printerWriterMercado = null;
		String linea;
		String respuesta;
		BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));

		try {

		Criptomoneda criptoNueva = new Criptomoneda();

		criptoNueva.crearCriptoVerif();

		BufferedReader br = new BufferedReader(new FileReader(archCriptoCSV));

		String[] lineaParseo = new String[10];

		while ((linea = br.readLine()) != null) { // Fijarme que pueda leer y ademas que no este repitido, caso
		// contrario damos de alta uno nuevo al final de csv.

		lineaParseo = linea.split(";");

		if (criptoNueva.getNombre().equals(lineaParseo[0])) {
		System.out.println("El registro esta repetido, ingrese 1 para Modificar el Simbolo o 2 para Salir: ");
		respuesta = brInput.readLine();

		if(respuesta.equals("1")) {
		br.close();
		return this.ModificarCripto();
		}
		else {
		System.out.println("La criptomoneda ya existe en el sistema. ");
		br.close();
		return false;
		}
		}

		}

		// Si pase el while de arriba es porque el registro no existe, entonces lo
		// inserto al final.

		fileCripto = new FileWriter(archCriptoCSV, true);
		printerWriterCripto = new PrintWriter(fileCripto);

		printerWriterCripto.println(criptoNueva.getNombre() + ";" + criptoNueva.getSimbolo() + ";" + criptoNueva.getValorUSD());
		printerWriterCripto.flush();

		// Una vez que escribimos en el archivo de cripto, hay que impactarla en el mercado.

		fileMercado = new FileWriter(archMercadoCSV, true);
		printerWriterMercado = new PrintWriter(fileMercado);

		printerWriterMercado.println(criptoNueva.getSimbolo() + ";500;1;1");
		printerWriterMercado.flush();

		br.close();

		}

		catch (Exception e) {
		e.printStackTrace();
		}

		printerWriterCripto.close();
		printerWriterMercado.close();

		return true;

		}
	public boolean ModificarCripto() {

		File archCriptoCSV = new File("src/Archivos/Criptomonedas.csv");
		File archCriptoCSVTemp = new File("src/Archivos/CriptomonedasTemp.csv");
		File archMercadoCSV = new File("src/Archivos/Mercado.csv");
		File archMercadoCSVTemp = new File("src/Archivos/MercadoTemp.csv");
		BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
		String NombreCripto;
		String CampoAModif = null;
		String res;
		String lineaCripto = null;
		String lineaMercado = null;
		Boolean verifNum = false;
		Boolean Existencia = false;
		String[] lineaParse = new String[10];
		String[] lineaParseMercado = new String[10];

		try {

			BufferedReader brFileCripto = new BufferedReader(new FileReader(archCriptoCSV));
			BufferedReader brFileMercado = new BufferedReader(new FileReader(archMercadoCSV));
			BufferedWriter brWriterCripto = new BufferedWriter(new FileWriter(archCriptoCSVTemp));
			BufferedWriter brWriterMercado = new BufferedWriter(new FileWriter(archMercadoCSVTemp));

			
			System.out.println("Ingrese el nombre de la cripto a la cual quiere modificar: ");
			NombreCripto = brInput.readLine();

			System.out.println("1) Para modificar el Nombre");
			System.out.println("2) Para modificar el Simbolo");
			System.out.println("3) Para modificar el ValorUSD");
			res = brInput.readLine();

			while ((lineaCripto = brFileCripto.readLine()) != null
					&& (lineaMercado = brFileMercado.readLine()) != null) {

				lineaParse = lineaCripto.split(";"); // Reedimensiono el array.

				if (NombreCripto.equals(lineaParse[0])) { // Si los nombres coinciden, existe la cripto y hay que
															// actualizarla.

					Existencia = true;

					if (res.equals("1")) {
						
						do {
							System.out.println("Ingrese el nuevo nombre a modificar para la cripto " + NombreCripto + " :");
							CampoAModif = brInput.readLine();
							
							if(CampoAModif.length() == 0)
								System.out.println("El nombre no puede ser vacio.");
							
						} while(CampoAModif.length() == 0);

						lineaCripto = CampoAModif + ";" + lineaParse[1] + ";" + lineaParse[2];
						
						brWriterCripto.write(lineaCripto);
						brWriterCripto.newLine();
						brWriterCripto.flush();
					}

					if (res.equals("2")) {

						do {
							
							System.out.println("Ingrese simbolo de la cripto:");
							CampoAModif = brInput.readLine();
							
							if(CampoAModif.length() == 0)
								System.out.println("El simbolo no puede ser vacio.");
						
						} while(CampoAModif.length() == 0);

						lineaCripto = NombreCripto + ";" + CampoAModif + ";" + lineaParse[2];
						lineaParseMercado = lineaMercado.split(";");

						lineaMercado = CampoAModif + ";" + lineaParseMercado[1] + ";" + lineaParseMercado[2]
												  + ";" + lineaParseMercado[3];
						
						brWriterCripto.write(lineaCripto);
						brWriterCripto.newLine();
						brWriterCripto.flush();
						
						brWriterMercado.write(lineaMercado);
						brWriterMercado.newLine();
						brWriterMercado.flush();
					}

					if (res.equals("3")) {
						
						do {
							
							try {
								
								System.out.println("Ingrese el nuevo valorUSD para la cripto " + NombreCripto + " :");
								CampoAModif = brInput.readLine();
								Double.parseDouble(CampoAModif);
								verifNum = true;
							}
							
							catch(NumberFormatException e) {
								System.out.println("Debe ingresar un numero valido.");
							}
						
						} while(!verifNum);

						lineaCripto = NombreCripto + ";" + lineaParse[1] + ";" + CampoAModif;
						
						brWriterCripto.write(lineaCripto);
						brWriterCripto.newLine();
						brWriterCripto.flush();
					}
				}

				else if (res.equals("2")) {
					
					brWriterCripto.write(lineaCripto);
					brWriterCripto.newLine();
					brWriterCripto.flush();
					
					brWriterMercado.write(lineaMercado);
					brWriterMercado.newLine();
					brWriterMercado.flush();
				}

				else {
					
					brWriterCripto.write(lineaCripto);
					brWriterCripto.newLine();
					brWriterCripto.flush();
				}
			}

			if (Existencia == false) {

				System.out.println("La criptomoneda a actualizar no existe. ");
				
				brFileCripto.close();
				brFileMercado.close();
				brWriterMercado.close();
				brWriterCripto.close();
				
				archCriptoCSVTemp.delete();
				archMercadoCSVTemp.delete();
				
				return Existencia;
			}

			if (res.equals("2")) {
				
				brWriterMercado.close();
				brFileMercado.close();
				
				archMercadoCSV.delete();
				archMercadoCSVTemp.renameTo(archMercadoCSV);
			}
			else {
				
				brWriterMercado.close();
				brFileMercado.close();
				archMercadoCSVTemp.delete();
			}
				

			brWriterCripto.close();
			brFileCripto.close();
			
			archCriptoCSV.delete();
			archCriptoCSVTemp.renameTo(archCriptoCSV);
		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return Existencia;
	}

	public boolean EliminarCripto() {
	
		// Elimino una cripto de criptomonedas pero tambien impacto la baja en el mercado.
		// Se escribira nuevamente el archivo sin el registro a eliminar.
		
		File archCriptoCSV = new File("src/Archivos/Criptomonedas.csv");
		File archCriptoCSVTemp = new File("src/Archivos/CriptomonedasTemp.csv");
		File archMercadoCSV = new File("src/Archivos/Mercado.csv");
		File archMercadoCSVTemp = new File("src/Archivos/MercadoTemp.csv");
		BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
		String NombreCripto;
		String lineaCripto = null;
		String lineaMercado = null;
		Boolean existenciaReg = false;
		
		try
		{
			BufferedReader brFileCripto = new BufferedReader(new FileReader(archCriptoCSV)); // Lectura archivo.
			BufferedReader brFileMercado = new BufferedReader(new FileReader(archMercadoCSV));
			BufferedWriter brWriterCripto = new BufferedWriter(new FileWriter(archCriptoCSVTemp));
			BufferedWriter brWriterMercado = new BufferedWriter(new FileWriter(archMercadoCSVTemp));
			
			String[] lineaParseo = new String[10];
			
			
			System.out.println("Ingrese el nombre de la criptomoneda que desea eliminar: ");
			NombreCripto = brInput.readLine(); // Cripto que queremos eliminar.
			
			while(((lineaCripto = brFileCripto.readLine()) != null) && ((lineaMercado = brFileMercado.readLine()) != null)) {
				
				// Leo todo el archivo, y escribo todos los registros excepto el que quiero eliminar.
				
				lineaParseo = lineaCripto.split(";");
				
				if(NombreCripto.equals(lineaParseo[0])) {
					
					System.out.println("Criptomoneda: " + NombreCripto + " eliminada.");
					existenciaReg = true;
				}
			
				else {
					
					brWriterCripto.write(lineaCripto);
					brWriterCripto.newLine();
					brWriterCripto.flush();
					
					brWriterMercado.write(lineaMercado);
					brWriterMercado.newLine();
					brWriterMercado.flush();
				}	
			}
			
			if(existenciaReg == false) {
				System.out.println("La criptomoneda a eliminar no existe. ");
				
				brFileCripto.close();
				brFileMercado.close();
				brWriterCripto.close();
				brWriterMercado.close();
				
				archCriptoCSVTemp.delete();
				archMercadoCSVTemp.delete();
				
				return existenciaReg;
			}
						
			brWriterCripto.close();
			brWriterMercado.close();
			brFileCripto.close();
			brFileMercado.close();
		
			archCriptoCSV.delete();
			archCriptoCSVTemp.renameTo(archCriptoCSV);
			
			archMercadoCSV.delete();
			archMercadoCSVTemp.renameTo(archMercadoCSV);
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return existenciaReg;
		
	}

	@Override
	public String toString() {
		return "Administrador [Nombre=" + super.nombre + ", " + "perfil=" + perfil + "]";
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}

