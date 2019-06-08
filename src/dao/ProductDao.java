package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.Product;

public class ProductDao extends AbstractDao<Product> {

	public ProductDao() {
		super("tb_produto");
	}

	@Override
	public void create(Product product) {
		String sql = "INSERT INTO tb_produto"
				+ " (nome,valor,descricao,foto,categoria_id) "
				+ " VALUES (?,?,?,?,?) ";
		
		try {			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,product.getName());
			pstm.setFloat(2,product.getValue());
			pstm.setString(3,product.getDescription());
			pstm.setString(4,product.getPhoto());
			pstm.setLong(5,product.getCategory().getId());
			
			pstm.execute();
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

	@Override
	public List<Product> read(Product domain) {
		
		String sql;
		
		if(domain == null) {
			sql = "SELECT * from tb_produto";
		} else {
			sql = "SELECT * from tb_produto WHERE id = " + domain.getId();
		}
		
		
		conn = ConnectionDB.openConnection();
		ResultSet result;
		CategoryDao categoryDao = new CategoryDao();
		List<Product> products = new ArrayList<Product>();
		
		try {
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				Product product = new Product();
				Category category = new Category();
				category.setId(result.getLong("categoria_id"));
				
				category = categoryDao.read(category).get(0);
				product.setCategory(category);
				product.setId(result.getLong("id"));
				product.setDescription(result.getString("descricao"));
				product.setName(result.getString("nome"));
				product.setPhoto(result.getString("foto"));
				product.setValue(result.getFloat("valor"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		
		return products;
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE tb_produto SET"
				+ " nome = ?,valor = ?, descricao = ?, foto = ?, categoria_id = ? "
				+ "WHERE id = ?";
		try {
			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,product.getName());
			pstm.setFloat(2,product.getValue());
			pstm.setString(3,product.getDescription());
			pstm.setString(4,product.getPhoto());
			pstm.setLong(5,product.getCategory().getId());
			pstm.setLong(6,product.getId());
			
			pstm.execute();
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

}
