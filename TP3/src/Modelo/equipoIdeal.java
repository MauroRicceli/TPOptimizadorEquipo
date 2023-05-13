package Modelo;

import java.util.ArrayList;

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
			fuerzaBruta(sol, calificacionParcial, iterador+1);
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
		
		_empresa.agregarEmpleado(empleado1);
		_empresa.agregarEmpleado(empleado2);
		_empresa.agregarEmpleado(empleado3);
		_empresa.agregarEmpleado(empleado4);
		_empresa.agregarEmpleado(empleado5);
		_empresa.agregarEmpleado(empleado6);
		_empresa.agregarEmpleado(empleado7);
		_empresa.agregarEmpleado(empleado8);
		_empresa.agregarEmpleado(empleado9);
		_empresa.agregarEmpleado(empleado10);
		_empresa.agregarEmpleado(empleado11);
		_empresa.agregarEmpleado(empleado12);
		_empresa.agregarEmpleado(empleado13);
		_empresa.agregarEmpleado(empleado14);
		_empresa.agregarEmpleado(empleado15);
		_empresa.agregarEmpleado(empleado16);
		_empresa.agregarEmpleado(empleado17);
		_empresa.agregarEmpleado(empleado18);
		_empresa.agregarEmpleado(empleado19);
		_empresa.agregarEmpleado(empleado20);
		_empresa.agregarEmpleado(empleado21);
		_empresa.agregarEmpleado(empleado22);
		_empresa.agregarEmpleado(empleado23);
		
		_empresa.agregarEnemistad(empleado19, empleado23);
		_empresa.agregarEnemistad(empleado1, empleado20);
		_empresa.agregarEnemistad(empleado13, empleado14);
		_empresa.agregarEnemistad(empleado7, empleado5);
		_empresa.agregarEnemistad(empleado8, empleado3);
		
	}
	
	public Empresa getEmpresa() {
		return _empresa;
	}
	
	public ArrayList<Empleado> getEquipoIdeal() {
		return _equipoIdeal;
	}

}

