package Auxiliares;

import java.util.LinkedList;

public class Empresa {
	private LinkedList<Empleado> _empleadosPosibles;
	private LinkedList<Tupla<Empleado, Empleado>> _empleadosEnemistados;
	
	public Empresa() {
		_empleadosPosibles = new LinkedList<Empleado>();
		_empleadosEnemistados = new LinkedList<Tupla<Empleado, Empleado>>();
	}
	
	public void agregarEmpleado(Empleado empleado) {		
		_empleadosPosibles.add(empleado);
	}
	
	public void agregarEnemistad(Empleado empleado1, Empleado empleado2) {
		Tupla<Empleado, Empleado> enemistad = new Tupla<Empleado, Empleado>(empleado1, empleado2);
		if(_empleadosEnemistados.contains(enemistad) || _empleadosEnemistados.contains(enemistad.tuplaRevertida())) {
			return;
		}
		_empleadosEnemistados.add(enemistad);
	}
	
	public boolean existeEnemistad(Empleado empleado1, Empleado empleado2) {
		Tupla<Empleado, Empleado> enemistad = new Tupla<Empleado, Empleado>(empleado1, empleado2);
		if(_empleadosEnemistados.contains(enemistad) || _empleadosEnemistados.contains(enemistad.tuplaRevertida())) {
			return true;
		}
		return false;
	}
	
	public LinkedList<Empleado> getEmpleadosPosibles(){
		return _empleadosPosibles;
	}
	
	public LinkedList<Tupla<Empleado, Empleado>> getEmpleadosEnemistados(){
		return _empleadosEnemistados;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Empleados Posibles: { \n");
		for(Empleado emp : _empleadosPosibles) {
			sb.append(emp.toString()+" ");
		}
		sb.append("} \n Empleados enemistados { \n");
		for(Tupla<Empleado, Empleado> enemistad : _empleadosEnemistados) {
			sb.append("< "+enemistad.getFirst().getNombre()+", "+enemistad.getSecond().getNombre()+" > \n");
		}
		sb.append("} \n");
		
		return sb.toString();
	}
	
	
}
