package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Auxiliares.Empleado;

@SuppressWarnings("serial")
public class Resultado extends JLayeredPane {
	private Interfaz _frame;
	private JTable table;
	private DefaultTableModel tableModel;

	public Resultado(Interfaz frame) {
		frame.getContentPane().add(this);
		_frame = frame;

		setBackground(Color.WHITE);
		setBounds(0, 0, 854, 472);	
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 11, 833, 434);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelResultados = new JPanel();
		panelResultados.setBackground(Color.WHITE);
		panelResultados.setBounds(149, 0, 684, 434);
		panel.add(panelResultados);
		panelResultados.setLayout(null);
		
		JLabel lblResultado = new JLabel("");
		lblResultado.setForeground(Color.DARK_GRAY);
		lblResultado.setFont(new Font("Arial", Font.BOLD, 14));
		lblResultado.setBounds(10, 30, 449, 23);
		panelResultados.add(lblResultado);
		
		botones(panel, panelResultados, lblResultado);
		
		
		JPanel panelEstadisticas = new JPanel();
		panelEstadisticas.setLayout(null);
		panelEstadisticas.setBackground(Color.WHITE);
		panelEstadisticas.setBounds(147, 0, 686, 434);
		panel.add(panelEstadisticas);
		panelEstadisticas.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Solucion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBounds(0, 11, 144, 23);
		panel.add(lblNewLabel);
	}

	private void botones(JPanel panel, JPanel panelResultados, JLabel lblResultado) {
		JButton btnAlgoritmo = new JButton("FB/Backtracking");
		btnAlgoritmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText("El mejor equipo mediante fuerza bruta/backtracking");
				_frame.getEquipo().generarEquipo();
				cargarDisponibles(panelResultados);
			}
		});
		btnAlgoritmo.setBackground(Color.WHITE);
		btnAlgoritmo.setForeground(Color.DARK_GRAY);
		btnAlgoritmo.setFont(new Font("Arial", Font.BOLD, 11));
		btnAlgoritmo.setBounds(14, 43, 125, 23);
		panel.add(btnAlgoritmo);
		
		JButton btnHeuristica = new JButton("Heuristica");
		btnHeuristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText("El mejor equipo mediante Heuristica");
				_frame.getEquipo().buscarSolucionHeuristica();
				cargarDisponibles(panelResultados);
			}
		});
		btnHeuristica.setFont(new Font("Arial", Font.BOLD, 11));
		btnHeuristica.setForeground(Color.DARK_GRAY);
		btnHeuristica.setBackground(Color.WHITE);
		btnHeuristica.setBounds(14, 77, 125, 23);
		panel.add(btnHeuristica);
		
		JButton btnInicio = new JButton("");
		btnInicio.setIcon(new ImageIcon(Resultado.class.getResource("/imagenes/inicio.png")));
		btnInicio.setBorder(null);
		btnInicio.setBackground(Color.WHITE);
		btnInicio.setBounds(621, 12, 41, 41);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
				_frame.inicializar();
				_frame.inicio();
			}
		});
		panelResultados.add(btnInicio);
		
		JButton btnEstadisticas = new JButton("");
		btnEstadisticas.setIcon(new ImageIcon(Resultado.class.getResource("/imagenes/estadistica.png")));
		btnEstadisticas.setBorder(null);
		btnEstadisticas.setBackground(Color.WHITE);
		btnEstadisticas.setBounds(621, 366, 41, 41);
		panelResultados.add(btnEstadisticas);
		
		JButton btnVolver = new JButton("");
		btnVolver.setBounds(570, 12, 41, 41);
		panelResultados.add(btnVolver);
		btnVolver.setIcon(new ImageIcon(Resultado.class.getResource("/imagenes/IconoSalir.png")));
		btnVolver.setBorder(null);
		btnVolver.setBackground(Color.WHITE);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					remover();
					_frame.incompatibilidad();
				} 
		});
	}
	
	public void cargarDisponibles(JPanel panelResultados) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 70, 508, 201);
		panelResultados.add(scrollPane);
		
		String informacion[] = {"Nombre", "Rol", "Calificaci\u00F3n"};
		tableModel = new DefaultTableModel(null,informacion);
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		table.setModel(tableModel);
		table.setEnabled(false);
		
		
		agregarFila();
		/*
		TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(elQueOrdena);
		agregarFila();
		
		table.getRowSorter().toggleSortOrder(1);
		*/
		
	}

	/**Carga datos guardados creando nuevas filas en la tabla*/
	private void agregarFila() {
		ArrayList<Empleado> empleados = _frame.getEquipo().getEquipoIdeal(); 
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
	
	public static void crear(Interfaz frame) {
		new Resultado(frame);
	}
}
