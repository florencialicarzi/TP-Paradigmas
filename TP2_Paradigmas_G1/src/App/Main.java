package App;

public class Main {
    public static void main(String[] args) {
        String archivoCSVMercado = "C:/Users/Florencia/Desktop/TP_Paradigmas/Archivos/Mercado.csv";
        String archivoCSVCriptomoneda = "C:/Users/Florencia/Desktop/TP_Paradigmas/Archivos/Criptomonedas.csv";
        
        Registro.ImportCSVMercado(archivoCSVMercado);
        Registro.mostrarEstadoMercado();
        
        Registro.ImportCSVCriptomoneda(archivoCSVCriptomoneda);
        Registro.mostrarEstadoCriptomoneda();
    }

}