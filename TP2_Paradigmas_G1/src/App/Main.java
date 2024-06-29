package App;

import Usuario.Usuario;

public class Main {
    public static void main(String[] args) {
        String archivoCSVMercado = "C:/Users/Florencia/Desktop/TP_Paradigmas/Archivos/Mercado.csv";
        String archivoCSVCriptomoneda = "C:/Users/Florencia/Desktop/TP_Paradigmas/Archivos/Criptomonedas.csv";
        String archivoCSVUsuarios = "C:/Users/Florencia/Desktop/TP_Paradigmas/Archivos/Usuarios.csv";
        
        Registro.ImportCSVMercado(archivoCSVMercado);
        Registro.mostrarEstadoActualMercado();
        
        Registro.ImportCSVCriptomoneda(archivoCSVCriptomoneda);
        Registro.mostrarEstadoCriptomonedas();
        
        Registro.ImportCSVUsuarios(archivoCSVUsuarios);
        Registro.mostrarAdmins();
        Registro.mostrarTraders();
        
        Usuario.ConsultarCriptomoneda("BTC");

    }

}