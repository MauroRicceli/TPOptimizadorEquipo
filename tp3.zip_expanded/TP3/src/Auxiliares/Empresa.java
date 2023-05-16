package Auxiliares;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Empresa {
	private LinkedList<Empleado> _empleadosPosibles;
	private LinkedList<Tupla<Empleado, Empleado>> _empleadosEnemistados;
	private  Quartet<Integer, Integer, Integer, Integer> _disponibilidad;

	public Empresa() {
		_empleadosPosibles = new LinkedList<Empleado>();
		_empleadosEnemistados = new LinkedList<Tupla<Empleado, Empleado>>();
		_disponibilidad = new Quartet<Integer, Integer, Integer, Integer>(0,0,0,0);
		generarEmpleados();
		calcularDisponibilidad();
	}
	
	public void agregarEmpleado(Empleado empleado) {
		if(_empleadosPosibles.contains(empleado)) {
			return;
		}
		_empleadosPosibles.add(empleado);
	}
	
	public void agregarEnemistad(Empleado empleado1, Empleado empleado2) {
		if(!_empleadosPosibles.contains(empleado1) || !_empleadosPosibles.contains(empleado2)) {
			return;
		}
		
		Tupla<Empleado, Empleado> enemistad = new Tupla<Empleado, Empleado>(empleado1, empleado2);
		if(_empleadosEnemistados.contains(enemistad) || _empleadosEnemistados.contains(enemistad.tuplaRevertida())) {
			return;
		}
			
		_empleadosPosibles.get(ubicacionEnListaDelEmpleado(empleado1)).agregarEnemigo();
		_empleadosPosibles.get(ubicacionEnListaDelEmpleado(empleado2)).agregarEnemigo();
		_empleadosEnemistados.add(enemistad);
	}
	
	public boolean existeEnemistad(Empleado empleado1, Empleado empleado2) {
		Tupla<Empleado, Empleado> enemistad = new Tupla<Empleado, Empleado>(empleado1, empleado2);
		if(_empleadosEnemistados.contains(enemistad) || _empleadosEnemistados.contains(enemistad.tuplaRevertida())) {
			return true;
		}
		return false;
	}
	
	public void eliminarEnemistad(Empleado empleado1, Empleado empleado2) {
		if(!existeEnemistad(empleado1, empleado2)) {
			return;
		}
		
		_empleadosPosibles.get(ubicacionEnListaDelEmpleado(empleado1)).eliminarEnemigo();
		_empleadosPosibles.get(ubicacionEnListaDelEmpleado(empleado2)).eliminarEnemigo();
		
		Tupla<Empleado, Empleado> enemistad = new Tupla<Empleado, Empleado>(empleado1, empleado2);
		for(int i = 0; i <= _empleadosEnemistados.size()-1; i++)  {
			if(_empleadosEnemistados.get(i).equals(enemistad) || _empleadosEnemistados.get(i).equals(enemistad.tuplaRevertida())) {
				_empleadosEnemistados.remove(i);
			}
		}
	}
	
	public LinkedList<Empleado> ordenarListaEmpleados(Comparator<Empleado> comparador){
		LinkedList<Empleado> ret = _empleadosPosibles;
		Collections.sort(ret, comparador);
		return ret;
		
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
	
	public void generarEmpleados() {
		Empleado empleado1 = new Empleado("Manuel Rodriguez", 5, "Arquitecto");
		Empleado empleado2 = new Empleado("Juan Francisco Real", 4, "Arquitecto");
		Empleado empleado3 = new Empleado("Francisco Roppo", 1, "Jefe de Equipo");
		Empleado empleado4 = new Empleado("German Ritondo", 4, "Jefe de Equipo");
		Empleado empleado5 = new Empleado("Luciano Rodriguez", 5, "Programador");
		Empleado empleado6 = new Empleado("Jazmin Esteguez", 2, "Tester");
		Empleado empleado7 = new Empleado("Julieta Poppero", 2, "Tester");
		Empleado empleado8 = new Empleado("Ludmila Zerriak", 5, "Programador");
		Empleado empleado9 = new Empleado("Roger Federer", 3, "Programador");
		Empleado empleado10 = new Empleado("Lionel Messi", 4, "Programador");
		Empleado empleado11 = new Empleado("Ramiro Montiel", 1, "Tester");
		Empleado empleado12 = new Empleado("Luciano Capria", 2, "Arquitecto");
		Empleado empleado13 = new Empleado("Mauro Ricceli", 4, "Arquitecto");
		Empleado empleado14 = new Empleado("Facundo Vultaggio", 2, "Tester");
		Empleado empleado15 = new Empleado("Juan Ignacio Valdez", 3, "Tester");
		Empleado empleado16 = new Empleado("Omar Repetto", 1, "Tester");
		Empleado empleado17 = new Empleado("Niko Bellik", 3, "Programador");
		Empleado empleado18 = new Empleado("Shieda Kayn", 4, "Programador");
		Empleado empleado19 = new Empleado("Karim Benzema", 5, "Arquitecto");
		Empleado empleado20 = new Empleado("Fausto Repri", 5, "Jefe de Equipo");
		Empleado empleado21 = new Empleado("Don Omar", 2, "Arquitecto");
		Empleado empleado22 = new Empleado("Geronimo Raster", 1, "Tester");
		Empleado empleado23 = new Empleado("Felipe Gilippe", 3, "Programador");
		
		agregarEmpleado(empleado1);
		agregarEmpleado(empleado2);
		agregarEmpleado(empleado3);
		agregarEmpleado(empleado4);
		agregarEmpleado(empleado5);
		agregarEmpleado(empleado6);
		agregarEmpleado(empleado7);
		agregarEmpleado(empleado8);
		agregarEmpleado(empleado9);
		agregarEmpleado(empleado10);
		agregarEmpleado(empleado11);
		agregarEmpleado(empleado12);
		agregarEmpleado(empleado13);
		agregarEmpleado(empleado14);
		agregarEmpleado(empleado15);
		agregarEmpleado(empleado16);
		agregarEmpleado(empleado17);
		agregarEmpleado(empleado18);
		agregarEmpleado(empleado19);
		agregarEmpleado(empleado20);
		agregarEmpleado(empleado21);
		agregarEmpleado(empleado22);
		agregarEmpleado(empleado23);
		
		agregarEnemistad(empleado19, empleado23);
		agregarEnemistad(empleado1, empleado20);
		agregarEnemistad(empleado13, empleado14);
		agregarEnemistad(empleado7, empleado5);
		agregarEnemistad(empleado8, empleado3);
		
		agregarEnemistad(empleado1, empleado2);
		agregarEnemistad(empleado1, empleado3);
		agregarEnemistad(empleado1, empleado4);
		agregarEnemistad(empleado1, empleado5);
		agregarEnemistad(empleado1, empleado6);
		agregarEnemistad(empleado1, empleado7);
		agregarEnemistad(empleado1, empleado8);
		agregarEnemistad(empleado1, empleado9);
		agregarEnemistad(empleado1, empleado10);
		agregarEnemistad(empleado1, empleado11);
		agregarEnemistad(empleado1, empleado12);
		agregarEnemistad(empleado1, empleado13);
		agregarEnemistad(empleado1, empleado14);
		agregarEnemistad(empleado1, empleado15);
		agregarEnemistad(empleado1, empleado16);
		agregarEnemistad(empleado1, empleado17);
		agregarEnemistad(empleado1, empleado18);
		agregarEnemistad(empleado1, empleado19);
		agregarEnemistad(empleado1, empleado20);
		agregarEnemistad(empleado1, empleado21);
		agregarEnemistad(empleado1, empleado22);
		agregarEnemistad(empleado1, empleado23);
		
	}

	public int ubicacionEnListaDelEmpleado(Empleado ingresado) {
		if (ingresado != null) {
			for (int i = 0; i < _empleadosPosibles.size() ; i++) {
				if (_empleadosPosibles.get(i).equals(ingresado)) {
					return i;
				}
			}
		}
		return -1;
	}

	public boolean removerEmpleado(Empleado ingresado) {
		int existente = ubicacionEnListaDelEmpleado(ingresado);
		if (existente != -1) {
			Empleado aux = _empleadosPosibles.get(existente);
			for(Empleado emp : _empleadosPosibles) {
				if(!emp.equals(aux)) {
					eliminarEnemistad(aux, emp);
				}
			}
			_empleadosPosibles.remove(existente);
			return true;
		}
		return false;
		
	}

	//aux: suma y compara los ya existentes de generarEmpleado()
	public void calcularDisponibilidad() {
		if (_disponibilidad != null) {
			String roles[]  = {"jefe de equipo","arquitecto","programador","tester"};
			for (Empleado empleado : _empleadosPosibles) {
				if (empleado.getRol().toLowerCase().equals(roles[3])) {
					_disponibilidad.setFourth(_disponibilidad.getFourth()+1);
				}
				if (empleado.getRol().toLowerCase().equals(roles[2])) {
					_disponibilidad.setThird(_disponibilidad.getThird()+1);
				}
			
				if (empleado.getRol().toLowerCase().equals(roles[1])) {
					_disponibilidad.setSecond(_disponibilidad.getSecond()+1);
				}
				if (empleado.getRol().toLowerCase().equals(roles[0])) {
					_disponibilidad.setFirst(_disponibilidad.getFirst()+1);
				}
			}	
		}
	}

	public Quartet<Integer, Integer, Integer, Integer> get_disponibilidad() {
		return _disponibilidad;
	}
	
	
	
}
