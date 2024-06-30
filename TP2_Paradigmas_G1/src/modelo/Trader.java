package modelo;

import java.util.ArrayList;
import java.util.Scanner;

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
	//consultar, ver mercado actual, recomendar, comprar y vender (también en formato de menú).
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
	 *
	 * @throws Exception Descripción de la excepción lanzada.
	 */
	public void comprar() {
		try {
			
		Mercado mercado = new Mercado("BTC", 1.38, 34.66, 13.84);
		Scanner scanner = new Scanner(System.in);
		
		//Para la sección de compras, se debe seleccionar el símbolo de la criptomoneda
        System.out.print("Ingrese la criptomoneda: ");
        String simbolo = scanner.nextLine();
        System.out.println("El simbolo ingresado es: " + simbolo);
        
        //El total a comprar, el sistema debe exhibir el valor en dólares de esta y el total que se puede comprar (capacidad).
        Criptomoneda cripto = new Criptomoneda("Bitcoin", "BTC", 70124.4); //Deberia buscar esta cripto
        
        //System.out.println(cripto.toString() + "\nCapacidad disponible: " + 1.38 * sats);//Y deberia mostrar los datos de la cripto
        
        System.out.print("Ingrese la cantidad de \"sats\" que desea comprar (0 para salir): ");
        int cantidad = scanner.nextInt();
        
        //Verif. de compra 
        if(!confirmarCompra(cantidad, 70124.4)) 
        {
        	System.out.println("La compra se canceló con exito.");
        	return;
        }
        
        //Actualizo el precio
        this.saldoActual -= cantidad * 70124.4 / sats;
        
        //En caso de que el usuario confirme la compra, la capacidad se debe restar en
        //el archivo mercados.csv y aumentar un 5% los parámetros volúmen en las
        //últimas 24 horas y variación en los últimos 7 días del mismo archivo.
        Double capacidad = 1.38 - (cantidad / sats);
		double volumen24hs = 0.3466 + 0.05;
		double variacion7d = 0.1384 + 0.05;
		
		mercado = new Mercado(simbolo, capacidad, volumen24hs, variacion7d);
        
        //Si la cantidad de compras de una criptomoneda supera las 1000 entonces se debe
        //aumentar su precio en dólares un 10%.
        cripto = new Criptomoneda("Bitcoin", simbolo, 70124.4 * 1.1);
        
        System.out.println("Mercado:\n" + mercado.toString());
        System.out.println("Criptomoneda:\n" + cripto.toString());
        
		//○ El proceso para confirmar la compra es el siguiente: El sistema debe
		//verificar que el usuario disponga de dinero en su cuenta bancaria, en caso
		//contrario rechaza la operación y le solicitará al usuario ingresar el dinero
		//faltante. Si la compra es satisfactoria, el sistema debe actualizar el saldo del
		//usuario (disminuir el saldo) y almacenar todas las criptomonedas compradas
		//hasta el momento por el usuario (a modo de archivo histórico) con el siguiente
		//diseño: Símbolo de la criptomoneda y la cantidad comprada.
		//En el caso de que exista la criptomoneda en el archivo, solo se debe actualizar
		//la cantidad (acumulando la anterior con la actual). El nombre de dicho archivo
		//debe ser NombreDeUsario_historico.csv
		//Por último se debe actualizar el saldo del usuario en su cuenta bancaria  	
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
	}
	
	
}
