package Main;

import Modelo.equipoIdeal;
import vista.Interfaz;

import java.awt.EventQueue;

import Auxiliares.Empresa;

public class main {

	public static void main(String[] args) {
		/*	Empresa emp = new Empresa();
		equipoIdeal solver = new equipoIdeal(emp);
		solver.asignarLimites(1, 2, 4, 5);
		solver.generarEquipo();
		System.out.println("\n EQUIPO IDEAL: \n"+solver.getEquipoIdeal().toString());*/
		//System.out.println(solver.heuristica());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
