package Auxiliares;

import java.util.Comparator;

public class comparadorPorIncompatibilidad implements Comparator<Empleado> {

	@Override //-1 menor, 0 igual, 1 mayor
	public int compare(Empleado emp1, Empleado emp2) {
		return emp1.getCantEnemigos() - emp2.getCantEnemigos();
	}

}
