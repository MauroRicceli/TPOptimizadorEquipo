package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Auxiliares.Tupla;

@SuppressWarnings("unused")
public class testTupla {
	
	@Test
	public void tuplaCrear() {
		Tupla<Integer, String> aux = new Tupla<Integer, String>(1, "Hola");
	}
	
	@Test
	public void tuplaIgualTrue() {
		Tupla<Integer, String> aux = new Tupla<Integer, String>(1, "Hola");
		Tupla<Integer, String> aux2 = new Tupla<Integer, String>(1, "Hola");
		assertEquals(aux, aux2);
	}
	
	@Test
	public void tuplaIgualFalse() {
		Tupla<Integer, String> aux = new Tupla<Integer, String>(1, "Hola");
		Tupla<Integer, String> aux2 = new Tupla<Integer, String>(1, "Chau");
		assertNotEquals(aux, aux2);
	}
	
	@Test
	public void tuplaRevertidaTrue() {
		Tupla<Integer, String> aux = new Tupla<Integer, String>(1, "Hola");
		Tupla<String, Integer> aux2 = new Tupla<String, Integer>("Hola", 1);
		
		assertEquals(aux.tuplaRevertida(), aux2);
	}
	
	@Test
	public void tuplaRevertidaFalse() {
		Tupla<Integer, String> aux = new Tupla<Integer, String>(1, "Hola");
		
		assertNotEquals(aux.tuplaRevertida(), aux);
	}
}
