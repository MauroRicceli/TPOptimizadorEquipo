package Main;

import Modelo.equipoIdeal;
import Auxiliares.Empresa;

public class main {

	public static void main(String[] args) {
		Empresa emp = new Empresa();
		equipoIdeal solver = new equipoIdeal(emp);
		solver.generarEmpleados();
		solver.generarEquipo();
		System.out.println(solver.getEquipoIdeal().toString());
	}

}