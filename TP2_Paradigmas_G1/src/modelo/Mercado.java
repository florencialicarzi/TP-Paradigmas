package modelo;

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
    
    
    
    public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}


	@Override
    public String toString() {
        return  simbolo +
                ">>> Capacidad:" + capacidad +
                "		Volumen en las ultimas 24 horas:" + volumen24hs*100 + "%"+
                "		Variacion en los ultimos 7 dias:" + variacion7d*100 + "%"+
                ' ';
    }
}
