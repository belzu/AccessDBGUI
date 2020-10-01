package presentation;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import proyecto.BusinessLogic;
import proyecto.Conexion;
import proyecto.Pedido;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class AddPedidoGUI2 extends JFrame {

	private JPanel contentPane;
	private List<String> nombreColumnas;
	private List<String> valorColumnas;
	private BusinessLogic bl;
	private JTextField selectedParameterValue;
	private JComboBox<String> comboParameterNames;
	private JLabel lblValorDe;
	private JLabel selectedParameterName;
	private JButton btnAinadirPedido;
	private Conexion dbManagerAddPedido;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private Pedido p;
	
	private List<String[]> variablesAndValues;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPedidoGUI2 frame = new AddPedidoGUI2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public AddPedidoGUI2(String url,TableGUI guiCentral) {
		System.out.println(url);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		bl = new BusinessLogic(url);
		
		System.out.println(bl.getConexion().getUrl());
		nombreColumnas=new ArrayList<String>();
		try {
			nombreColumnas = bl.getNomColumnas();
		}
		catch(Exception ex) { System.out.println(ex.getMessage());}
		
		
		setBounds(100, 100, 464, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		p = new Pedido();
		
		dbManagerAddPedido = new Conexion(url);
		int numColumnas = bl.getNomColumnas().size();
		
		String[] columnas = new String[numColumnas-1];
		for(int i = 1; i < numColumnas; i++) {
			if(!bl.getNomColumnas().get(i).equals("Id")) {
//				p.addColumnWithValue(comboParameterNames.getSelectedItem().toString(),"");
				columnas[i-1] = bl.getNomColumnas().get(i);
			}
		}
	
		DefaultComboBoxModel<String> cbContent = new DefaultComboBoxModel<String>(columnas);
		this.comboParameterNames = new JComboBox<String>(cbContent);

		
		this.setTitle("Añadir pedido");
		
		lblValorDe = new JLabel("Valor de");
		
		selectedParameterName = new JLabel(this.comboParameterNames.getSelectedItem().toString());
		
		selectedParameterValue = new JTextField();
		selectedParameterValue.setColumns(10);
		
		JButton btnParamValue = new JButton("A\u00F1adir");
		//btnParamValue.setSelected(true);
		//setContentPane(contentPane);
		getRootPane().setDefaultButton(btnParamValue);
		btnParamValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				p.addColumnWithValue(comboParameterNames.getSelectedItem().toString(),selectedParameterValue.toString());
				textPane.setText(textPane.getText().toString()+"\n"+comboParameterNames.getSelectedItem().toString() + ":\t" +selectedParameterValue.getText().toString());
				//p.set(comboParameterNames.getSelectedIndex(), selectedParameterValue.getText().toString());
				p.addColumnWithValue(comboParameterNames.getSelectedItem().toString(), selectedParameterValue.getText().toString());
				
				System.out.println(p.size());
				System.out.println(numColumnas);
				
				if(comboParameterNames.getSelectedIndex() < (numColumnas-2)) comboParameterNames.setSelectedItem(comboParameterNames.getItemAt(comboParameterNames.getSelectedIndex()+1));
				selectedParameterValue.setText("");
				
				//Si el numero de [caracteristica del pedido, valor de dicha caracteristica]-s ==(numColumnas sin el dni) podemos ainadir el pedido
				if(p.size() == (numColumnas-1)) btnAinadirPedido.setEnabled(true);
			}
		});
		
		//Cambia el texto que nos dice que parametro del pedido estamos configurando
		comboParameterNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedParameterName.setText(comboParameterNames.getSelectedItem().toString()); 
			}
		});
		
		btnAinadirPedido = new JButton("Insertar Pedido");
		
		btnAinadirPedido.setEnabled(false);
		btnAinadirPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(p.get(0, 1));
					if(p == null) System.out.println("Esto vale null");
					dbManagerAddPedido.insertarPedido(p);
					guiCentral.btnBuscar.doClick();
					btnAinadirPedido.setEnabled(false);
					p = new Pedido();
					textPane.setText("");
					comboParameterNames.setSelectedItem(comboParameterNames.getItemAt(0));
					selectedParameterName.setText(comboParameterNames.getItemAt(0).toString());
					selectedParameterValue.setText("");
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR al introducir los datos en la base de datos\n" + ex.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				finally {
					setVisible(false);
				}
			}
		});
		
		scrollPane = new JScrollPane();
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(lblValorDe, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(selectedParameterName, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(selectedParameterValue, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(133)
					.addComponent(btnAinadirPedido)
					.addContainerGap(198, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnParamValue, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
							.addGap(28)
							.addComponent(comboParameterNames, 0, 140, Short.MAX_VALUE)
							.addGap(117))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(selectedParameterValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectedParameterName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblValorDe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboParameterNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnParamValue, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addGap(39)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAinadirPedido)
					.addContainerGap())
		);
		
		scrollPane.setViewportView(textPane);
		contentPane.setLayout(gl_contentPane);
		

	}
}
