package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.DomainEntity;

public abstract class AbstractDao<domain extends DomainEntity> implements IDao<domain> {
	
	protected Connection conn;
	
	protected PreparedStatement pstm;
	
	private String tableName;
	
	protected final String lastId = "SELECT LAST_INSERT_ID()";
	
	public AbstractDao(String tableName) {
		this.tableName = tableName;
	}
	
	public String findBy(String[] tableValues, String[] result){
		if(tableValues.length != result.length) {
			try {
				throw new Exception("Valores de consultas diferentes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		List<domain> domains = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from " + tableName + " WHERE ");
		for(int i = 0; i < tableValues.length; i++) {
			sql.append(tableValues[i] + " = '" + result[i] + "'");
		}
		return sql.toString();
	}

	@Override
	public void delete(DomainEntity domain) {
		String sql = "DELETE FROM " + tableName +" WHERE id = " + domain.getId() + ";";
		try {
			conn = ConnectionDB.openConnection();
			conn.prepareStatement(sql).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionDB.closeConnection(conn, null);
		}
	}

}
