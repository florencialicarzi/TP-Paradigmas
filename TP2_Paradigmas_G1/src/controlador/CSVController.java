package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVController {

	public static  List<String[]> importarCSVGenerico(String archivoCSV){
		List<String[]> registrosCSV = new ArrayList<>();
		
        try (BufferedReader buffer = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            
			if((linea = buffer.readLine())== null)
				return null;

            while ((linea = buffer.readLine()) != null) {
                	String[] val = linea.split(";"); 
                	registrosCSV.add(val);
                }              
        } 
        catch (IOException e) {
        	System.err.println("Error al leer el archivo: " + archivoCSV);
            e.printStackTrace();
        }

        return registrosCSV;
	}
	
	public static String[] buscarRegistroCSV( String archivoCSV, String buscado, int nroCampo) {
		try (BufferedReader buffer = new BufferedReader(new FileReader(archivoCSV))) {
			String linea;
			
			while ((linea = buffer.readLine()) != null) {
				String[] val = linea.split(";"); 
				if(val[nroCampo].equals(buscado)) {
					return val;
				}
			}
		}
		catch (IOException e) {
        	System.err.println("Error al leer el archivo: " + archivoCSV);
            e.printStackTrace();
        }
		return null;
	}
	
	public static void escribirAlFinalArchivo(String archivoCSV, String nuevoRegistro) {
		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(archivoCSV, true))) {
            buffer.write(nuevoRegistro);
            buffer.flush();
            System.out.println("Registro agregado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + archivoCSV);
            e.printStackTrace();
        }
	}
	
}