package Main;

import Modelo.equipoIdeal;
import Auxiliares.Empresa;

public class main {

	public static void main(String[] args) {
		Empresa emp = new Empresa();
		equipoIdeal solver = new equipoIdeal(emp);
		solver.asignarLimites(1, 2, 4, 5);
		solver.generarEmpleados();
		solver.generarEquipo();
		System.out.println("\n EQUIPO IDEAL: \n"+solver.getEquipoIdeal().toString());
	}

}
