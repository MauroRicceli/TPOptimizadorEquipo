package Auxiliares;

public class Empleado {
	private String _rol;
	private String _nombre;
	private Integer _calificacionHistorica;
	
	
	public Empleado(String nombre, Integer calificacion, String rol) {
		datosValidos(nombre, calificacion);
		_nombre = nombre;
		_rol = rol;
		_calificacionHistorica = calificacion;
	}
	
	private void datosValidos(String nombre, Integer calificacion) {
		if(nombre.length() < 3) {
			throw new IllegalArgumentException("Ingresar un nombre válido con más de 2 carácteres para una persona: "+nombre);
		}
		if(calificacion <= 0 || calificacion > 5) {
			throw new IllegalArgumentException("La calificacion debe ser un numero entre 1 y 5: "+calificacion);
		}
	}
	
	public boolean equals(Object object) {
		if(object instanceof Empleado) {
			Empleado aux = (Empleado) object;
			if(aux.getCalificacion().equals(_calificacionHistorica) && aux.getNombre().equals(_nombre) && aux.getRol().equals(_rol)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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

}
