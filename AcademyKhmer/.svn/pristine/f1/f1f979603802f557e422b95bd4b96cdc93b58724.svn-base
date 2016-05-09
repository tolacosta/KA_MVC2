package model.dao;

import java.sql.*;

import javax.naming.NamingException;

import model.dto.Department;

public class DepartmentDAO {
	
	private Connection con;
	
	public DepartmentDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	
	public boolean addDepartment(Department department) throws SQLException{
		String sql = "INSERT INTO tbldepartment(departmentid,departmentname) VALUES(NEXTVAL('seq_department'),?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department.getDepartmentName());
			if(pstmt.executeUpdate() > 0)
				return true;
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean updateDepartment(Department department) throws SQLException{
		try {
			String sql = "UPDATE tbldepartment SET departmentname = ? WHERE departmentid = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department.getDepartmentName());
			pstmt.setInt(2, department.getDepartmentId());
			if(pstmt.executeUpdate() > 0)
				return true;
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean deleteDepartment(int id) throws SQLException{
		String sql = "DELETE FROM tbldepartment WHERE departmentid = ?;";
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
	
	public ResultSet listDepartment() throws SQLException{
		ResultSet list = null;
		try {
			String sql = "SELECT * FROM tbldepartment;";
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
