package proyecto;

import java.awt.FileDialog;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Conexion {
	private String database = "Aprender2.accdb";// ANTES -> "BD/Aprender.accdb";
	private String username = "";
	private String password = "";
	//ANTES -> private String url = "jdbc:ucanaccess://" + System.getProperty("user.dir").replace("\\", "/") + "/" + database;
	
	private String url; //= "jdbc:ucanaccess://"+"C:\\Users\\ignacio\\Desktop\\Programas\\Proyecto access padre berni/Aprender2.accdb";
//	private List<String> columnas = getColumnNames();
	public Connection conn = null;
	
	
	public Conexion() {
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public Conexion(String url_) {
		try {
			url = url_;
			conn = DriverManager.getConnection(url_,username,password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	
	public Conexion(boolean buscar_dB) {
		if(buscar_dB) {
			FileDialog fd = new FileDialog(new JFrame());
			fd.setTitle("Seleccione la base de datos Access a la que quiera acceder");
			fd.setVisible(true);
			File[] f = fd.getFiles();
			if(f.length > 0) System.out.println(fd.getFiles()[0].getAbsolutePath());
			try {
				url = "jdbc:ucanaccess://" +fd.getFiles()[0].getAbsolutePath().replace("\\", "/");
				conn = DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	//Esta funcion obtiene el nombre de las columnas de TablaClientes
	public List<String> getColumnsNames(){
		List<String> listaResultado = new ArrayList<String>();
		Conexion conn = new Conexion(this.url);
		
		try {
			Connection connn = DriverManager.getConnection(this.url, username, password);
			DatabaseMetaData md = connn.getMetaData();
			//getTables(null, "schenaName", "%" ,new String[] {"TABLE"});
			ResultSet rss = md.getTables(null, null, "%", null);
		    String table = "";
		    while(rss.next())
		    	if(!rss.getString(3).contains("MSys")) {
		    		table = rss.getString(3);
		    		System.out.println("Hola");
		    		break;
		    	}
		    System.out.println(table);
			Statement consulta = conn.getConnection().createStatement();
			//ResultSet rs = consulta.executeQuery("SELECT * FROM TablaClientes");
			ResultSet rs = consulta.executeQuery("SELECT * FROM "+table);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnCount = rsmd.getColumnCount();
	
			// The column count starts from 1
			for (int i = 1; i <= columnCount; i++ ) {
			  String name = rsmd.getColumnName(i);
			  listaResultado.add(name);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERROR al conseguir los datos en la base de datos\n" + e.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
		}
		return listaResultado;
	}
	
	public Connection getConnection() {
		return conn;		
	}
//	
//	public String concatenarColumnas(List<String> l) {
//		String resultado = "";
//		for(int i = 0;i<columnas.size();i++) resultado = resultado + ","+columnas.get(i)+",";
//		return resultado;
//	}
//	
	
	
	public void modificarDato(String valorOld, String valorNew,String id,String nombreColumna) {
		try {
			Connection connn = DriverManager.getConnection(this.url, username, password);
			DatabaseMetaData md = connn.getMetaData();
			//getTables(null, "schenaName", "%" ,new String[] {"TABLE"});
			ResultSet rss = md.getTables(null, null, "%", null);
		    String table = "";
		    while(rss.next())
		    	if(!rss.getString(3).contains("MSys")) {
		    		table = rss.getString(3);
		    		System.out.println("Hola");
		    		break;
		    	}
		    System.out.println(table);
			
			
			Conexion conn = new Conexion(this.url);
			Statement consulta = conn.getConnection().createStatement();
			consulta.executeUpdate("UPDATE "+ table +" SET "+nombreColumna+" = '"+ valorNew + //" WHERE "+ nombreColumna+ " = "
					//+valorOld + 
					"'  WHERE  id = '"+ id+"'");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERROR al conseguir los datos en la base de datos\n" + e.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	//Te devuelve la lista de Pedidos desde la base de datos
	public List<Pedido> setListaPedidos(){
		List<Pedido> listaResultado = new ArrayList<Pedido>();
		Pedido pedidoActual = new Pedido();
		try {
			Connection connn = DriverManager.getConnection(this.url, username, password);
			DatabaseMetaData md = connn.getMetaData();
			//getTables(null, "schenaName", "%" ,new String[] {"TABLE"});
			ResultSet rss = md.getTables(null, null, "%", null);
		    String table = "";
		    while(rss.next())
		    	if(!rss.getString(3).contains("MSys")) {
		    		table = rss.getString(3);
		    		System.out.println("Hola");
		    		break;
		    	}
		    System.out.println(table);
			
			
			Conexion conn = new Conexion(this.url);
			Statement consulta = conn.getConnection().createStatement();
			ResultSet resultado = consulta.executeQuery("Select * FROM " + table);
			
			while(resultado.next()) {
				List<String> nombresColumnas = getColumnsNames();
				List<String[]> variablesAndValue = new ArrayList<String[]>();
				for(int i = 0;i<nombresColumnas.size();i++) {
					variablesAndValue.add(new String[] {nombresColumnas.get(i),resultado.getString(nombresColumnas.get(i))}); //Nos devuelve el nombre de la columna i mas su resultado
				}
				pedidoActual = new Pedido(variablesAndValue);
				listaResultado.add(pedidoActual);
				//ESTO ES LO QUE ESTABA ANTES
//				pedidoActual = new Pedido(resultado.getString("id"),resultado.getString("nombre"),resultado.getString("dni"),
//						resultado.getString("numtelef"),resultado.getString("fechaentrega"),resultado.getString("pedido"),resultado.getString("urgencia"));
//				System.out.println(resultado.getString("id")+ " " + resultado.getString("nombre")+ " " +resultado.getString("dni")+ " "+
//				resultado.getString("numtelef")+ " " + resultado.getString("fechaentrega")+ " " +resultado.getString("pedido")+ " " +resultado.getString("urgencia"));
//		listaResultado.add(pedidoActual);
				
				
				//ESTO YA ESTABA COMENTADO
//				pedidoActual = new Pedido(resultado.getString(columnas.get(index)),resultado.getString("nombre"),resultado.getString("dni"),				
//						resultado.getString("numtelef"),resultado.getString("fechaentrega"),resultado.getString("pedido"),resultado.getString("urgencia"));
				

			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERROR al conseguir los datos en la base de datos\n" + e.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
		}
		return listaResultado;
	}
	//Obtiene el nombre de las columnas de la tabla
	public List<String> getColumnNames(){
		List<String> listaResultado = new ArrayList<String>();
		try {
			Connection connn = DriverManager.getConnection(this.url, username, password);
			DatabaseMetaData md = connn.getMetaData();
			//getTables(null, "schenaName", "%" ,new String[] {"TABLE"});
			ResultSet rss = md.getTables(null, null, "%", null);
		    String table = "";
		    while(rss.next())
		    	if(!rss.getString(3).contains("MSys")) {
		    		table = rss.getString(3);
		    		System.out.println("Hola");
		    		break;
		    	}
		    System.out.println(table);
			
			
			
			Conexion conn = new Conexion(this.url);
			Statement consulta = conn.getConnection().createStatement();
			ResultSet resultado = consulta.executeQuery("Select * FROM "+table);
			ResultSetMetaData rsmd = resultado.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i));
                listaResultado.add(rsmd.getColumnName(i));
            }
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERROR al conseguir los datos en la base de datos\n" + e.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
		}
		return listaResultado;
	}
	
	
	public List<Pedido> setListaPedidos(String s1,String s2){
		List<Pedido> listaResultado = new ArrayList<Pedido>();
		Pedido pedidoActual = new Pedido();
		try {
			Conexion conn = new Conexion(this.url);
			Statement consulta = conn.getConnection().createStatement();
			
			System.out.println("Select * FROM TablaClientes WHERE "+s1+ " = '"+s2+"'");
			ResultSet resultado = consulta.executeQuery("Select * FROM TablaClientes WHERE "+s1+ "= '"+s2+"'");
			
			
			while(resultado.next()) {
				List<String> nombresColumnas = getColumnsNames();
				List<String[]> variablesAndValue = new ArrayList<String[]>();
				for(int i = 0;i<nombresColumnas.size();i++) {
					variablesAndValue.add(new String[] {nombresColumnas.get(i),resultado.getString(nombresColumnas.get(i))}); //Nos devuelve el nombre de la columna i mas su resultado
					System.out.println("Nombre columna "+nombresColumnas.get(i));
					System.out.println("Valor columna "+resultado.getString(nombresColumnas.get(i)));
				}
				
				pedidoActual = new Pedido(variablesAndValue);	
				listaResultado.add(pedidoActual);
				

				
//				ESTO ES LO QUE ESTABA ANTES
//				pedidoActual = new Pedido(resultado.getString("id"),resultado.getString("nombre"),resultado.getString("dni"),
//						resultado.getString("numtelef"),resultado.getString("fechaentrega"),resultado.getString("pedido"),resultado.getString("urgencia"));
//				System.out.println(resultado.getString("id")+ " " + resultado.getString("nombre")+ " " +resultado.getString("dni")+ " "+
//						resultado.getString("numtelef")+ " " + resultado.getString("fechaentrega")+ " " +resultado.getString("pedido")+ " " +resultado.getString("urgencia"));
//				listaResultado.add(pedidoActual);
			}
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERROR al conseguir los datos en la base de datos\n" + e.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
		}
		return listaResultado;
	}
	
	
	public void insertarPedido(Pedido p) {
		
		System.out.println("Me ejecuto");
		Conexion conn = new Conexion(this.url);	
		
		if(p == null) System.out.println("Esto vale null");
		
		System.out.println("P en 0 1 vale " + p.get(0, 0) +" " + p.get(0, 1));
		
		try {
			Statement consulta = conn.getConnection().createStatement();
			String stringVariables = new String("");
			String stringValores = new String("");
			for(int i = 0;i<p.size();i++) {
				 //Nos devuelve el nombre de la columna i mas su resultado
				if(i == p.size()-1) {
					stringVariables += p.get(i,0);
					stringValores += "'"+p.get(i,1)+"'";
				}
				else {
					stringVariables += p.get(i)[0]+",";
					stringValores += "'"+p.get(i,1)+"',";
				}
			}

			consulta.executeUpdate("INSERT INTO TablaClientes("+stringVariables+") values("+stringValores+")");
			
			
			
				
//			ESTO ES LO QUE ESTABA ANTES
//			consulta.executeUpdate("INSERT INTO TablaClientes(nombre,dni,numtelef,fechaentrega,pedido,urgencia) values('"+p.nombre+"','"+p.dni+"','"+p.telefono
//					+"',#"+p.fechaEntrega+"#,'"+p.pedido+"','"+p.urgencia+"')");
//			JOptionPane.showMessageDialog(null, "Se ha introducido un pedido con la siguiente informacion en la base de datos\n\n"+
//					p.nombre+" "+p.dni + " " + p.telefono+" "+p.fechaEntrega +" "+ p.pedido + " " +
//					p.urgencia, "Pedido añadido", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR al introducir los datos en la base de datos\n" + e.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void eliminarPedido(String s1, String s2) {
		Conexion conn = new Conexion(this.url);	
		try {
			Statement consulta = conn.getConnection().createStatement();
			consulta.executeUpdate("DELETE FROM TablaClientes WHERE "+ s1 +" = '"+s2+"'");
			JOptionPane.showMessageDialog(null, "Se ha eliminado el pedido con "+s1+" = "+s2 +"\n", "Pedido añadido", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR al introducir los datos en la base de datos\n" + e.getMessage(), "ERROR en Conexion", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public boolean esta(String s1, String s2) {
		Conexion conn = new Conexion(this.url);	
		boolean bool = false;
		try {
			Statement consulta = conn.getConnection().createStatement();
			ResultSet resultado = consulta.executeQuery("Select "+s1+" FROM TablaClientes WHERE "+s1+ " = '"+s2+"'");

			if(!resultado.next()) {
				JOptionPane.showMessageDialog(null, "El elemento con " + s1 + " = "+ s2 + "\nNO esta en la base de datos", "Elemento inexistente", JOptionPane.ERROR_MESSAGE);
				bool = false;
			}
			else {
				JOptionPane.showMessageDialog(null, "El elemento con "+s1+" = "+s2 +" se encuentra en la base de datos", "El elemento esta", JOptionPane.INFORMATION_MESSAGE);
				bool = true;
			}
			
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+e.getErrorCode(), "ERROR", JOptionPane.ERROR_MESSAGE);
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	
	
	public String getDbName() {
		return database;
	}
}
