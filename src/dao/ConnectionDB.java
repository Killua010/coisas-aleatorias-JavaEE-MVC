package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static final String username = "daniel";
	private static final String password = "daniel";
	private static final String url = "jdbc:mysql://localhost/coisasAleatorias?createDatabaseIfNotExist=true";
	private static final String driverPath = "com.mysql.jdbc.Driver";
	
	public static Connection openConnection() {
		try {
			Class.forName(driverPath);
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(Connection connection, PreparedStatement preparedStatement) {
		if(connection != null){
			try{
				connection.close();
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			
		}
		if(preparedStatement != null){
			try{
				preparedStatement.close();
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			
		}
	}
}
