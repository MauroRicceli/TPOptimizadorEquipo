package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Auxiliares.Empleado;
import Auxiliares.Empresa;
import Auxiliares.Quartet;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;


@SuppressWarnings({"serial","rawtypes","unchecked"})
public class Registro extends JLayeredPane {
	
	private JTextField txtNombre;
	private JComboBox txtRoles;
	private JComboBox txtCalificacion;
	DefaultListModel<String> modelo;
	
	private Interfaz _frame;
	private Empresa _empresa;
	
	JList<String> list;
	
	public Registro(Interfaz frame, Empresa empresa) {
		_frame = frame;  
		_empresa = empresa;
		
		modelo = new DefaultListModel<String>();
		
		setBounds(0, 0, 839, 434);	
		frame.getContentPane().add(this);
		
		panelDatos();
		lista();
	}

	private void botones(JPanel panelDatos) {
		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/IconoSalir.png")));
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setBounds(558, 22, 39, 32);
		btnVolver.setBorder(null);
		panelDatos.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.limite();
			}
		});
		
		JButton btnAgregar = new JButton("");
		btnAgregar.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/agregar.png")));
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.setBounds(38, 327, 49, 38);
		panelDatos.add(btnAgregar);
		btnAgregar.setBorder(null);
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleado ingresado = datosIngresados();
				if (ingresado != null) {
					sumarDisponibilidad(ingresado.getRol(),+1);
					_empresa.agregarEmpleado(ingresado);
					agregarEmpleadoLista(ingresado);
					
					txtNombre.setText("");
					txtRoles.setSelectedIndex(-1);
					txtCalificacion.setSelectedIndex(-1);
				}
			}
		});
		
		JButton btnInicio = new JButton("");
		btnInicio.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/inicio.png")));
		btnInicio.setBorder(null);
		btnInicio.setBackground(Color.WHITE);
		btnInicio.setBounds(607, 22, 41, 32);
		panelDatos.add(btnInicio);
		
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.inicio();
			}
		});
		
		
		
		JButton btnIncompatibilidades = new JButton("Incompatibilidades >>");
		btnIncompatibilidades.setForeground(Color.DARK_GRAY);
		btnIncompatibilidades.setFont(new Font("Arial", Font.BOLD, 11));
		btnIncompatibilidades.setBackground(Color.WHITE);
		btnIncompatibilidades.setBounds(39, 391, 180, 20);
		panelDatos.add(btnIncompatibilidades);
		
		btnIncompatibilidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarLimites()) {
					remover();
					_frame.incompatibilidad();
				}
			}
		});

		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(97, 327, 55, 38);
		panelDatos.add(btnEliminar);
		
		btnEliminar.setBorder(null);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleado ingresado = datosIngresados();
				if (ingresado != null) {
					
					if (_empresa.ubicacionEnListaDelEmpleado(ingresado) != -1) {
						JOptionPane.showMessageDialog(null, "Eliminado del registro: "+ingresado);
						sumarDisponibilidad(ingresado.getRol(),-1);
						modelo.remove(_empresa.ubicacionEnListaDelEmpleado(ingresado));
						list.setModel(modelo);
						_empresa.removerEmpleado(ingresado);
						
						txtNombre.setText("");
						txtRoles.setSelectedIndex(-1);
						txtCalificacion.setSelectedIndex(-1);
						
					} else {
						JOptionPane.showMessageDialog(null, "No existe en el registro");

					}
				}
			}
		});
		
	}

	
	protected boolean verificarLimites() {
		Quartet<Integer,Integer,Integer,Integer> cantidad = _empresa.get_disponibilidad();
		Quartet<Integer,Integer,Integer,Integer> limite = _frame.getEquipo().get_limiteEmpleados();
		if (cantidad.getFirst() < limite.getFirst()) {
			JOptionPane.showMessageDialog(null, "Error: la cantidad de lideres de proyecto es menor a la esperada: "+limite.getFirst());
			return false;
		}
		if (cantidad.getSecond() < limite.getSecond()) {
			JOptionPane.showMessageDialog(null, "Error: la cantidad de arquitectos a la esperada: "+limite.getSecond());
			return false;
		}
		if (cantidad.getThird() < limite.getThird()) {
			JOptionPane.showMessageDialog(null, "Error: la cantidad de programadores es menor a la esperada: "+limite.getThird());
			return false;
		}
		if (cantidad.getFourth() < limite.getFourth()) {
			JOptionPane.showMessageDialog(null, "Error: la cantidad de testers es menor a la esperada: "+limite.getFourth());
			return false;
		}
		return true;
	}

	protected void sumarDisponibilidad(String rol, int operacion) {
		Quartet<Integer,Integer,Integer,Integer> cantidad = _empresa.get_disponibilidad();
		if (cantidad != null) {
			if (rol.toLowerCase().equals("programador")) {
				cantidad.setThird(cantidad.getThird() + operacion);
			}
			if (rol.toLowerCase().equals("tester")) {
				cantidad.setFourth(cantidad.getFourth() + operacion);
			}
			if (rol.toLowerCase().equals("arquitecto")) {
				cantidad.setSecond(cantidad.getSecond() + operacion);
			}
			if (rol.toLowerCase().equals("jefe de equipo")) {
				cantidad.setFirst(cantidad.getFirst() + operacion);
			}
		}
	}

	private void lista() {
		if (_empresa.getEmpleadosPosibles().size() > 0) {
			for (Empleado empleado : _empresa.getEmpleadosPosibles()) {
				agregarEmpleadoLista(empleado);
			}
		}	
	}
	
	private void agregarEmpleadoLista(Empleado empleado) {
		modelo.addElement(empleado.getNombre()+", "+empleado.getRol()+", "+""+empleado.getCalificacion());
		list.setModel(modelo);
	}

	private void panelDatos() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 838, 433);
		add(scrollPane);
		
		JLayeredPane panelFondo = new JLayeredPane();
		scrollPane.setViewportView(panelFondo);
		panelFondo.setBackground(new Color(0, 128, 128));
		panelFondo.setLayout(null);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(new Color(255, 255, 255));
		panelDatos.setBounds(70, 0, 676, 431);
		panelFondo.add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre completo *");
		JLabel lblRegistro = new JLabel("Registro");
		JLabel lblRol = new JLabel("Rol a aplicar *");
		JLabel lblCalificacion = new JLabel("Calificaci\u00F3n hist\u00F3rica por desempe\u00F1o *");
		
		txtNombre = new JTextField();
		txtRoles = new JComboBox();
		txtCalificacion = new JComboBox();
		
		lblNombre.setBounds(50, 88, 180, 14);
		lblRegistro.setBounds(39, 40, 180, 14);
		lblRol.setBounds(50, 178, 180, 14);
		lblCalificacion.setBounds(50, 271, 378, 14);
		
		panelDatos.add(lblNombre);
		panelDatos.add(lblRegistro);
		panelDatos.add(lblRol);
		panelDatos.add(lblCalificacion);
		
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtNombre.setForeground(new Color(128, 128, 128));
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 10));
	//	txtNombre.setText("Ingrese su nombre");
		txtNombre.setBounds(74, 113, 492, 20);
	
		txtRoles.setFont(new Font("Arial", Font.PLAIN, 10));
		txtRoles.setForeground(new Color(128, 128, 128));
		txtRoles.setModel(new DefaultComboBoxModel(new String[] {"Arquitecto", "Jefe de Equipo", "Tester", "Programador"}));
		txtRoles.setBounds(74, 203, 492, 20);
		txtRoles.setSelectedIndex(-1);
		
		txtCalificacion.setFont(new Font("Arial", Font.PLAIN, 10));
		txtCalificacion.setForeground(new Color(128, 128, 128));
		txtCalificacion.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		txtCalificacion.setBounds(74, 296, 492, 20);
		txtCalificacion.setSelectedIndex(-1);
		
		panelDatos.add(txtRoles);
		panelDatos.add(txtCalificacion);
		panelDatos.add(txtNombre);
		
		botones(panelDatos);
		
	
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(281, 348, 283, 67);
		panelDatos.add(scrollPane_1);
		
		list = new JList<String>();
		scrollPane_1.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("Registrados");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBounds(281, 327, 147, 14);
		panelDatos.add(lblNewLabel);
		
		cargarImagen(panelFondo);
		
		panelFondo.setPreferredSize(new Dimension(0,600));
	}

	
	protected Empleado datosIngresados() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("arquitecto");
		roles.add("programador");
		roles.add("tester");
		roles.add("jefe de equipo");
		
		String nombre = txtNombre.getText();
		
		if(txtRoles.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un rol");
			return null;
		}
		if(txtCalificacion.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una calificación");
			return null;
		}
		
		String rol = (String) txtRoles.getSelectedItem();
		int calificacion = Integer.parseInt((String) txtCalificacion.getSelectedItem());
		
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: ventana vacía");
			return null;
		}
		
		if (calificacion < 1 || calificacion > 5) {
			JOptionPane.showMessageDialog(null, "Error: calificación inválida, el intervalo permitido es [1,5]");
			return null;
		}

		return new Empleado(nombre, calificacion,rol);
	}
	
	private void cargarImagen(JLayeredPane panelFondo) {
		JLabel fondo = new JLabel("");
		fondo.setHorizontalAlignment(SwingConstants.LEFT);
		fondo.setVerticalAlignment(SwingConstants.TOP);
		fondo.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/fondo.png")));
		fondo.setBackground(new Color(0, 128, 128));
		fondo.setBounds(0, 0, 836, 838);
		panelFondo.add(fondo);
		
	}
	
	protected void remover() {
		_frame.remove(this);
		_frame.repaint();
	}
	
	public static void crear(Interfaz frame, Empresa empresa) {
		new Registro(frame,empresa);
	}
}