package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Client;
import domain.Role;
import domain.User;

public class ClientDao extends AbstractDao<Client> {

	public ClientDao() {
		super("tb_cliente");
	}

	@Override
	public void create(Client client) {
		
		String sqlClient = "INSERT INTO tb_cliente"
				+ " (nome, sobrenome, usuario_id) "
				+ " VALUES (?,?,?) ";
		
		UserDao userDao = new UserDao();
		
		ResultSet result = null;
		
		try {			
			client.getUser().setRole(Role.CLIENTE);
			userDao.create(client.getUser());
			
			conn = ConnectionDB.openConnection();
	    	pstm = conn.prepareStatement(sqlClient);
			pstm.setString(1,client.getFirstName());
			pstm.setString(2,client.getLastName());
			pstm.setLong(3,client.getUser().getId());
			pstm.execute();
			
			result = conn.prepareStatement(lastId).executeQuery();
	    	while(result.next()) 
	    		client.setId(result.getLong(1));
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}
	
	public List<Client> find(String[] tableValues, String[] results){
		
		List<Client> clients = new ArrayList<>();
		
		String sql = super.findBy(tableValues, results);
		
		UserDao userDao = new UserDao();
		ResultSet result;
		Connection conn = ConnectionDB.openConnection();
		try {
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				User user = new User();
				Client client = new Client();
				
				client.setId(result.getLong("id"));
				client.setLastName(result.getString("sobrenome"));
				client.setFirstName(result.getString("nome"));
				
				user.setId(result.getLong("usuario_id"));
				client.setUser(userDao.read(user).get(0));
				
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		
		return clients;
	}

	@Override
	public List<Client> read(Client domain) {
		String sql = "SELECT * from tb_cliente WHERE id = " + domain.getId();
		
		Connection conn = ConnectionDB.openConnection();
		ResultSet result;
		
		List<Client> clients = new ArrayList<>();
		UserDao userDao = new UserDao();
		
		try {
			result = conn.prepareStatement(sql).executeQuery();
			while(result.next()) {
				User user = new User();
				Client client = new Client();
				
				client.setId(result.getLong("id"));
				client.setLastName(result.getString("sobrenome"));
				client.setFirstName(result.getString("nome"));
				
				user.setId(result.getLong("usuario_id"));
				client.setUser(userDao.read(user).get(0));
				
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn, null);
		}
		
		return clients;
	}

	@Override
	public void update(Client domain) {
		String sql = "UPDATE tb_cliente SET"
				+ " nome = ?, sobrenome = ? "
				+ "WHERE id = ?";

		try {			
			UserDao userDao = new UserDao();
			userDao.update(domain.getUser());
			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, domain.getFirstName());
			pstm.setString(2, domain.getLastName());
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
