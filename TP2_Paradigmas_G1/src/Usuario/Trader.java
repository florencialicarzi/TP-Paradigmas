package Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import Mercado.Criptomoneda;

public class Trader extends Usuario{
	private Integer cuentaBancaria;
	private String nombreBanco;
	private double saldoActual;
	private ArrayList<Criptomoneda> billetera = new ArrayList<Criptomoneda>();
	ArrayList<Transaccion> historico = new ArrayList<Transaccion>();
	
	public Trader(String nombre, Integer cuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.cuentaBancaria = cuentaBancaria;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}

	@Override
	public String toString() {
		return "Trader [Nombre="+ super.nombre+ ", " + "cuentaBancaria=" + cuentaBancaria+ ", " + ", nombreBanco=" + nombreBanco+ ", " + ", saldoActual="
				+ saldoActual + "]";
	}
	

	
	public Trader(String nombre, int cuentaBancaria, String nombreBanco, double saldo)
	{
		super(nombre);
		this.setCuentaBancaria(cuentaBancaria);
		this.setNombreBanco(nombreBanco);
		this.setSaldoActual(saldo);
		
		System.out.println("El trader \"" + nombre + "\" fue creado con exito.");
	}
	
	public void inicio() {
        try{
        	System.out.println("Inicio de sesion\n-----------------------");
        	BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        	System.out.print("Username: ");
			String username = bufferRead.readLine();
			
			System.out.println(username);
			
			if(username == null)
				return;
			
			System.out.println("Registro de cuenta\n-----------------------");
			System.out.print("Ingrese el nombre: ");
			String nombre = bufferRead.readLine();
			System.out.print("Ingrese el numero de cuenta bancaria: ");
			String cuentaBancaria = bufferRead.readLine();
			System.out.print("Ingrese el nombre del banco: ");
			String nombreBanco = bufferRead.readLine();
			System.out.print("Ingrese el saldo actual: ");
			String saldoActual = bufferRead.readLine();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(int cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public ArrayList<Criptomoneda> getBilletera() {
		return billetera;
	}

	public void setBilletera(ArrayList<Criptomoneda> billetera) {
		this.billetera = billetera;
	}
}
/*
El otro usuario es un trader que contiene como atributos el nombre,,
el número de cuenta bancaria, el nombre del Banco y el saldo actual (Considerar siempre
positivo). Dichos usuarios se encuentran en el archivo usuarios.csv

Un posible formato podría ser:
hrizo24 , 7824621 ,Banco Río , 78000.00
mlopez , 5821621, Banco Francés , 800000.00

En el caso de que el trader no se encuentre registrado
en el sistema, deberá registrarse ingresando su nombre de usuario y los datos de su cuenta
bancaria asociada: Número de cuenta, Nombre del banco y el saldo. 

Menú de opciones
-----------------------
1) Crear Criptomoneda
2) Modificar Criptomoneda
3) Eliminar Criptomoneda
4) Consultar Criptomoneda
5) Consultar estado actual del mercado
6) Salir
Ingrese su opción (1 - 6): _

*/