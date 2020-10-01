package presentation;
import proyecto.Pedido;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class MostrarDatosFilaGUI2 extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public MostrarDatosFilaGUI2(Pedido p) {//String id, String dni,String nombre, String nTelef, String fechaEntrega, String pedido, String urgencia){
		setResizable(false);	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 574, 405);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textPane);
		String texto = new String("");
		
		for(int i = 0; i<p.size();i++){
			texto+=p.get(i, 0)+": "+p.get(i, 1)+"\n"+"\n";	
		}
		
		
//		ESTO ES LO QUE ESTABA ANTES
//		String idd = "ID:	"+id+"\n"+"\n";
//		String nombree = "Nombre:	"+nombre+"\n"+"\n";
//		String fechaEntregaa = "Fecha entrega:	"+fechaEntrega+ "\n"+"\n";
//		String pedidoo = "Pedido:	"+pedido+"\n"+"\n";
//		String urgenciaa = "Urgencia:	"+urgencia +"\n"+"\n";
//		String numTeleff = "Numero de telefono:	"+nTelef+" \n"+"\n";
//		String dnii = "DNI:	"+dni+" \n"+"\n";
//		textPane.setText(idd+nombree+dnii+numTeleff+fechaEntregaa+pedidoo+urgenciaa);
		
		textPane.setText(texto);
	}
	
}
