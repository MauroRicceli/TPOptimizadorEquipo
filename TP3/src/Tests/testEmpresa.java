package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Auxiliares.Empleado;
import Auxiliares.Empresa;

public class testEmpresa {
	private Empresa _empresaTest;
	
	@Before
	public void Before() {
		_empresaTest = new Empresa();
	}
	
	@Test
	public void agregarEmpleadoValido() {
		Empleado empleado = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		_empresaTest.agregarEmpleado(empleado);
		
		assertEquals(_empresaTest.getEmpleadosPosibles().size(), 1);
	}
	
	@Test
	public void agregarEmpleadoRepetido() {
		Empleado emp1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado emp2 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		
		_empresaTest.agregarEmpleado(emp1);
		_empresaTest.agregarEmpleado(emp2);
		
		assertEquals(_empresaTest.getEmpleadosPosibles().size(), 1);
	}
	
	@Test
	public void agregarEnemistad() {
		Empleado emp1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado emp2 = new Empleado("Jose Manuel", 2, "Tester");
		
		_empresaTest.agregarEnemistad(emp1, emp2);
		assertEquals(_empresaTest.getEmpleadosEnemistados().size(), 1);
	}
	
	@Test
	public void existeEnemistadTrue() {
		Empleado emp1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado emp2 = new Empleado("Jose Manuel", 2, "Tester");
		
		_empresaTest.agregarEnemistad(emp1, emp2);
		assertTrue(_empresaTest.existeEnemistad(emp2, emp1));
	}
	
	@Test
	public void existeEnemistadFalse() {
		Empleado emp1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado emp2 = new Empleado("Jose Manuel", 2, "Tester");
		Empleado emp3 = new Empleado("Laura Jimenez", 5, "Programador");
		
		_empresaTest.agregarEnemistad(emp1, emp2);
		assertFalse(_empresaTest.existeEnemistad(emp2, emp3));
	}

}
