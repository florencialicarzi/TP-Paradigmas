package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.List;

import controlador.CriptoMercadoController;
import controlador.TraderController;
import vista.ConsoleVista;
import vista.TraderVista;

public class Trader extends Usuario {
	private Integer cuentaBancaria;
	private String nombreBanco;
	private double saldoActual;

	public Trader(String nombre, Integer cuentaBancaria, String nombreBanco, double saldoActual) {
		super(nombre);
		this.cuentaBancaria = cuentaBancaria;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}

	public void Comprar() {
		// DECLARACION VARIABLES
		Criptomoneda cripto;
		Mercado mercado;
		String simbolo;
		double cantidad;
		int aumento;

		// SELECCION MONEDA
		simbolo = ConsoleVista.obtenerSimbolo();

		// BUSQUEDA DE CRIPTOMONEDA Y MERCADO
		cripto = Criptomoneda.buscarCripto(simbolo);
		mercado = Mercado.buscarMercado(simbolo);

		// SELECCION CANTIDAD
		cantidad = TraderVista.obtenerCantidad();

		// VALIDACION COMPRA
		if (!confirmarCompra(cantidad, cripto.getValorUSD())) {
			System.out.println("La compra se cancelo con exito.");
			return;
		}

		// ACA TIENE QUE ACTUALIZAR EL ARCHIVO | NO HACE UNA
		// MIERDA!!!!!!!!!!!!!!!!!!!!!!!!!

		// ACTUALIZAR USUARIO
		this.saldoActual -= cantidad * cripto.getValorUSD();

		// REGISTRAR HISTORICO
		historico(simbolo, cantidad);

		// ACTUALIZACION LOG-COMPRAS
		aumento = cripto.cargarComprasCripto(cantidad);

		// ACTUALIZACION CRIPTO
		if (aumento != 0)
			cripto.aumentoPrecio(aumento);

		// ACTUALIZACION DE MERCADO
		mercado.actualizarMercadoCompra(cantidad, 0.05);

		// ACTUALIZACION DE ARCHIVOS
		actualizarRegTrader();
		cripto.actualizarRegCripto();
		mercado.actualizarRegMercado();
	}

	private boolean confirmarCompra(double cantidad, double precio) {
		if (cantidad == 0)
			return false;
		if (cantidad < 0)
			throw new RuntimeException("La cantidad no puede ser negativa.");
		if (this.saldoActual < cantidad * precio)
			throw new RuntimeException("El saldo no es suficiente.");
		return true;
	}

	private boolean confirmarVenta(double cantidadVenta, double cantidadDisponible) {
		if (cantidadVenta == 0)
			return false;
		if (cantidadVenta < 0)
			throw new RuntimeException("La cantidad no puede ser negativa.");
		if (cantidadDisponible <= 0)
			throw new RuntimeException("No hay criptomonedas disponibles.");
		return true;
	}

	public void historico(String simbolo, double cantidad) {

		String historico;
		String temp = "src/Archivos/temp.csv";
		String archHistoricoCSV = "src/Archivos/" + super.nombre + "_Historico.csv";

		File inputFile = new File(archHistoricoCSV);
		File tempFile = new File(temp);

		double total = cantidad;

		try {
			if (!inputFile.exists())
				inputFile.createNewFile();
		} catch (Exception e) {
			throw new RuntimeException("El archivo no existia y no se pudo crear.");
		}

		try (BufferedReader br = new BufferedReader(new FileReader(archHistoricoCSV));
				BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {

			if ((historico = br.readLine()) == null)
				historico = "Simbolo;Cantidad";
			bw.write(historico);
			bw.newLine();

			while ((historico = br.readLine()) != null) {

				if (simbolo.equals(historico.substring(0, historico.indexOf(";")))) {
					total = Double.parseDouble(historico.substring(historico.indexOf(";") + 1)) + cantidad;
					historico = simbolo + ";" + total;
					cantidad = 0;
				}
				bw.write(historico);
				bw.newLine();
			}

			if (cantidad != 0) {
				bw.write(simbolo + ";" + cantidad);
				bw.newLine();
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		// ELIMINAR ANTIGUO
		if (!inputFile.delete()) {
			throw new RuntimeException("No se pudo eliminar el archivo original");
		}

		// RENOMBRAR TEMP
		if (!tempFile.renameTo(inputFile)) {
			throw new RuntimeException("No se pudo renombrar el archivo temporal");
		}
	}

	public void Vender() {

		String simbolo;
		Mercado mercado;
		Historico historico;
		Criptomoneda cripto;

		double cantidad;
		int aumento;

		// SELECCION MONEDA
		simbolo = ConsoleVista.obtenerSimbolo();

		// BUSQUEDA DE CRIPTOMONEDA, MERCADO E HISTORICO
		cripto = Criptomoneda.buscarCripto(simbolo);
		mercado = Mercado.buscarMercado(simbolo);
		historico = buscarHistorico(simbolo);


		// SELECCION CANTIDAD
		cantidad = TraderVista.obtenerDouble(simbolo + " disponibles para la venta: " + historico.getCant()
		+ "\nIngrese la cantidad que desea vender:");

		// VALIDACION VENTA
		if (!confirmarVenta(cantidad, historico.getCant())) {
			System.out.println("La compra se cancelo con exito.");
			return;
		}

		// ACA TIENE QUE ACTUALIZAR EL ARCHIVO | NO HACE UNA
		// MIERDA!!!!!!!!!!!!!!!!!!!!!!!!!

		// ACTUALIZAR USUARIO
		this.saldoActual += cantidad * cripto.getValorUSD();

		// REGISTRAR HISTORICO
		historico(simbolo, -cantidad);

		// ACTUALIZACION LOG-COMPRAS
		aumento = cripto.cargarComprasCripto(-cantidad);

		// ACTUALIZACION DE CRIPTOMONEDA
		if (aumento != 0)
			cripto.aumentoPrecio(aumento);

		// ACTUALIZACION DE MERCADO
		mercado.actualizarMercadoCompra(-cantidad, -0.07);

		// ACTUALIZACION DE ARCHIVOS
		actualizarRegTrader();
		cripto.actualizarRegCripto();
		mercado.actualizarRegMercado();
	}

	public Historico buscarCriptoHistorico(String simbolo) {
		return null;
	}

	@Override
	public String toString() {
		return "Trader [Nombre=" + super.nombre + ", " + "cuentaBancaria=" + cuentaBancaria + ", " + ", nombreBanco="
				+ nombreBanco + ", " + ", saldoActual=" + saldoActual + "]";
	}

	public void Recomendar() {

		CriptoMercadoController.inicializarListas();
		List<Criptomoneda> registrosCriptomoneda = CriptoMercadoController.getRegistrosCriptomoneda();
		List<Mercado> registrosMercado = CriptoMercadoController.getRegistrosMercado();

		String simboloRecomendado = "";
		double porcentajeRecomendado = 0;

		if (registrosCriptomoneda.size() != registrosMercado.size()) {
			throw new IllegalStateException("Las listas no tienen la misma longitud.");
		}

		for (int i = 0; i < registrosCriptomoneda.size(); i++) {
			Criptomoneda criptomoneda = registrosCriptomoneda.get(i);
			Mercado mercado = registrosMercado.get(i);

			double calculo = (mercado.getCapacidad() / criptomoneda.getValorUSD()) * 100;

			if (calculo > porcentajeRecomendado) {
				porcentajeRecomendado = calculo;
				simboloRecomendado = criptomoneda.getSimbolo();
			}
		}

		TraderVista.mostrarMensaje("La criptomoneda recomendada es " + simboloRecomendado + " con un porcentaje de "
				+ porcentajeRecomendado + "%");
		ConsoleVista.pause();
	}

	public void verHistorico() {
		List<Historico> registrosHistorico = TraderController
				.getRegistrosHistoricos("src/Archivos/" + super.nombre + "_Historico.csv");

		int opcion = TraderVista.obtenerInt("1) Orden alfabetico\n2)Orden por saldo");
		if (opcion == 1) {
			registrosHistorico.sort(Comparator.comparing(Historico::getSimbolo));
		} else if (opcion == 2) {
			registrosHistorico.sort(Comparator.comparingDouble(Historico::getCant).reversed());
		} else {
			TraderVista.mostrarMensaje("Opcion no valida");
			return;
		}

		registrosHistorico.forEach(System.out::println);
		registrosHistorico.clear();
		ConsoleVista.pause();
	}

	public Historico buscarHistorico(String simbolo) {

		String historico;
		String archCriptoCSV = "src/Archivos/" + super.nombre + "_Historico.csv";

		File inputFile = new File(archCriptoCSV);

		if (inputFile.exists())
			try (BufferedReader br = new BufferedReader(new FileReader(archCriptoCSV))) {
				while ((historico = br.readLine()) != null) {
					if (simbolo.equals(historico.substring(0, historico.indexOf(";"))))
						return new Historico(historico);
				}
			} catch (Exception e) {
				throw new RuntimeException("Error al leer el archivo");
			}
		return null;
	}

	public boolean actualizarRegTrader() {

		String trader;

		File archTempCSV = new File("src/Archivos/UsuariosTemp.csv");
		File archUsuarioCSV = new File("src/Archivos/Usuarios.csv");

		try (BufferedReader br = new BufferedReader(new FileReader(archUsuarioCSV));
				BufferedWriter bw = new BufferedWriter(new FileWriter(archTempCSV))) {

			while ((trader = br.readLine()) != null) {
				if (super.nombre.equals(trader.substring(0, trader.indexOf(";"))))
					trader = super.nombre + ";" + this.cuentaBancaria + ";" + this.nombreBanco + ";" + this.saldoActual;
				bw.write(trader);
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo");
		}

		// ELIMINAR ANTIGUO
		if (!archUsuarioCSV.delete()) {
			throw new RuntimeException("No se pudo eliminar el archivo original");
		}

		// RENOMBRAR TEMP
		if (!archTempCSV.renameTo(archUsuarioCSV)) {
			throw new RuntimeException("No se pudo renombrar el archivo temporal");
		}

		return true;
	}

}
