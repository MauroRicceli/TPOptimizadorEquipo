package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Auxiliares.Empleado;
import Auxiliares.Empresa;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class Disponibles extends JLayeredPane {
	private Interfaz _frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private Empresa _empresa;
	private JPanel panel;
	public Disponibles(Interfaz frame,Empresa empresa) {
		frame.getContentPane().add(this);
		_frame = frame;
		_empresa = empresa;
		setBackground(Color.WHITE);
		setBounds(0, 0, 854, 472);	
		
	
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(117, 0, 642, 472);
		add(panel);
		panel.setLayout(null);
		cargarDisponibles();
			
		JLabel lblNewLabel = new JLabel("Empleados  registrados");
		lblNewLabel.setBounds(63, 88, 206, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
			
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(63, 114, 171, 2);
		panel.add(separator);
			
			
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Disponibles.class.getResource("/imagenes/fondo.png")));
		lblNewLabel_1.setBounds(0, 0, 854, 472);
		add(lblNewLabel_1);
		botones();
	
	}

	private void botones() {
		JButton btnVolver = new JButton("");
		
		btnVolver.setBounds(579, 39, 39, 32);
		panel.add(btnVolver);
		btnVolver.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/IconoSalir.png")));
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setBorder(null);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_frame.inicio();
			}
		});
		
	}
	
	public void cargarDisponibles() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 150, 467, 228);
		panel.add(scrollPane);
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		
		table.setEnabled(false);
		
		String informacion[] = {"Nombre", "Rol", "Calificaci\u00F3n"};
		tableModel = new DefaultTableModel(null,informacion);
		table.setModel(tableModel);
		TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(elQueOrdena);
		agregarFila();
		
		table.getRowSorter().toggleSortOrder(1);
		
	}

	/**Carga datos guardados creando nuevas filas en la tabla*/
	private void agregarFila() {
		LinkedList<Empleado> empleados = _empresa.getEmpleadosPosibles(); 
		if(empleados!=null) {
			for (Empleado empleado : empleados) {
				agregarFilaEnTabla(empleado);
			}
		}
	}
	
	private void agregarFilaEnTabla(Empleado empleado) {
		tableModel.addRow(new String[] {empleado.getNombre(), empleado.getRol(),""+empleado.getCalificacion()});
		
	}

	protected void remover() {
		_frame.remove(this);
		_frame.repaint();
	}
	
	public static void crear(Interfaz frame, Empresa empresa) {
		new Disponibles(frame, empresa);
	}
}