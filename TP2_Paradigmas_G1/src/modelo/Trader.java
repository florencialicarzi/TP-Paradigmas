package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.ImportCSV;

public class Trader extends Usuario {
	private Integer cuentaBancaria;
	private String nombreBanco;
	private double saldoActual;
	private final int sats = 100000000;
	private ArrayList<Criptomoneda> billetera = new ArrayList<Criptomoneda>();
	
	public Trader(String nombre, Integer cuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.cuentaBancaria = cuentaBancaria;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}
 
	
	public void vender() {
        /*
        Para el caso de la venta, se debe seleccionar el símbolo de la criptomoneda y el
        sistema debe exhibir la cantidad máxima que puede vender. Dicha cantidad se debe
        obtener del archivo histórico del usuario. Luego, el usuario debe ingresar la cantidad
        a vender, si dicha cantidad es superior a la indicada se debe emitir un mensaje de
        error y continuar con la ejecución del menú.
        ○ En el caso de que se confirme la venta, se debe aumentar la capacidad en el
        archivo de mercados.csv de dicha criptomoneda y disminuir un 7% el valor
        los parámetros volúmen en las últimas 24 horas y variación en los últimos 7
        días. Luego de realizada la operación se debe actualizar el archivo histórico
        del usuario modificando la cantidad (disminuir la cantidad) y actualizar el saldo
        del usuario. 
        */
		Scanner scanner = new Scanner(System.in);
		String simbolo = scanner.nextLine();
		
		String archivoCSVHistorico = "C:/Users/Florencia/Desktop/TP_Paradigmas/Archivos/flicarzi_historico.csv";        
		List<String[]> registrosCSV = ImportCSV.importarCSVGenerico(archivoCSVHistorico);
    }
	
	@Override
	public String toString() {
		return "Trader [Nombre="+ super.nombre+ ", " + "cuentaBancaria=" + cuentaBancaria+ ", " + ", nombreBanco=" + nombreBanco+ ", " + ", saldoActual="
				+ saldoActual + "]";
	}
	
	//Cripto
	public void consultar() {
		/*
			El sistema le ofrecerá al usuario consultar/visualizar el archivo histórico de sus
			transacciones (Estado actual de las criptomonedas compradas y vendidas) ordenado
			alfabéticamente por símbolo o por cantidad en modo descendente.
		*/
	}
	
	//Mercado
	public void verMercadoActual() {
		
	}
	
	/**
	 * Muestra en pantalla la criptomoneda más recomendada.
	 *
	 * @throws Exception Error de conexion con el sistema
	 * @throws RuntimeException No hay criptomonedas cargadas en el sistema
	 */
	public void recomendar() {
		/*
			En el caso de que el usuario requiera una recomendación de compra por parte del
			sistema, el mismo realizará una evaluación estadística entre la criptomoneda de
			mayor cotización y la cantidad disponible en el archivo de mercados.csv
			El cálculo que se debe realizar es:
			(Cantidad disponible de la criptomoneda de mayor valor / precio en dólares de la criptomoneda) *
			100
			El sistema recomendará aquella criptodivisa de mayor porcentaje, tomando como
			referencia la del archivo criptomonedas.csv
		*/
	}
	
	/**
	 * Para la compra de criptomonedas:
	 * <li>Muestra en pantalla la criptomoneda seleccionada y espera la confirmación de la compra.</li>
	 * <li>Si la compra de una criptomoneda supera las 1000, entonces su valor aumenta en un 10%.</li>
	 * <li>Genera/Actualiza historicos</li>
	 *
	 * @throws Exception Descripción de la excepción lanzada.
	 */
	public void comprar() {
		try (Scanner scanner = new Scanner(System.in)){
			
			//DECLARACION VARIABLES
			Criptomoneda cripto;
			Mercado mercado;
			String simbolo;
			int cantidad;
			
			//SELECCION MONEDA
	        System.out.print("Ingrese la criptomoneda: ");
	        simbolo = scanner.nextLine();
	        
	        //BUSQUEDA DE CRIPTOMONEDA Y MERCADO
	        cripto = Criptomoneda.buscarCripto(simbolo);
	        mercado = Mercado.buscarMercado(simbolo);

	        //SELECCION CANTIDAD
	        System.out.print("Ingrese la cantidad de \"sats\" que desea comprar (0 para salir): ");
	        cantidad = scanner.nextInt();
	        scanner.nextLine();
	        
	        //VALIDACION COMPRA
	        if(!confirmarCompra(cantidad, cripto.getValorUSD()))
	        {
	        	System.out.println("La compra se canceló con exito.");
	        	return;
	        }
	        
	        //ACA TIENE QUE ACTUALIZAR EL ARCHIVO | NO HACE UNA MIERDA!!!!!!!!!!!!!!!!!!!!!!!!!
	        
	        //ACTUALIZAR USUARIO
	        this.saldoActual -= cantidad * cripto.getValorUSD() / sats;
	        
	        //HISTORICO
	        historico(simbolo, cantidad);

	        //ACTUALIZACION CRIPTO
	        if(Criptomoneda.obtenerTotalCripto(simbolo) > 999)
	        	cripto.incValorDiezPorciento();
	        
	        //ACTUALIZACION DE MERCADO
	        mercado.actualizarMercadoCompra(cantidad);
	        
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean confirmarCompra(int cantidad, double precio) {
		if(cantidad == 0)
			return false;
		if(cantidad < 0)
			throw new RuntimeException("La cantidad no puede ser negativa.");
		if(this.saldoActual < cantidad * precio / sats)
			throw new RuntimeException("El saldo no es suficiente.");
		return true;
	}
	
	public void historico(String simbolo, int cantidad) {

		String historico;
        String temp = "src/Archivos/temp.csv";
        String archHistoricoCSV = "src/Archivos/" + super.nombre + "_Historico.csv";

        File inputFile = new File(archHistoricoCSV);
        File tempFile = new File(temp);
        
        int total = cantidad;
        
        try { 
        	if (!inputFile.exists())
        		inputFile.createNewFile(); 
        }
        catch (Exception e) { 
        	throw new RuntimeException("El archivo no existia y no se pudo crear."); 
        }
        

        try (BufferedReader br = new BufferedReader(new FileReader(archHistoricoCSV));
             BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            
            if((historico = br.readLine()) == null)
            	 historico = "Simbolo;Cantidad";
            bw.write(historico);
            bw.newLine();
            
            while ((historico = br.readLine()) != null) {
            	
            	if (simbolo.equals(historico.substring(0, historico.indexOf(";")))){
            		total = Integer.parseInt(historico.substring(historico.indexOf(";") + 1)) + cantidad;
            		historico = simbolo + ";" + total;
            		cantidad = 0;
            	}
            	bw.write(historico);
                bw.newLine();
            }
            
            if(cantidad != 0) {
            	bw.write(simbolo + ";" + cantidad);
            	bw.newLine();
            }

        }
		catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        //ELIMINAR ANTIGUO
        if (!inputFile.delete()) {
            throw new RuntimeException("No se pudo eliminar el archivo original");
        }

        //RENOMBRAR TEMP
        if (!tempFile.renameTo(inputFile)) {
        	throw new RuntimeException("No se pudo renombrar el archivo temporal");
        }
	}

	
	private void buscarMercado() {
		
	}
}
