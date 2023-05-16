package vista;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Inicio extends JLayeredPane {
	Interfaz _frame;
	public Inicio(Interfaz frame) {
		_frame = frame;
		
		frame.getContentPane().add(this);
		iniciar();
		cargarImagenes();
	}
	
	private void cargarImagenes() {
		JLabel imagen = new JLabel("");
		JLabel fondo = new JLabel("");
		
		imagen.setIcon(new ImageIcon(Inicio.class.getResource("/imagenes/imagen2.png")));
		fondo.setIcon(new ImageIcon(Inicio.class.getResource("/imagenes/fondo.png")));
		
		fondo.setHorizontalAlignment(SwingConstants.CENTER);

		imagen.setBounds(23, 26, 300, 247);
		fondo.setBounds(0, 0, 839, 77);

		add(imagen);
		add(fondo);

	}
	

	private void iniciar() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 854, 472);	
		
		JLabel []labels = {new JLabel("SOFTWARE FACTORY"),  new JLabel("EL MEJOR EQUIPO")};
		JLabel []textos = {new JLabel("RR.HH: nuevo grupo de desarrollo"),
						   new JLabel("Roles demandados para el nuevo proyecto: "),
						   new JLabel("l\u00EDder de proyecto, programador, tester y arquitecto")};
		JSeparator []separadores = {new JSeparator(),new JSeparator()};
		
		for (JLabel label : labels) {
			label.setForeground(new Color(0, 128, 128));
		    label.setFont(new Font("Arial Black", Font.PLAIN, 18));
		    add(label);
		}
		
		for (JLabel label : textos) {
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Arial", Font.PLAIN, 10));
		    add(label);
		}

		for (JSeparator separador : separadores) {
			separador.setForeground(new Color(0, 128, 128));
			separador.setBackground(new Color(0, 128, 128));
			add(separador);
		}

		labels[0].setBounds(365, 88, 253, 28);
		//labels[1].setBounds(49, 279, 253, 28);
		
		textos[0].setBounds(365, 118, 253, 35);
		//textos[1].setBounds(49, 304, 253, 23);
		//textos[2].setBounds(49, 318, 253, 23);
		
	
		separadores[0].setBounds(365, 118, 228, 7);
		//separadores[1].setBounds(49, 300, 228, 7);

		opciones();
	
	}

	private void opciones() {
		JButton botones[] = {new JButton("DISPONIBILIDAD"),new JButton("REGISTRO"),
							 new JButton("REQUERIMIENTOS"),new JButton("INCOPATIBILIDADES")};
		
		for (JButton boton : botones) {
			boton.setHorizontalAlignment(SwingConstants.LEFT);
			boton.setFont(new Font("Arial Black", Font.BOLD, 13));
			boton.setBackground(new Color(255, 255, 255));
			boton.setForeground(Color.DARK_GRAY);
			add(boton);
		}
		
		botones[0].setBounds(365, 282, 242, 23);
		botones[1].setBounds(365, 204, 242, 23);
		//botones[2].setBounds(365, 316, 242, 23);
		botones[3].setBounds(365, 350, 242, 23);
	
		//DISPONIBILIDAD
		botones[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.disponibilidad();
			}
		});
		
		//REGISTRO
		botones[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.limite();
			}
		});
	
		//REQUERIMIENTOS
		botones[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.resultado();
			}
			
		});
		
		//INCOPATIBILIDADES
		botones[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.incompatibilidad();
			}
			
		});
		
	}

	protected void remover() {
		_frame.remove(this);
		_frame.repaint();
	}

	public static void crear(Interfaz frame) {
		new Inicio(frame);
		
	}
}