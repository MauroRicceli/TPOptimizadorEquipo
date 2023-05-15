package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Auxiliares.Empleado;
import Auxiliares.Empresa;
import Auxiliares.Tupla;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Incompatibilidad extends JPanel {
	private Interfaz _frame;
	private Empresa _empresa;
	private DefaultListModel<Empleado> modelo;
	private DefaultListModel<String> modeloString;
	private	ArrayList<Empleado> temporal;
	private JList<Empleado> registrados;
	private JList<String> incompatibles;
	
	private Empleado agregado;
	private Integer eliminado;
	
	private JTextField txtNombreEmp1;
	private JTextField txtCalificacionEmp1;
	private JTextField txtRolEmp1;
	
	private JTextField txtNombreEmp2;
	private JTextField txtCalifEmp2;
	private JTextField txtRolEmp2;
	
	private JTable table;
	private JPanel panel;
	
	private JScrollPane scrollPane_2;
	public Incompatibilidad(Interfaz frame, Empresa empresa) {
		modelo = new DefaultListModel<Empleado>();
		modeloString = new DefaultListModel<String>();
		temporal = new ArrayList<Empleado>();

		_frame = frame;
		_empresa = empresa;
		
		frame.getContentPane().add(this);
		
		setBackground(Color.WHITE);
		setBounds(0, 0, 839, 434);
		setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 839, 434);
		add(scrollPane_3);
		
		JPanel panel_1 = new JPanel();
		scrollPane_3.setViewportView(panel_1);
		panel_1.setPreferredSize(new Dimension(0,600));
		panel_1.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(50, 0, 723, 710);
		panel_1.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(59, 356, 428, 64);
		panel.add(scrollPane_2);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 80, 272, 195);
		panel.add(scrollPane);
		
		registrados = new JList<Empleado>();
		scrollPane.setViewportView(registrados);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(381, 80, 270, 193);
		panel.add(scrollPane_1);
		
		incompatibles = new JList<String>();
		scrollPane_1.setViewportView(incompatibles);
		
		JLabel fondo = new JLabel("");
		fondo.setVerticalAlignment(SwingConstants.TOP);
		fondo.setHorizontalAlignment(SwingConstants.CENTER);
		fondo.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/fondo.png")));
		fondo.setBounds(0, 0, 820, 710);
		panel_1.add(fondo);
		
		cargarTextFields();
		cargarLabels();
		cargarBotones();
		cargarLista();
		cargarDisponibles();
	}

	private void cargarTextFields() {
		txtNombreEmp1 = new JTextField();
		txtNombreEmp1.setColumns(10);
		txtNombreEmp1.setBounds(59, 495, 114, 20);
		panel.add(txtNombreEmp1);
		
		txtCalificacionEmp1 = new JTextField();
		txtCalificacionEmp1.setColumns(10);
		txtCalificacionEmp1.setBounds(217, 495, 114, 20);
		panel.add(txtCalificacionEmp1);
		
		txtRolEmp1 = new JTextField();
		txtRolEmp1.setColumns(10);
		txtRolEmp1.setBounds(373, 495, 114, 20);
		panel.add(txtRolEmp1);
		
		txtNombreEmp2 = new JTextField();
		txtNombreEmp2.setColumns(10);
		txtNombreEmp2.setBounds(59, 539, 114, 20);
		panel.add(txtNombreEmp2);
		
		txtCalifEmp2 = new JTextField();
		txtCalifEmp2.setColumns(10);
		txtCalifEmp2.setBounds(217, 539, 114, 20);
		panel.add(txtCalifEmp2);
		
		txtRolEmp2 = new JTextField();
		txtRolEmp2.setColumns(10);
		txtRolEmp2.setBounds(373, 539, 114, 20);
		panel.add(txtRolEmp2);
	}

	private void cargarLabels() {
		JLabel labels[] = {new JLabel("Registrados"),new JLabel("Incompatibles"),new JLabel("Seleccionar o registrar pares incompatibles"),
				new JLabel("Empleados con incopatibilidad"),new JLabel("Ingreso manual"),new JLabel("Nombre"),new JLabel("Calificación"),
				new JLabel("Rol"),new JLabel("Nombre"),new JLabel("Calificación"),new JLabel("Rol")};
		
		for (int label = 0; label < labels.length-1; label++) {
			labels[label].setForeground(Color.DARK_GRAY);
			panel.add(labels[label]);
			if (label == 2) {
				labels[2].setFont(new Font("Arial", Font.BOLD, 14));
			}
			if (label  >= 5) {
				labels[label].setFont(new Font("Arial", Font.BOLD, 11));
			} else {
				labels[label].setFont(new Font("Arial", Font.BOLD, 12));
			}
			panel.add(labels[label]);

		}

		labels[0].setBounds(59, 66, 78, 14);
		labels[1].setBounds(384, 66, 94, 14);
		labels[2].setBounds(59, 29, 322, 34);
		
		labels[3].setBounds(59, 331, 175, 14);
		labels[4].setBounds(59, 453, 247, 14);
		
		
		labels[5].setBounds(59, 478, 114, 14);
		labels[6].setBounds(217, 478, 114, 14);
		labels[7].setBounds(373, 478, 114, 14);
		labels[8].setBounds(59, 526, 114, 14);
		labels[9].setBounds(217, 526, 114, 14);
		labels[10].setBounds(373, 526, 114, 14);
		
	}
	

	private void cargarBotones() {		
		registrados.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		        	agregado = registrados.getSelectedValue();
		        }
		    }
		});
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (agregado != null && temporal.size() < 2) {
					temporal.add(agregado);
				}
				if (temporal.size() == 2) {
					if (!temporal.get(0).equals(temporal.get(1)) && !_empresa.existeEnemistad(temporal.get(0), temporal.get(1))) {
						
						_empresa.agregarEnemistad(temporal.get(0), temporal.get(1));
						modeloString.addElement(temporal.get(0).getNombre() + " y " + temporal.get(1).getNombre());
						incompatibles.setModel(modeloString);

						temporal.clear();
						cargarDisponibles();
						
					} else {
						temporal.clear();
					}
				}
			}
			
		});
		
		btnAgregar.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/agregar.png")));
		btnAgregar.setForeground(Color.DARK_GRAY);
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 11));
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.setBounds(59, 286, 50, 34);
		panel.add(btnAgregar);
		btnAgregar.setBorder(null);

		incompatibles.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		        	eliminado = incompatibles.getSelectedIndex();
		        }
		    }
		});
		
		JButton btnDescartar = new JButton("");
		btnDescartar.setBorder(null);
		btnDescartar.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/eliminar.png")));
		btnDescartar.setForeground(Color.DARK_GRAY);
		btnDescartar.setFont(new Font("Arial", Font.BOLD, 11));
		btnDescartar.setBackground(Color.WHITE);
		btnDescartar.setBounds(384, 284, 50, 41);
		panel.add(btnDescartar);
		
		btnDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(eliminado);

				if (eliminado != null && eliminado != -1) {		
					
					((DefaultTableModel) table.getModel()).removeRow(eliminado);
					
					_empresa.getEmpleadosEnemistados().remove(_empresa.getEmpleadosEnemistados().get(eliminado));
					modeloString.remove(eliminado);
					incompatibles.setModel(modeloString);
					
					cargarDisponibles();

				}
			}
		});
	
		JButton btnContinuar = new JButton("Continuar >>");
		btnContinuar.setForeground(Color.DARK_GRAY);
		btnContinuar.setFont(new Font("Arial", Font.BOLD, 11));
		btnContinuar.setBackground(Color.WHITE);
		btnContinuar.setBounds(541, 297, 109, 23);
		panel.add(btnContinuar);
		
		if (_frame.getEquipo().get_limiteEmpleados() == null) {
			btnContinuar.setVisible(false);
		}
		
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (_frame.getEquipo().get_limiteEmpleados() != null) {
					setVisible(false);
					_frame.resultado();
				}
			}
		});
		btnContinuar.setForeground(Color.DARK_GRAY);
		btnContinuar.setFont(new Font("Arial", Font.BOLD, 11));
		btnContinuar.setBackground(Color.WHITE);
		btnContinuar.setBounds(541, 297, 109, 23);
		panel.add(btnContinuar);

		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/IconoSalir.png")));
		btnVolver.setBorder(null);
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(618, 11, 41, 41);
		panel.add(btnVolver);
		
		if (_frame.getEquipo().get_limiteEmpleados() == null) {
			btnVolver.setVisible(false);
		}
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (_frame.getEquipo().get_limiteEmpleados() != null) {
					remover();
					_frame.registro();
				} 
			}
		});
	
		
		JButton btnRegistrar = new JButton("");
		btnRegistrar.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/agregar.png")));
		btnRegistrar.setForeground(Color.DARK_GRAY);
		btnRegistrar.setFont(new Font("Arial", Font.BOLD, 11));
		btnRegistrar.setBackground(Color.WHITE);
		btnRegistrar.setBounds(518, 481, 41, 34);
		panel.add(btnRegistrar);
		btnRegistrar.setBorder(null);

		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tupla<Empleado,Empleado> parIncompatible = datosIngresados();
				if (parIncompatible != null) {
					if (_empresa.getEmpleadosEnemistados().contains(parIncompatible)) {
						JOptionPane.showMessageDialog(null, "Error: el par ya está en la lista");
					} else {
						
						_empresa.agregarEnemistad(parIncompatible.getFirst(), parIncompatible.getSecond());
						modeloString.addElement(parIncompatible.getFirst().getNombre() + " y " + parIncompatible.getSecond().getNombre());
						incompatibles.setModel(modeloString);

						temporal.clear();
						cargarDisponibles();
						
						txtNombreEmp1.setText("");
						txtRolEmp1.setText("");
						txtCalificacionEmp1.setText("");
						txtNombreEmp2.setText("");
						txtRolEmp2.setText("");
						txtCalifEmp2.setText("");
					}
				}
			}
		});
	
		JButton btnEliminar = new JButton("");
		btnEliminar.setBorder(null);
		btnEliminar.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(518, 526, 41, 34);
		panel.add(btnEliminar);
		
		JButton btnInicio = new JButton("");
		btnInicio.setIcon(new ImageIcon(Incompatibilidad.class.getResource("/imagenes/inicio.png")));
		btnInicio.setBorder(null);
		btnInicio.setBackground(Color.WHITE);
		btnInicio.setBounds(661, 11, 41, 41);
		panel.add(btnInicio);
		
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.inicio();
			}
		});
		

		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tupla<Empleado,Empleado> parIncompatible = datosIngresados();
				if (!_empresa.getEmpleadosEnemistados().contains(parIncompatible)) {
					JOptionPane.showMessageDialog(null, "Error : el par no se encuentra en la lista");

				}
				if (parIncompatible != null && _empresa.getEmpleadosEnemistados().contains(parIncompatible)) {
					
					_empresa.getEmpleadosEnemistados().remove(parIncompatible);
					modeloString.removeElement(parIncompatible.getFirst().getNombre() + " y " + parIncompatible.getSecond().getNombre());
					incompatibles.setModel(modeloString);
					
					cargarDisponibles();
					
					JOptionPane.showMessageDialog(null, "Par removido existosamente");
					
					txtNombreEmp1.setText("");
					txtRolEmp1.setText("");
					txtCalificacionEmp1.setText("");
					txtNombreEmp2.setText("");
					txtRolEmp2.setText("");
					txtCalifEmp2.setText("");
				} 
			}
		});
		

	}
	
	protected Tupla<Empleado, Empleado> datosIngresados() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("arquitecto");
		roles.add("programador");
		roles.add("tester");
		roles.add("jefe de equipo");
		
		String nombre = txtNombreEmp1.getText();
		String rol = txtRolEmp1.getText();
		String calificacion = txtCalificacionEmp1.getText();
		
		String nombre2 = txtNombreEmp2.getText();
		String rol2 = txtRolEmp2.getText();
		String calificacion2 = txtCalifEmp2.getText();
		
		//VERIFICACION CASILLERO VACIO
		if (nombre.equals("") || rol.equals("") || calificacion.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: ventana vacía");
			return null;
		}
		if (nombre2.equals("") || rol2.equals("") || calificacion2.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: ventana vacía");
			return null;
		}
		
		//VERIFICAION CALIFICACIONES
		if (Integer.parseInt(calificacion) < 1 || Integer.parseInt(calificacion) > 5) {
			JOptionPane.showMessageDialog(null, "Error: calificación inválida, el intervalo permitido es [1,5]");
			return null;
		}
		if (Integer.parseInt(calificacion2) < 1 || Integer.parseInt(calificacion2) > 5) {
			JOptionPane.showMessageDialog(null, "Error: calificación inválida, el intervalo permitido es [1,5]");
			return null;
		}
	
		//VERIFICACION ROLES
		if (!roles.contains(rol.toLowerCase()) || !roles.contains(rol2.toLowerCase())) {
			JOptionPane.showMessageDialog(null, "Error: el rol ingresado no es válido");
			return null;
		}
		
		Empleado emp1 = new Empleado(nombre,Integer.parseInt(calificacion),rol.toLowerCase());
		Empleado emp2 = new Empleado(nombre2,Integer.parseInt(calificacion2),rol2.toLowerCase());
		
		if (emp1.equals(emp2)) {
			JOptionPane.showMessageDialog(null, "Error: un empleado no puede ser compatible con uno mismo");
			return null;
		}
		
		Tupla<Empleado,Empleado> parIncompatible = new Tupla<Empleado,Empleado>(emp1,emp2);
		return parIncompatible;
	}

	private void cargarLista() {
		if (_empresa.getEmpleadosPosibles().size() > 0) {
			for (Empleado empleado : _empresa.getEmpleadosPosibles()) {
				modelo.addElement(empleado);
				registrados.setModel(modelo);
			}
		}	
		
		if (_empresa.getEmpleadosEnemistados().size() > 0) {
			for (Tupla<Empleado, Empleado> empleado : _empresa.getEmpleadosEnemistados()) {
				modeloString.addElement(empleado.getFirst().getNombre()+ " y "+empleado.getSecond().getNombre());
				incompatibles.setModel(modeloString);
			}
		}	
	}
	
	
	public void cargarDisponibles() {
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(null, new String[] {"Nombre", "Rol", "Calificaci\u00F3n"}));
		scrollPane_2.setViewportView(table);
		
		LinkedList<Tupla<Empleado, Empleado>> empleados = _empresa.getEmpleadosEnemistados(); 
		if(empleados !=null && empleados.size() > 0) {
			for (Tupla<Empleado, Empleado> empleado : empleados) {
				agregarFilaEnTabla(empleado.getFirst());
				agregarFilaEnTabla(empleado.getSecond());
			}
		}
		
	}

	private void agregarFilaEnTabla(Empleado empleado) {
		((DefaultTableModel) table.getModel()).addRow(new String[] {empleado.getNombre(), empleado.getRol(),""+empleado.getCalificacion()});
	}
	
	protected void remover() {
		_frame.remove(this);
		_frame.repaint();
	}
	
	public static void crear(Interfaz frame, Empresa empresa) {
		new Incompatibilidad(frame, empresa);
	}
}