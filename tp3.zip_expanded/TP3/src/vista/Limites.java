package vista;

import javax.swing.JPanel;

import Auxiliares.Empresa;
import Auxiliares.Quartet;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Limites extends JPanel {
	private Interfaz _frame;
	private Empresa _empresa;
	public Limites(Interfaz frame, Empresa empresa) {
		_frame = frame;
		_empresa = empresa;
		
		setBounds(0, 0, 839, 434);	
		frame.getContentPane().add(this);
		setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(87, 0, 696, 434);
		add(panel);
		panel.setLayout(null);
		
		cargarLabels(panel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(71, 93, 327, 2);
		panel.add(separator);
		
		JSpinner programadores = new JSpinner();
		programadores.setBounds(73, 154, 48, 35);
		panel.add(programadores);
		
		JSpinner testers = new JSpinner();
		testers.setBounds(73, 200, 48, 35);
		panel.add(testers);
		
		JSpinner arquitectos = new JSpinner();
		arquitectos.setBounds(73, 252, 48, 35);
		panel.add(arquitectos);
		
		JSpinner lider = new JSpinner();
		lider.setBounds(73, 312, 48, 35);
		panel.add(lider);
		
		
		JButton btnNewButton = new JButton("Continuar >> ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quartet<Integer,Integer,Integer,Integer> limites = verificarLimites(programadores, testers, arquitectos, lider);
				
				if (limites != null) {
					_frame.getEquipo().asignarLimites(1, 2, 4, 5); //<jefe equipo, arquitecto, programador, tester>
					remover();
					_frame.registro();
				}
			}
		});
		
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(73, 367, 124, 23);
		panel.add(btnNewButton);
		
		
		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/IconoSalir.png")));
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setBounds(616, 29, 39, 32);
		btnVolver.setBorder(null);
		panel.add(btnVolver);
			
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.inicio();
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Limites.class.getResource("/imagenes/fondo.png")));
		lblNewLabel.setBounds(0, 0, 839, 434);
		add(lblNewLabel);
	}

	private void cargarLabels(JPanel panel) {
		JLabel lblNewLabel_1 = new JLabel("Limites para cada puesto");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_1.setBounds(73, 67, 221, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PROGRAMADORES");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(136, 164, 124, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("TESTERS");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(136, 210, 124, 14);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("ARQUITECTOS");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(136, 262, 124, 14);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("LIDER DE PROYECTO");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_3.setBounds(136, 322, 124, 14);
		panel.add(lblNewLabel_2_3);
		

		
		JLabel lblNewLabel_3 = new JLabel("Seleccione la cantidad de cupos disponibles para cada rol");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setBounds(73, 106, 375, 14);
		panel.add(lblNewLabel_3);
	}
	
	@SuppressWarnings("null")
	private Quartet<Integer,Integer,Integer,Integer> verificarLimites(JSpinner programadores, JSpinner testers, JSpinner arquitectos,JSpinner lider) {
		//<jefe equipo, arquitecto, programador, tester>
		Quartet<Integer,Integer,Integer,Integer> limites = new Quartet<Integer,Integer,Integer,Integer>(0,0,0,0) ;	
		
		//JEFE DE EQUIPO  #1
		if ((Integer) lider.getValue() >= 1) {
			limites.setFirst((Integer) lider.getValue());
		} else {
			JOptionPane.showMessageDialog(null, "El valor ingresado para lideres no es válido "+ lider.getValue());
			return null;
		}
		
		//ARQUITECTO #2
		if ((Integer) arquitectos.getValue() >= 1) {
			limites.setSecond((Integer) arquitectos.getValue());
		} else {
			JOptionPane.showMessageDialog(null, "El valor ingresado para arquitectos no es válido "+ arquitectos.getValue());
			return null;
		} 
		
		//PROGRAMADOR #3
		if ((Integer) programadores.getValue() >= 1) {
			limites.setThird((Integer) programadores.getValue());
		} else {
			JOptionPane.showMessageDialog(null, "El valor ingresado para programadores no es válido "+ programadores.getValue());
			return null;
		}
		
		//TESTER #4
		if ((Integer) testers.getValue() >= 1) {
			limites.setFourth((Integer) testers.getValue());
		} else {
			JOptionPane.showMessageDialog(null, "El valor ingresado para testers no es válido "+ testers.getValue());
			return null;
		}
		
		return limites;
	}
	
	protected void remover() {
		_frame.remove(this);
		_frame.repaint();
	}
	
	public static void crear(Interfaz frame, Empresa empresa) {
		new Limites(frame,empresa);	
	}
}
