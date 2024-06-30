package modelo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Mercado {
	

	private String simbolo;
    private double capacidad;
    private double volumen24hs;
    private double variacion7d;
    private final int sats = 100000000;

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
    
    public void actualizarMercadoCompra(int cantidad) {
    	this.capacidad -= (cantidad / sats);
    	this.volumen24hs += 0.05;
    	this.variacion7d += 0.05;
    }
    
    public static Mercado buscarMercado(String simbolo) {

		String mercado;
		String archMercadoCSV = "Archivos/Mercado.csv";
		InputStream is = Criptomoneda.class.getClassLoader().getResourceAsStream(archMercadoCSV);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is)))
		{
			while ((mercado = br.readLine()) != null)
				if(simbolo.equals(mercado.substring(0, mercado.indexOf(";"))))
					return new Mercado(mercado);
		}
		catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}
		return null;
	}
    
    public String getSimbolo() {
		return simbolo;
	}



	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}



	@Override
    public String toString() {
        return "Datos del mercado:\n" +
                "Capacidad:" + capacidad +
                "		Volumen en las ultimas 24 horas:" + volumen24hs*100 + "%"+
                "		Variacion en los ultimos 7 dias:" + variacion7d*100 + "%"+
                ' ';
    }
}
