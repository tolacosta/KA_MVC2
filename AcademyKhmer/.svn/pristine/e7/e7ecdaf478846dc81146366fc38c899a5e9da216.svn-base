package model.dao;

import java.sql.*;

import javax.naming.NamingException;

import model.dto.*;

public class UniversityDAO {
	
	private Connection con;
	
	public UniversityDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	
	public boolean addUniversity(University university) throws SQLException{
		String sql = "INSERT INTO tbluniversity(universityid,universityname) VALUES(NEXTVAL('seq_university'),?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, university.getUniversityName());
			if(pstmt.executeUpdate() > 0)
				return true;
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean updateUniversity(University university) throws SQLException{
		try {
			String sql = "UPDATE tbluniversity SET universityname = ? WHERE universityid = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, university.getUniversityName());
			pstmt.setInt(2, university.getUniversityId());
			if(pstmt.executeUpdate() > 0)
				return true;
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean deleteUniversity(int id) throws SQLException{
		String sql = "DELETE FROM tbluniversity WHERE universityid = ?;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate() > 0)
				return true;
		}finally{
			con.close();
		}
		return false;
	}
	
	public ResultSet listUniversity() throws SQLException{
		ResultSet list = null;
		try {
			String sql = "SELECT * FROM tbluniversity;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			list = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return list;
	}
}
