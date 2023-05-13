package Auxiliares;

/**
 * Clase que permite representar un objeto ordenado con dos argumentos, siendo estos cualquier objeto existente.
 * @author Mauro Ricceli
 * @author Jazmin Quinteros
 *
 * @param <A> objeto 1
 * @param <B> objeto 2
 * @param <C> objeto 3
 * @param <D> objeto 4
 * 
 */

public class Quartet<A, B, C, D> {
	private A _objeto1;
	private B _objeto2;
	private C _objeto3;
	private D _objeto4;
	
	/**
	 * Crea una tupla con los dos objetos ingresados como argumento
	 * @param <objeto1> primer objeto
	 * @param <objeto2> segundo objeto
	 * @param <objeto3> tercer objeto
	 * @param <objeto4> cuarto objeto
	 */
	
	public Quartet(A objeto1, B objeto2, C objeto3, D objeto4) {
		_objeto1 = objeto1;
		_objeto2 = objeto2;
		_objeto3 = objeto3;
		_objeto4 = objeto4;
	}
	
	/**
	 * Analiza si dos tuplas son exactamente iguales
	 * @return true si son iguales // false si son distintas
	 */
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Quartet) {
			@SuppressWarnings("unchecked")
			Quartet<A,B,C,D> aux = (Quartet<A,B,C,D>) object;
			if(_objeto1.equals(aux.getFirst()) && _objeto2.equals(aux.getSecond()) && _objeto3.equals(aux.getThird()) && _objeto4.equals(aux.getFourth())) {
				return true;
			} else {
				return false;
			}	
		} else {
			return false;
		}
	}
	
	public A getFirst() {
		return _objeto1;
	}
	
	public B getSecond() {
		return _objeto2;
	}
	
	public C getThird() {
		return _objeto3;
	}
	
	public D getFourth() {
		return _objeto4;
	}
	
	public void setFirst(A objeto) {
		_objeto1 = objeto;
	}
	
	public void setSecond(B objeto) {
		_objeto2 = objeto;
	}
	
	public void setThird(C objeto) {
		_objeto3 = objeto;
	}
	
	public void setFourth(D objeto) {
		_objeto4 = objeto;
	}
}
