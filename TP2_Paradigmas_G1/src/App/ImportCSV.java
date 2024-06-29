package App;

import java.io.BufferedReader;
import java.io.FileReader;
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
            System.out.println("Excepcion!");
        }

        return registrosCSV;
	}
	
}