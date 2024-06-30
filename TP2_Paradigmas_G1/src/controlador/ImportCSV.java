package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportCSV {

	public static  List<String[]> importarCSVGenerico(String archivoCSV){
		List<String[]> registrosCSV = new ArrayList<>();
		
        try (BufferedReader buffer = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = buffer.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false; 
                }
                else {
                	String[] val = linea.split(";"); 
                	registrosCSV.add(val);
                }
                
            }
        } 
        catch (IOException e) {
        	System.err.println("Error al leer el archivo: " + archivoCSV);
            e.printStackTrace();
        }

        return registrosCSV;
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