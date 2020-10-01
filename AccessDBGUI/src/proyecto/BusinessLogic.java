package proyecto;
import java.util.List;
import java.util.ArrayList;

public class BusinessLogic {
		
	public class Node{
		Node next;
		Node last;
		Pedido item;
		public Node(Node last,Node next,Pedido item) {
			this.next = next;
			this.last = last;
			this.item = item;
		}
	}
	
	private Conexion dbManager;
	private  List<Pedido> listaPedidos;
	
	public Conexion getConexion() {
		return dbManager;
	}
	public BusinessLogic() {
		dbManager = new Conexion();
		listaPedidos = new ArrayList<Pedido>();
	}
	public BusinessLogic(boolean buscar_dB) {
		dbManager = new Conexion(buscar_dB);
		listaPedidos = new ArrayList<Pedido>();
	}
	public BusinessLogic(String url) {
		dbManager = new Conexion(url);
		listaPedidos = new ArrayList<Pedido>();
	}
	

	public List<Pedido> getListaPedidos(){
		listaPedidos = dbManager.setListaPedidos();
		return listaPedidos;
	}
	
	
	public List<Pedido> getListaPedidos(String s1,String s2){
		listaPedidos = dbManager.setListaPedidos(s1,s2);
		return listaPedidos;
		
	}
	
	public void eliminarPedido(String s1, String s2) {
		dbManager.eliminarPedido(s1, s2);
	}
	
	public boolean esta(String s1, String s2) {
		return dbManager.esta(s1, s2);
	}
	
	public void cambiarValor(String valorOld, String valorNew,String id,String nombreColumna) {
		dbManager.modificarDato(valorOld,valorNew,id,nombreColumna);
	}
	public List<String> getNomColumnas(){
		return dbManager.getColumnNames();
	}
	
	public String getBdName() {
		return dbManager.getDbName();
	}
	
	public List<String> getColumnsNames(){
		return dbManager.getColumnsNames();
	}
	
}
