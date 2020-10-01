package proyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IniciarProyecto {
	public static void main(String[] args) {
		
		System.out.println(System.getProperty("user.dir"));
		BusinessLogic bl = new BusinessLogic();
		//bl.getListaPedidos();
		try {
			Conexion conn = new Conexion();
			Statement consulta = conn.getConnection().createStatement();
			ResultSet resultado = consulta.executeQuery("Select * FROM TablaClientes");
			while(resultado.next()) {
				//System.out.println("id de "+ resultado.getString("Nombre") + ": "+ resultado.getString("id"));
				System.out.println(resultado.getString("id")+ " " + resultado.getString("nombre")+ " " +resultado.getString("dni")+ " "+
						resultado.getString("numtelef")+ " " + resultado.getString("fechaentrega")+ " " +resultado.getString("pedido")+ " " +resultado.getString("urgencia"));
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
