package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.Product;

public class CategoryDao extends AbstractDao<Category> {

	public CategoryDao() {
		super("tb_categoria");
	}

	@Override
	public void create(Category category) {
		String sql = "INSERT INTO tb_categoria"
				+ " (nome,foto) "
				+ " VALUES (?,?) ";
		
		try {			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,category.getName());
			pstm.setString(2,category.getPhoto());
			
			pstm.execute();
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

	@Override
	public List<Category> read(Category domain) {
		
		String sql;
		
		if(domain == null) {
			sql = "SELECT * from tb_categoria";
		} else {
			sql = "SELECT * from tb_categoria WHERE id = " + domain.getId();
		}
		
		
		conn = ConnectionDB.openConnection();
		ResultSet result;
		
		List<Category> categories = new ArrayList<Category>();
		
		try {
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				Category category = new Category();
				category.setId(result.getLong("id"));
				category.setName(result.getString("nome"));
				category.setPhoto(result.getString("foto"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		
		return categories;
	}

	@Override
	public void update(Category category) {
		String sql = "UPDATE tb_categoria SET"
				+ " nome = ?, foto = ? "
				+ "WHERE id = ?";

		try {			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,category.getName());
			pstm.setString(2,category.getPhoto());
			pstm.setLong(3,category.getId());
			
			pstm.execute();
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

}
