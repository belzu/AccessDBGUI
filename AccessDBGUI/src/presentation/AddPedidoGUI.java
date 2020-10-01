package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import proyecto.Conexion;
import proyecto.Pedido;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddPedidoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNumTel;
	private JTextField txtDni;
	private JTextField txtAno;
	private JTextField txtMes;
	private JTextField txtDia;
	private JTextField txtPedido;
	private Conexion dbManagerAddPedido;
	private ButtonGroup grupo1;

	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddPedidoGUI frame = new AddPedidoGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddPedidoGUI() {
		setResizable(false);
		setTitle("A\u00F1adir pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dbManagerAddPedido = new Conexion();
		
		grupo1  = new ButtonGroup();

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(25, 33, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNumeroDeTelfono = new JLabel("Numero de tel\u00E9fono");
		lblNumeroDeTelfono.setBounds(25, 71, 113, 14);
		contentPane.add(lblNumeroDeTelfono);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(25, 107, 62, 14);
		contentPane.add(lblDni);
		
		JLabel lblFechaDeEntrega = new JLabel("Fecha de entrega:");
		lblFechaDeEntrega.setBounds(25, 145, 113, 14);
		contentPane.add(lblFechaDeEntrega);
		
		JLabel lblAno = new JLabel("A\u00F1o");
		lblAno.setBounds(148, 145, 46, 14);
		contentPane.add(lblAno);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(266, 145, 46, 14);
		contentPane.add(lblMes);
		
		JLabel lblDia = new JLabel("D\u00EDa");
		lblDia.setBounds(442, 145, 46, 14);
		contentPane.add(lblDia);
		
		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setBounds(25, 195, 46, 14);
		contentPane.add(lblPedido);
		
		JLabel lblUrgencia = new JLabel("Urgencia");
		lblUrgencia.setBounds(25, 237, 83, 14);
		contentPane.add(lblUrgencia);
		
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.setBounds(244, 286, 111, 45);
		contentPane.add(btnGuardar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(159, 30, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtNumTel = new JTextField();
		txtNumTel.setBounds(159, 68, 86, 20);
		contentPane.add(txtNumTel);
		txtNumTel.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(159, 104, 86, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtAno = new JTextField();
		txtAno.setBounds(186, 142, 62, 20);
		contentPane.add(txtAno);
		txtAno.setColumns(10);
		
		txtMes = new JTextField();
		txtMes.setBounds(307, 142, 86, 20);
		contentPane.add(txtMes);
		txtMes.setColumns(10);
		
		txtDia = new JTextField();
		txtDia.setBounds(484, 142, 86, 20);
		contentPane.add(txtDia);
		txtDia.setColumns(10);
		
		txtPedido = new JTextField();
		txtPedido.setBounds(159, 192, 86, 20);
		contentPane.add(txtPedido);
		txtPedido.setColumns(10);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(179, 233, 52, 23);
		contentPane.add(rdbtnNo);
		
		JRadioButton rdbtnSi = new JRadioButton("S\u00ED");
		rdbtnSi.setBounds(114, 233, 63, 23);
		contentPane.add(rdbtnSi);
		
		grupo1.add(rdbtnSi);
		grupo1.add(rdbtnNo);
		
		JButton btnBorrarTodo = new JButton("Borrar todo");

		btnBorrarTodo.setBounds(374, 33, 149, 65);
		contentPane.add(btnBorrarTodo);
		
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txtFechaEntrega = txtMes.getText()+"/"+txtDia.getText()+"/"+txtAno.getText();
				String txtUrgencia;
				if(rdbtnSi.isSelected()) txtUrgencia = "True";
				else txtUrgencia = "False";
				Pedido p = new Pedido("0",txtNombre.getText(),txtDni.getText(),txtNumTel.getText(),txtFechaEntrega,txtPedido.getText(),
						txtUrgencia);			
				dbManagerAddPedido.insertarPedido(p);
				setVisible(false);
				
			}
		});
		
		
		
		btnBorrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAno.setText("");
				txtDia.setText("");
				txtDni.setText("");
				txtMes.setText("");
				txtNombre.setText("");
				txtNumTel.setText("");
				txtPedido.setText("");
				rdbtnNo.setSelected(false);
				rdbtnSi.setSelected(false);
			}
		});
	}
}
