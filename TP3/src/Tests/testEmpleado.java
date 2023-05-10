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
	public void empleadosIguales() {
		Empleado empleado1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado empleado2 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		
		assertEquals(empleado1, empleado2);
	}
	
	

}
