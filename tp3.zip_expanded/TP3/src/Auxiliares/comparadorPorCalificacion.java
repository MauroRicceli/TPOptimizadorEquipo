package Auxiliares;

import java.util.Comparator;

public class comparadorPorCalificacion implements Comparator<Empleado> {

	@Override
	public int compare(Empleado o1, Empleado o2) {
		return o2.getCalificacion() - o1.getCalificacion();
	}

}
