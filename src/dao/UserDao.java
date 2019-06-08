package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Client;
import domain.Role;
import domain.User;

public class UserDao extends AbstractDao<User> {

	public UserDao() {
		super("tb_usuario");
	}

	@Override
	public void create(User domain) {
		String sqlUser = "INSERT INTO tb_usuario"
				+ " (email, senha, papel) "
				+ " VALUES (?,?,?) ";
		
		ResultSet result = null;
		
		conn = ConnectionDB.openConnection();
		
		try {
			
			pstm = conn.prepareStatement(sqlUser);
			pstm.setString(1,domain.getEmail());
			pstm.setString(2,domain.getPassword());
			pstm.setString(3, domain.getRole().toString());
			
			pstm.execute();
			
			result = conn.prepareStatement(lastId).executeQuery();
	    	while(result.next()) 
	    		domain.setId(result.getLong(1));
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

	@Override
	public List<User> read(User domain) {
		
		String sql;
		
		if(domain.getId() == null) {
			sql = "SELECT * from tb_usuario WHERE email = '" + domain.getEmail() + "' AND senha = '" + domain.getPassword() + "'";
		} else {
			sql = "SELECT * from tb_usuario WHERE id = " + domain.getId();
		}
		
		Connection conn = ConnectionDB.openConnection();
		ResultSet result;
		
		List<User> users = new ArrayList<>();
		
		try {
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				User user = new User();
				
				user.setId(result.getLong("id"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("senha"));
				user.setRole(Role.valueOf(result.getString("papel")));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		
		return users;
	}

	@Override
	public void update(User domain) {
		String sql = "UPDATE tb_usuario SET"
				+ " email = ?, senha = ? "
				+ "WHERE id = ?";

		try {						
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, domain.getEmail());
			pstm.setString(2, domain.getPassword());
			pstm.setLong(3, domain.getId());
			
			pstm.execute();
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}


}
