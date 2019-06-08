package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import domain.ContactUs;

public class ContactUsDao implements IDao<ContactUs> {

	@Override
	public void create(ContactUs domain) {
		String sql = "INSERT INTO tb_fale_conosco"
				+ " (nome,email,assunto) "
				+ " VALUES (?,?,?) ";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {			
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,domain.getName());
			pstm.setString(2,domain.getEmail());
			pstm.setString(3,domain.getSubject());
			
			pstm.execute();
			
			return;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			ConnectionDB.closeConnection(conn, pstm);
		}
	}

	@Override
	public List<ContactUs> read(ContactUs domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ContactUs domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ContactUs domain) {
		// TODO Auto-generated method stub
		
	}

}
