package proyecto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
//	public String id;
//	public String nombre;
//	public String dni;
//	public String telefono;
//	public String fechaEntrega;
//	public String pedido;		
//	public String urgencia;
	
	
	private List<String[]> variablesAndValues;
	
	public Pedido(List<String[]> variablesYValores){//String id, String nombre, String dni, String telefono,String fechaEnt, String pedido, String urgencia){
//		this.id = id;
//		this.nombre = nombre;
//		this.telefono = telefono;
//		this.dni = dni;
//		this.fechaEntrega = fechaEnt;
//		this.pedido = pedido;
//		this.urgencia = urgencia;
		
		//variablesAndValues contiene las variables y los valores. Contiene una lista dinamica de arrays de dos elementos: nombre de la variable y su valor
//		this.variablesAndValues = new ArrayList<String[]>();
		this.variablesAndValues = variablesYValores;
	}
	public Pedido() {
		this.variablesAndValues = new ArrayList<String[]>();
	}
	
	
	//It adds a new column with its respective value in case.
	//In case the column is already there, it just updates the column's value
	public void addColumnWithValue(String column, String value) {
		String[] valores = {column,value};
		boolean found = false;
		for(int i = 0; i<variablesAndValues.size();i++) {
			if(variablesAndValues.get(i)[0].equals(column)) {
				System.out.println("The column " + column + " has been updated\n");
				variablesAndValues.get(i)[1] = value;
				found = true;
				break;
			}
		}
		if(!found) variablesAndValues.add(valores);
	}
	
	public String[] get(int i) {
		return variablesAndValues.get(i);
	}
	public String get(int i,int j) {
		return variablesAndValues.get(i)[j];
	}
	public void set(int i, String j) {
		variablesAndValues.set(i, new String[]{variablesAndValues.get(i)[0],j});
	}
	public int size() {
		return variablesAndValues.size();
	}
}
