package vista;

import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

import Auxiliares.Empresa;
import Modelo.equipoIdeal;

import java.awt.Color;

@SuppressWarnings("serial")
public class Interfaz extends JFrame {
	private Empresa empresa;
	private equipoIdeal equipo;
	public Interfaz() {
		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 472);	
		
		inicializar();
		
		icono();
		inicio();
	}

	public void inicializar() {
		empresa = new Empresa();
		equipo = new equipoIdeal(empresa);
	}
	
	public void registro() {
		Registro.crear(this,empresa);
	}
	
	public void inicio() {
		Inicio.crear(this);
	}
	
	public void limite() {
		Limites.crear(this, empresa);
	}
	
	public void incompatibilidad() {
		Incompatibilidad.crear(this,empresa);
	}
	
	public void disponibilidad() {
		Disponibles.crear(this, empresa);
	}
	
	public void resultado() {
		Resultado.crear(this);
	}
	
	public static void crearNuevaInterfaz() {
		new Interfaz();
	}


	public void icono() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/imagenes/icono.png")));
	}

	public Window getFrame() {
		return this;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public equipoIdeal getEquipo() {
		return equipo;
	}
	
	
}
