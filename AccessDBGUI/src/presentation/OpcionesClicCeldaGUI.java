package presentation;
import proyecto.Pedido;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OpcionesClicCeldaGUI extends JFrame {

	private JPanel contentPane;
	private MostrarDatosFilaGUI2 mostrar;
	private ChangeValueGUI change;
	private boolean changeDone;
	/**
	 * Create the frame.
	 */
	public boolean changed() {
		return changeDone;
	}
	
	public OpcionesClicCeldaGUI(String url,int i, int j, String columna,String valorOld,String id, Pedido pedido,TableGUI guiCentral) {
		setTitle("Elige una opci\u00F3n");
		setResizable(false);
		changeDone = false;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("Cambiar Valor Celda");
		if(j == 0) btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(change == null) {
				change = new ChangeValueGUI(url,columna,
						id,valorOld,guiCentral);
				change.setVisible(true);
				}
				else {
					change.setVisible(true);
				}
				setVisible(false);
//				JOptionPane.showMessageDialog(null, "Recuerda volver a darle a Buscar para recargar la tabla", "Recargar", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(20, 106, 195, 50);
		contentPane.add(btnNewButton);
		if(change != null) changeDone = change.changed();
		JButton btnVerFila = new JButton("Ver fila");
		btnVerFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mostrar == null) {
					mostrar = new MostrarDatosFilaGUI2(pedido);//pedido.id,
					//pedido.dni,pedido.nombre,pedido.telefono,pedido.fechaEntrega,pedido.pedido,pedido.urgencia);
					mostrar.setVisible(true);
				}
				else {
					mostrar.setVisible(true);
				}
				setVisible(false);
			}
		});
		btnVerFila.setBounds(257, 106, 177, 50);
		contentPane.add(btnVerFila);
		
		JLabel lblNewLabel = new JLabel("\u00BFQu\u00E9 quieres hacer?");
		lblNewLabel.setBounds(164, 26, 151, 35);
		contentPane.add(lblNewLabel);
	}
}
