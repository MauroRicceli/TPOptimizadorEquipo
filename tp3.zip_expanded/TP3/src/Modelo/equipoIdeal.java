package Modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import Auxiliares.Empleado;
import Auxiliares.Empresa;
import Auxiliares.Quartet;

public class equipoIdeal {
	private ArrayList<Empleado> _equipoIdeal;
	private Integer _calificacionTotal;
	private Empresa _empresa;
	private Quartet<Integer, Integer, Integer, Integer> _limiteEmpleados; //<jefe equipo, arquitecto, programador, tester>
	private Integer _cantEmpleados;
	
	
	public equipoIdeal(Empresa empresa) {
		_equipoIdeal = new ArrayList<Empleado>();
		_empresa = empresa;
		_calificacionTotal = 0;
	}
	
	public void generarEquipo() {
		ArrayList<Empleado> solucion = new ArrayList<Empleado>();
		fuerzaBruta(solucion, 0, 0);
	}
	
	public void asignarLimites(Integer jefeEquipos, Integer arquitectos, Integer programadores, Integer testers) {
		Quartet<Integer, Integer, Integer, Integer> aux = new Quartet<Integer, Integer, Integer, Integer>(jefeEquipos, arquitectos, programadores, testers);
		_limiteEmpleados = aux;
		_cantEmpleados = aux.getFirst()+aux.getSecond()+aux.getThird()+aux.getFourth();
	}
	
	//TESTEAR
	@SuppressWarnings("unchecked")
	private void fuerzaBruta(ArrayList<Empleado> sol, int calificacionParcial, int iterador){
		if(sol.size() == _cantEmpleados) {
			if((calificacionParcial > _calificacionTotal)) {
				_calificacionTotal = calificacionParcial;
				_equipoIdeal = (ArrayList<Empleado>) sol.clone();
				return;
			} else {
				return;
			}
		}
		
		if(iterador == _empresa.getEmpleadosPosibles().size()) {
			return;
		}
		
		Empleado emp = _empresa.getEmpleadosPosibles().get(iterador);
		if(esPosibleAñadirAlEquipo(emp, sol)) {
			
			sol.add(emp);
			fuerzaBruta(sol, calificacionParcial+emp.getCalificacion(), iterador+1);
			
			sol.remove(emp);
			fuerzaBruta(sol, calificacionParcial, iterador+1);
			
		} else {
			fuerzaBruta(sol, calificacionParcial, iterador+1); //backtracking??
		}
	}
	
	private boolean esPosibleAñadirAlEquipo(Empleado empleado, ArrayList<Empleado> solucionParcial) {
		for(Empleado empleadoIdeal : solucionParcial) {
			if(_empresa.existeEnemistad(empleado, empleadoIdeal)) {
				return false;
			}
		}
		int contRol = 0;
		for(Empleado empleadoIdeal : solucionParcial) {
			if(empleadoIdeal.getRol().toLowerCase().equals(empleado.getRol().toLowerCase())) {
				contRol++;
			}
		}
		
		return obtenerLimiteEmpleadoPorEquipo(empleado, contRol);		
	}
	
	private boolean obtenerLimiteEmpleadoPorEquipo(Empleado empleado, Integer contRol) {
		if(empleado.getRol().toLowerCase().equals("jefe de equipo")) {
			return contRol < _limiteEmpleados.getFirst();
		}
		if(empleado.getRol().toLowerCase().equals("arquitecto")) {
			return contRol < _limiteEmpleados.getSecond();
		}
		if(empleado.getRol().toLowerCase().equals("programador")) {
			return contRol < _limiteEmpleados.getThird();
		}
		if(empleado.getRol().toLowerCase().equals("tester")) {
			return contRol < _limiteEmpleados.getFourth();
		}
		return false;
	}
	
	
	public void buscarSolucionHeuristica() {
		ArrayList<Empleado> posibleSolucion1 = heuristicaPorCalificacion();
		if(posibleSolucion1 != null) {
			_equipoIdeal = posibleSolucion1;
		}
		
		ArrayList<Empleado> posibleSolucion2 = heuristicaPorIncompatibilidad();
		if(posibleSolucion2 != null) {
			if(posibleSolucion1 != null) {
				if(calcularCalificacionTotal(posibleSolucion1) < calcularCalificacionTotal(posibleSolucion2)) {
					_equipoIdeal = posibleSolucion2;
				}
			} else {
				_equipoIdeal = posibleSolucion2;
			}
		}
	}
	
	
	public ArrayList<Empleado> heuristicaPorCalificacion() {
		LinkedList<Empleado> sortedListaEmpleados = _empresa.ordenarListaEmpleados(new Comparator<Empleado>() {
			
			@Override
			public int compare(Empleado o1, Empleado o2) {
				return o2.getCalificacion() - o1.getCalificacion();
			}
			
		});
		
		ArrayList<Empleado> equipoIdealParcial = new ArrayList<Empleado>();
		
		for(Empleado emp : sortedListaEmpleados) {
			if(equipoIdealParcial.size() == _cantEmpleados) {
				return equipoIdealParcial;
			}
			
			if(esPosibleAñadirAlEquipo(emp, equipoIdealParcial)) {
				equipoIdealParcial.add(emp);
			}
		}
		return null;
	}
	
	public ArrayList<Empleado> heuristicaPorIncompatibilidad() {
		
		LinkedList<Empleado> sortedListaEmpleados = _empresa.ordenarListaEmpleados(new Comparator<Empleado>() {

			@Override //-1 menor, 0 igual, 1 mayor
			public int compare(Empleado emp1, Empleado emp2) {
				return emp1.getCantEnemigos() - emp2.getCantEnemigos();
			}
			
		});
		
		ArrayList<Empleado> equipoIdealParcial = new ArrayList<Empleado>();
		
		for(Empleado emp : sortedListaEmpleados) {
			if(equipoIdealParcial.size() == _cantEmpleados) {
				return equipoIdealParcial;
			}
			
			if(esPosibleAñadirAlEquipo(emp, equipoIdealParcial)) {
				equipoIdealParcial.add(emp);
			}
		}
		return null;	
	}
	
	public Integer calcularCalificacionTotal(ArrayList<Empleado> equipo) {
		Integer ret = 0;
		for(Empleado emp : equipo) {
			ret = ret + emp.getCalificacion();
		}
		return ret;
	}
	
	public Empresa getEmpresa() {
		return _empresa;
	}
	
	public ArrayList<Empleado> getEquipoIdeal() {
		return _equipoIdeal;
	}

	public Quartet<Integer, Integer, Integer, Integer> get_limiteEmpleados() {
		return _limiteEmpleados;
	}
	

}

