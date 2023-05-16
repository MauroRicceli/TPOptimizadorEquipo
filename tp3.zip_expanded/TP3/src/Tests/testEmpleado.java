package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Auxiliares.Empleado;

@SuppressWarnings("unused")
public class testEmpleado {
	
	@Test
	public void crearEmpleado() {
		Empleado empleado = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void crearEmpleadoFalloNombre() {
		Empleado empleado = new Empleado("As", 5, "Arquitecto");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void crearEmpleadoFalloCalificacion() {
		Empleado empleado = new Empleado("Manuel Rodriguez", 6, "Arquitecto");
	}
	
	@Test
	public void empleadosIgualesTrue() {
		Empleado empleado1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado empleado2 = new Empleado("manuel roDRiguez", 5, "arqUIteCto");
		
		assertEquals(empleado1, empleado2);
	}
	
	@Test
	public void empleadosIgualesFalse() {
		Empleado empleado1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado empleado2 = new Empleado("Roman Riquelme", 5, "Arquitecto");
		
		assertNotEquals(empleado1, empleado2);
	}
	
	@Test
	public void hashCodeIgualesTrue() {
		Empleado empleado1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado empleado2 = new Empleado("manuel roDRiguez", 5, "arqUIteCto");
		
		assertEquals(empleado1.hashCode(), empleado2.hashCode());
	}
	
	@Test
	public void hashCodeIgualesFalse() {
		Empleado empleado1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado empleado2 = new Empleado("Roman Riquelme", 5, "Arquitecto");
		
		assertNotEquals(empleado1.hashCode(), empleado2.hashCode());
	}
	
	

}
