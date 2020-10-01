package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proyecto.BusinessLogic;
import proyecto.Conexion;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeValueGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private BusinessLogic dbManagerChangeValue;
	private boolean changeDone;
	
	public boolean changed() {
		return changeDone;
	}

	/**
	 * Create the frame.
	 */
	public ChangeValueGUI(String url,String valorOld, String id, String nombreColumna,TableGUI guiCentral) {
		setResizable(false);
		setTitle("Cambiar valor");
		changeDone = false;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		dbManagerChangeValue = new BusinessLogic(url);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblCambiarValorDe = new JLabel("Cambiar valor de");
		
		JLabel lblValorViejo = new JLabel(valorOld);

		JLabel lblA = new JLabel("a");
		
		JButton btnCambiarValor = new JButton("Cambiar valor");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCambiarValorDe, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblValorViejo, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblA, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(161)
							.addComponent(btnCambiarValor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(184)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCambiarValorDe)
							.addComponent(lblValorViejo)
							.addComponent(lblA)))
					.addGap(34)
					.addComponent(btnCambiarValor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(25))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		btnCambiarValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.out.println(id + " " + nombreColumna);
					int reply = JOptionPane.showConfirmDialog(null, "¿Seguro de que quieres realizar los cambios?"
							, "¿Cambiar?",  JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.NO_OPTION)
					{
						setVisible(false);
					}
					else {
						try {
							dbManagerChangeValue.cambiarValor(valorOld, textField.getText().toString(), id, nombreColumna);
							guiCentral.btnBuscar.doClick();
						}
						catch(Exception er) {
							
						}
						finally {
							changeDone = true;
							setVisible(false);
						}
					}				
					
			}
		});
	}
}
