package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Auxiliares.Quartet;


public class testQuartet {

	@Test
	public void quartetCrear() {
		Quartet<Integer, String, Double, String> aux = new Quartet<Integer, String, Double, String>(3, "es mayor que", 1.5, "true" );
	}
	
	@Test
	public void quartetEqualsTrue() {
		Quartet<Integer, String, Double, String> aux1 = new Quartet<Integer, String, Double, String>(3, "es mayor que", 1.5, "true" );
		Quartet<Integer, String, Double, String> aux2 = new Quartet<Integer, String, Double, String>(3, "es mayor que", 1.5, "true" );
		
		assertEquals(aux1, aux2);
	}
	
	@Test
	public void quartetEqualsFalse() {
		Quartet<Integer, String, Double, String> aux1 = new Quartet<Integer, String, Double, String>(3, "es mayor que", 1.5, "true" );
		Quartet<String, String, Double, String> aux2 = new Quartet<String, String, Double, String>("3", "es mayor que", 1.5, "true" );
		
		assertNotEquals(aux1, aux2);
	}

}
