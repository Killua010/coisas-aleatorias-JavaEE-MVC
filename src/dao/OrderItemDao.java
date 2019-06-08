package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Client;
import domain.ItemOrder;
import domain.Order;
import domain.Product;

public class OrderItemDao extends AbstractDao<ItemOrder> {

	public OrderItemDao() {
		super("tb_item_pedido");
	}

	@Override
	public void create(ItemOrder domain) {
		String sql = "INSERT INTO tb_item_pedido"
				+ " (quantidade, produto_id, pedido_id) "
				+ " VALUES (?, ?, ?) ";
		
		try {			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,domain.getQuantity());
			pstm.setLong(2,domain.getProduct().getId());
			pstm.setLong(3,domain.getOrder().getId());
			
			pstm.execute();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

	@Override
	public List<ItemOrder> read(ItemOrder domain) {
		String sql;	
		sql = "SELECT * from tb_item_pedido WHERE pedido_id = " + domain.getOrder().getId();
				
		conn = ConnectionDB.openConnection();
		ResultSet result;
		
		List<ItemOrder> itemOrders = new ArrayList<ItemOrder>();
		
		try {
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				ItemOrder itemOrder = new ItemOrder();
				itemOrder.setId(result.getLong("id"));
				itemOrder.setQuantity(result.getInt("quantidade"));
				
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product.setId(result.getLong("produto_id"));
				
				itemOrder.setProduct(productDao.read(product).get(0));
				
				itemOrders.add(itemOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		return itemOrders;
	}

	@Override
	public void update(ItemOrder domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ItemOrder domain) {
		// TODO Auto-generated method stub
		
	}

}
