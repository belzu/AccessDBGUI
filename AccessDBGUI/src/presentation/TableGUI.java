package presentation;
import proyecto.BusinessLogic;
import java.util.List;
import java.util.ArrayList;
import proyecto.Pedido;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import proyecto.Conexion;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;
public class TableGUI extends JFrame implements ActionListener{

	/* THIS PROJECT WAS CREATED BY IGNACIO BELZUNEGUI
	 * 
	 * 
	 * 
	 */
	
	
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTextField txtEliminar;
	private JTable table;
	private List<Pedido> listaPedidos;
	private BusinessLogic bL;
	private AddPedidoGUI2 ainadirPedido;
	private OpcionesClicCeldaGUI opciones;
	private String dbName;
	
	JButton btnBuscar;
	
	
	//En el contexto de la aplicacion, pedido se refiere a una nueva instancia/fila
	
	
	/*
	DefaultTableModel model = new DefaultTableModel(); 
	JTable table = new JTable(model); 

	// Create a couple of columns 
	model.addColumn("Col1"); 
	model.addColumn("Col2"); 

	// Append a row 
	model.addRow(new Object[]{"v1", "v2"});
	
	
	
	/**
	 * Launch the application.
	 */
	
	
	
	//--- BEGIN MANERA 1 PARA EXPLORAR CARPETAS ---
	/*
    try {
    	
      JFileChooser fileChooser = new JFileChooser();
      
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      
      int returnVal = fileChooser.showOpenDialog(AddPedidoGUI2.this);
      
      if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          //This is where a real application would open the file.
          System.out.println("Opening: " + file.getName());
          System.out.println("File chosen: " + file.getAbsolutePath());
      }

     

	    
    }
    catch (Exception ex){}
    */
	
	
	//--- END MANERA 1 PARA EXPLORAR CARPETAS---
	
	
	// --- BEGIN MANERA 1 PARA EXPLORAR ARCHIVOS Y OBTENER SU PATH (ENPLAN BIEN) ---
	/*
	FileDialog fd = new FileDialog(new JFrame());
	fd.setVisible(true);
	File[] f = fd.getFiles();
	if(f.length > 0){
	    System.out.println(fd.getFiles()[0].getAbsolutePath());
	}
	*/
	// --- END MANERA 1 PARA EXPLORAR ARCHIVOS Y OBTENER SU PATH (ENPLAN BIEN) ---
	
	//Muestra la tabla con los pedidos
	public void mostrarTabla() {	
		
		for(int i = 0; i<listaPedidos.size();i++) {		
//		
// --- BEGIN COMMENT ---
//			DefaultTableModel model = (DefaultTableModel) table.getModel();
//			for(int j = 0; j<listaPedidos.get(i).size();j++) {
//				model.addRow(new Object[]{(String[])listaPedidos.get(i).get(j).toArray(new String[0])});
////				System.out.println(Arrays.toString(subset1white));
////				model.addRow(new Object[]{(String[])listaPedidos.get(i).get(j,1)});
//			}
////			model.addRow(new Object[]{listaPedidos.get(i).id, listaPedidos.get(i).nombre, listaPedidos.get(i).dni,
////					listaPedidos.get(i).telefono,listaPedidos.get(i).fechaEntrega,listaPedidos.get(i).pedido,listaPedidos.get(i).urgencia});
// --- END COMMENT ---		
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[]{});
			for(int j = 0; j<listaPedidos.get(i).size();j++) {
				
				
				System.out.println(model.getRowCount() + " " + model.getColumnCount());
				
				
				 Object row = listaPedidos.get(i).get(j, 1);
				 
				 
				 System.out.println("model value " + model.getValueAt(i, j));
				 model.setValueAt(row,i,j);
				
				
//			VIEJO
//			//DefaultTableModel model = (DefaultTableModel) table.getModel();
//		    //model.addRow(new Object[]{"id", "Nombre", "DNI", "Telefono", "Direccion", "Pedido", "Urgencia"});
			}
		}
	}
	
	
	public void eliminarTabla() {
		//Borrar todas las filas de una tabla
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table / Borrar las lineas una por una desde el final de la tabla
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
//	public void OrdenarDeMenAMayor(String id){		
//		for(int i = 0; i < listaPedidos.size();i++) {
//			int menor = Integer.parseInt(listaPedidos.get(i).id);
//			for(int j = i; j < listaPedidos.size();j++) {
//				if(Integer.parseInt(listaPedidos.get(j).id) < menor) {
//					int auxInt = menor;
//					listaPedidos.set(menor,listaPedidos.get(j));
//					
//					listaPedidos.set(j, listaPedidos.get(menor));
//					menor = Integer.parseInt(listaPedidos.get(j).id);
//					
//				}
//				
//			}
//			
//			
//		}		
//	}
	
	public void actionPerformed(ActionEvent e) {
		if(opciones!=null) {
			if(opciones.changed()) mostrarTabla();
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableGUI frame = new TableGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	/**
	 * Create the frame.
	 */
	public TableGUI() {
		//bL = new BusinessLogic();
		bL = new BusinessLogic(true);
		String url = bL.getConexion().getUrl();
		
		
		setTitle("Interfaz de conexión con la base de datos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAinadir = new JButton("Nuevo pedido");
		
		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		
		//Este boton tiene algo especial que no tienen el resto, y es que es capaz de actualizar la base de datos en tiempo real
		btnBuscar = new JButton("Buscar");
		
		txtEliminar = new JTextField();
		txtEliminar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Eliminar fila con");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		table = new JTable(0,7);
		table.setCellSelectionEnabled(false);
		table.setEnabled(true);
//		table.setSelectionMode(DefaultListModel.SINGLE_SELECTION);
		
		
		
		
		TableGUI guiCentral  = this;
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//btnBet.setEnabled(true);
				int i = table.getSelectedRow();
				int j = table.getSelectedColumn();
				
				//Le pasamos el valor viejo, el id de la columna que cambiaremos, y el nombre de la columna que cambiaremos
				System.out.println("has hecho clic en la fila: "+ i);
				System.out.println("valor de la celda en la que has hecho clic "+ table.getValueAt(i, j) );			
				
				if(opciones == null) {
				
					//Si se ha añadido una columna mas a una base de datos, se actualizan las filas preexistentes a dicho cambio
					if(table.getValueAt(i,j) == null) {
						table.setValueAt("", i, j);
						listaPedidos.get(i).addColumnWithValue(table.getColumnName(j), null);
					}
					
					opciones = new OpcionesClicCeldaGUI(url,i,j,table.getValueAt(i, j).toString(),
								table.getColumnName(j),table.getValueAt(i,0).toString(),
								listaPedidos.get(i),guiCentral);
						opciones.setVisible(true);
				}
				else {
					//Si se ha añadido una columna mas a una base de datos, se actualizan las filas preexistentes a dicho cambio
					if(table.getValueAt(i,j) == null) {
						table.setValueAt("", i, j);
						listaPedidos.get(i).addColumnWithValue(table.getColumnName(j), null);
					}
					
					opciones.setVisible(false);
					opciones = new OpcionesClicCeldaGUI(url,i,j,table.getValueAt(i, j).toString(),
							table.getColumnName(j),table.getValueAt(i,0).toString(),
							listaPedidos.get(i),guiCentral);
					opciones.setVisible(true);
				}

				
				
				
				
//				ChangeValueGUI change = new ChangeValueGUI(table.getValueAt(i, j).toString(),
//						table.getValueAt(i,0).toString(),table.getColumnName(j));
//				change.setVisible(true);
				
				//table.setValueAt(aValue, i, j);
				//selectedPayoff = (PayoffContainer) tableModelPayoffs.getValueAt(i,2);
			}
		});
		
//		int numColumnas = bL.getNomColumnas().size();
//		String[] columnas = new String[numColumnas];
//		
//		for(int i = 0; i < numColumnas; i++) columnas[i] = bL.getNomColumnas().get(i);
//		
//		table.setModel(new DefaultTableModel(new Object[][] {
//		}, columnas				
//		));
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			
			//Esto es lo que hace que se vean los nombres de las columnas en la tabla
			(String[])bL.getColumnsNames().toArray(new String[0])
//			ESTO ES LO QUE ESTABA ANTES
//			new String[] {
//					 
//				"id", "Nombre", "DNI", "Numero telefono", "fecha Entrega", "Pedido", "Urgencia"
//			}
			
		));
		

		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(70);
		
		//Establecer tamaño de las columnas
		for(int i = 0; i<table.getColumnCount();i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(90);
		}	
		//Establecer que todas las columnas son autoresizable
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setDefaultEditor(Object.class, null);
		

		
		JComboBox comboBox_eliminar = new JComboBox();
		
		comboBox_eliminar.setModel(new DefaultComboBoxModel((String[])bL.getColumnsNames().toArray(new String[0])));//new String[] {"id", "Nombre", "Dni", "Numero Telefono", "Direcci\u00F3n"}));
//		comboBox_eliminar.setModel(new DefaultComboBoxModel(new String[] {"id", "Nombre", "Dni", "Numero Telefono", "Direcci\u00F3n"}));
		JLabel lblEquivalenteA = new JLabel("equivalente a");
		
		JComboBox comboBox_buscar = new JComboBox();
		comboBox_buscar.setToolTipText("");
		
		comboBox_buscar.setModel(new DefaultComboBoxModel((String[])bL.getColumnsNames().toArray(new String[0])));//new String[] {"id", "Nombre", "Dni", "numtelef", "fechaEntrega"}));
//		comboBox_buscar.setModel(new DefaultComboBoxModel(new String[] {"id", "Nombre", "Dni", "numtelef", "fechaEntrega"}));
		JLabel lblSiHacesClic = new JLabel("Si haces clic en <buscar> estando el texto vac\u00EDo, mostrar\u00E1 toda la tabla");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(btnAinadir, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
							.addGap(99))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(102)
							.addComponent(comboBox_eliminar, 0, 141, Short.MAX_VALUE)))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addComponent(txtEliminar, 66, 66, 66))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEquivalenteA, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addGap(81)))
					.addGap(5))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(292)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
					.addGap(209))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblSiHacesClic, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
					.addGap(80))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(comboBox_buscar, 0, 112, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtBuscar, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
					.addGap(33)
					.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addGap(89))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAinadir, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(3))
								.addComponent(comboBox_eliminar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEliminar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblEquivalenteA, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(3)))
							.addGap(13)))
					.addGap(5)
					.addComponent(btnEliminar)
					.addGap(18)
					.addComponent(lblSiHacesClic)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox_buscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(24))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(24))
						.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
		
		btnAinadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ainadirPedido == null) {
					ainadirPedido = new AddPedidoGUI2(url,guiCentral);
					ainadirPedido.setVisible(true);	
				}
				else {
					ainadirPedido.setVisible(true);	
				}
	
			}
		});
		
		
		
		
		btnBuscar.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e){				
				//Borrar todas las filas de una tabla
//				DefaultTableModel dm = (DefaultTableModel) table.getModel();
//				int rowCount = dm.getRowCount();
//				//Remove rows one by one from the end of the table / Borrar las lineas una por una desde el final de la tabla
//				for (int i = rowCount - 1; i >= 0; i--) {
//				    dm.removeRow(i);
//				}
				eliminarTabla();
				try {//Hacer que aparezcan todos los valores de la lista en la tabla (en el for)
					listaPedidos = bL.getListaPedidos();
					if(txtBuscar.getText().equals(null) || txtBuscar.getText().equals("")) listaPedidos = bL.getListaPedidos();					
					else listaPedidos = bL.getListaPedidos(comboBox_buscar.getSelectedItem().toString() ,txtBuscar.getText());
					System.out.println(listaPedidos);
					mostrarTabla();
				}				
				catch (Exception er) {
					JOptionPane.showMessageDialog(null, "ERROR al conseguir los datos en la base de datos\n "+ er.toString(), "ERROR en GUI", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Seguro de que quieres realizar los cambios?"
						, "¿Cambiar?",  JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

					if(txtEliminar.getText().equals(null) || txtEliminar.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "Inserta el "+comboBox_eliminar.getSelectedItem().toString()
								+ " del pedido(s) que quieras eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else {
						if(bL.esta(comboBox_eliminar.getSelectedItem().toString(), txtEliminar.getText())) {
							bL.eliminarPedido(comboBox_eliminar.getSelectedItem().toString(), txtEliminar.getText());
//							txtEliminar.setText(null);
							//Borrar todas las filas de una tabla
							DefaultTableModel dm = (DefaultTableModel) table.getModel();
							int rowCount = dm.getRowCount();
							//Remove rows one by one from the end of the table / Borrar las lineas una por una desde el final de la tabla
							for (int i = rowCount - 1; i >= 0; i--) {
							    dm.removeRow(i);
							}
							try {
								listaPedidos = bL.getListaPedidos();
								if(txtBuscar.getText().equals(null) || txtBuscar.getText().equals("")) listaPedidos = bL.getListaPedidos();					
								else listaPedidos = bL.getListaPedidos(comboBox_buscar.getSelectedItem().toString() ,txtBuscar.getText());
								mostrarTabla();
							}				
							catch (Exception er) {
								JOptionPane.showMessageDialog(null, "ERROR al eliminar los datos en la base de datos\n "+ er.toString(), "ERROR en BD", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				txtEliminar.setText(null);
			}
		});

		

	}
}
