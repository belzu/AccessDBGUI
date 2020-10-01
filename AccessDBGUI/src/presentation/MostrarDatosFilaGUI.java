package presentation;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class MostrarDatosFilaGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public MostrarDatosFilaGUI(String id, String dni,String nombre, String nTelef, String fechaEntrega, String pedido, String urgencia) {
		setBounds(100, 100, 546, 414);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		

		
		JTextPane textPane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
		);
//		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		String idd = "ID:	"+id+"\n";
		String nombree = "Nombre:	"+nombre+"\n";
		String fechaEntregaa = "Fecha entrega:	"+fechaEntrega+ "\n";
		String pedidoo = "Pedido:	"+pedido+"\n";
		String urgenciaa = "Urgencia:	"+urgencia +"\n";
		String numTeleff = "Numero de telefono:	"+nTelef+" \n";
		String dnii = "DNI:	"+dni+" \n";
		textPane.setText(idd+nombree+dnii+numTeleff+fechaEntregaa+pedidoo+urgenciaa);
		
	}
}
