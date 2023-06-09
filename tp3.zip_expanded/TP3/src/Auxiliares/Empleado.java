package Auxiliares;

import java.util.Objects;

public class Empleado {
	private String _rol;
	private String _nombre;
	private Integer _calificacionHistorica;
	private Integer _cantEnemigos;
	
	
	public Empleado(String nombre, Integer calificacion, String rol) {
		datosValidos(nombre, calificacion);
		_nombre = nombre;
		_rol = rol;
		_calificacionHistorica = calificacion;
		_cantEnemigos = 0;
	}
	
	private void datosValidos(String nombre, Integer calificacion) {
		if(nombre.length() < 3) {
			throw new IllegalArgumentException("Ingresar un nombre válido con más de 2 carácteres para una persona: "+nombre);
		}
		if(calificacion <= 0 || calificacion > 5) {
			throw new IllegalArgumentException("La calificacion debe ser un numero entre 1 y 5: "+calificacion);
		}
	}
	
	public void agregarEnemigo() {
		_cantEnemigos = _cantEnemigos + 1;
	}
	
	public void eliminarEnemigo() {
		_cantEnemigos = _cantEnemigos - 1;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Empleado) {
			Empleado aux = (Empleado) object;
			if(aux.getCalificacion().equals(_calificacionHistorica) && aux.getNombre().toLowerCase().equals(_nombre.toLowerCase()) && aux.getRol().toLowerCase().equals(_rol.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return _nombre+" siendo un "+_rol+" de la empresa con una calificacion histórica de "+_calificacionHistorica+"\n";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(_nombre.toLowerCase(), _rol.toLowerCase(), _calificacionHistorica);
	}
	
	public String getRol() {
		return _rol;
	}
	
	public String getNombre() {
		return _nombre;
	}
	
	public Integer getCalificacion() {
		return _calificacionHistorica;
	}
	
	public Integer getCantEnemigos() {
		return _cantEnemigos;
	}

}
