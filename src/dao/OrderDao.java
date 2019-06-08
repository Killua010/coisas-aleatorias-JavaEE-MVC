package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import domain.Category;
import domain.Client;
import domain.ItemOrder;
import domain.ItemShoppingCart;
import domain.Order;
import domain.User;

public class OrderDao extends AbstractDao<Order> {

	public OrderDao() {
		super("tb_pedido");
	}
	
	public List<Order> find(String[] tableValues, String[] results){
		
		List<Order> orders = new ArrayList<>();
		
		String sql = super.findBy(tableValues, results);
		ResultSet result = null;
		try {
			conn = ConnectionDB.openConnection();
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				Order order = new Order();
				order.setId(result.getLong("id"));
				order.setTotal(result.getFloat("valor"));
				
				ClientDao clientDao = new ClientDao();
				Client client = new Client();
				client.setId(result.getLong("cliente_id"));
				
				order.setClient(clientDao.read(client).get(0));
				
				OrderItemDao orderItemDao = new OrderItemDao();
				ItemOrder itemOrder = new ItemOrder();
				itemOrder.setOrder(order);
				for(ItemOrder itemOrder2: orderItemDao.read(itemOrder)){
					order.getItemOrders().add(itemOrder2);
				}
				
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		
		return orders;
	}

	@Override
	public void create(Order domain) {
		String sql = "INSERT INTO tb_pedido"
				+ " (valor, cliente_id) "
				+ " VALUES (?, ?) ";
		
		ResultSet result = null;
		
		try {			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setFloat(1,domain.getTotal());
			pstm.setLong(2,domain.getClient().getId());
			
			pstm.execute();
			
			result = conn.prepareStatement(lastId).executeQuery();
	    	while(result.next()) 
	    		domain.setId(result.getLong(1));
			
	    	OrderItemDao dao = new OrderItemDao();
	    	
	    	for(ItemOrder item: domain.getItemOrders()) {
	    		item.setOrder(domain);
	    		dao.create(item);
	    	}
	    	
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

	@Override
	public List<Order> read(Order domain) {

		String sql;	
		sql = "SELECT * from tb_pedido";
				
		conn = ConnectionDB.openConnection();
		ResultSet result;
		
		List<Order> orders = new ArrayList<Order>();
		
		try {
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				Order order = new Order();
				order.setId(result.getLong("id"));
				order.setTotal(result.getFloat("valor"));
				
				ClientDao clientDao = new ClientDao();
				Client client = new Client();
				client.setId(result.getLong("cliente_id"));
				
				order.setClient(clientDao.read(client).get(0));
				
				OrderItemDao orderItemDao = new OrderItemDao();
				ItemOrder itemOrder = new ItemOrder();
				itemOrder.setOrder(order);
				for(ItemOrder itemOrder2: orderItemDao.read(itemOrder)){
					order.getItemOrders().add(itemOrder2);
				}
				
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		
		return orders;
	}

	@Override
	public void update(Order domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Order domain) {
		// TODO Auto-generated method stub
		
	}

}
