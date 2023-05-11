package Auxiliares;

import java.util.HashSet;
import java.util.LinkedList;

public class Empresa {
	private HashSet<Empleado> _empleadosPosibles;
	private LinkedList<Tupla<Empleado, Empleado>> _empleadosEnemistados;
	
	public Empresa() {
		_empleadosPosibles = new HashSet<Empleado>();
		_empleadosEnemistados = new LinkedList<Tupla<Empleado, Empleado>>();
	}
	
	public void agregarEmpleado(String nombre, Integer calificacion, String rol) {		
		Empleado aux = new Empleado(nombre, calificacion, rol);
		_empleadosPosibles.add(aux);
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
	
	public HashSet<Empleado> getEmpleadosPosibles(){
		return _empleadosPosibles;
	}
	
	public LinkedList<Tupla<Empleado, Empleado>> getEmpleadosEnemistados(){
		return _empleadosEnemistados;
	}
	
	
}
