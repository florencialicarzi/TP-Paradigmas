package modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TraderTests {

	Trader trader;
	
	@BeforeEach
	public void setup()
	{
		trader = new Trader("flicarzi", 546825, "Supervielle", 1000000);
	}
	
	@Test
	void test() {
		trader.comprar();
		assertEquals(1, 1);
	}

}


/*
@Test
void testabrirSinFallos(){
	//Arrange
	//Act
	c.abrir(1);
	c.abrir(1); //Re-intento de apertura
	//Assert
	assertTrue(c.estaAbierta()); //Abre la puerta
	assertEquals(c.contarAperturasExitosas(), 1);
	assertEquals(c.contarAperturasFallidas(), 0);
}*/