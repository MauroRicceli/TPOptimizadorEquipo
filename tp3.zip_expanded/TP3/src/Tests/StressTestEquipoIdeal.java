package Tests;

import java.util.ArrayList;
import java.util.Random;

import Auxiliares.Empleado;
import Auxiliares.Empresa;
import Modelo.equipoIdeal;

public class StressTestEquipoIdeal {
	private String[] _roles = {"Jefe de Equipo", "Arquitecto", "Programador", "Tester"};
	
	public StressTestEquipoIdeal() {
	}
	
	
	public void stressTest() {
		for(int n=1; n<150; ++n) {
			long inicio = System.currentTimeMillis();
			equipoIdeal aux = new equipoIdeal(generarEmpleados(n));
			aux.asignarLimites(1, 2, 4, 5);
			aux.generarEquipo();
			
			long fin = System.currentTimeMillis();
			double tiempo = (fin - inicio) / 1000.0;
			
			System.out.println("n = " + n + ": " + tiempo + " seg.");
		}
	}
	
	private Empresa generarEmpleados(int n) {
		Empresa emp = new Empresa();
		Random rand = new Random();
		
		for(Integer i = 0; i < n; i++) {
			Empleado aux = new Empleado(i.toString()+"ASD", rand.nextInt(1,6), _roles[rand.nextInt(4)]);
			emp.agregarEmpleado(aux);
		}
		
		return emp;
	}
	
	public static void main(String[] args) {
		StressTestEquipoIdeal aux = new StressTestEquipoIdeal();
		aux.stressTest();
	}

}
