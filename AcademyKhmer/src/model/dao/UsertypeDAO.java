package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.dto.Usertype;

public class UsertypeDAO {
	Connection con;
	public UsertypeDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	
	public ResultSet list(String keyword) throws SQLException{
		try {
			ResultSet rs = null;			
			String sql = "SELECT UT.*, COUNT(DISTINCT USERID) COUNTUSERS FROM TBLUSERTYPE UT LEFT JOIN TBLUSER U ON UT.USERTYPEID=U.USERTYPEID WHERE UT.USERTYPENAME LIKE '?' GROUP BY UT.USERTYPEID";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs = ps.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet list() throws SQLException{
		return list("");
	}
	
	public Usertype get(int usertypeid) throws SQLException{
		try {
			ResultSet rs = null;
			Usertype dto = null;
			String sql = "SELECT UT.*, COUNT(DISTINCT USERID) COUNTUSERS FROM TBLUSERTYPE UT LEFT JOIN TBLUSER U ON UT.USERTYPEID=U.USERTYPEID WHERE UT.USERTYPEID="+usertypeid+" GROUP BY UT.USERTYPEID";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				dto = new Usertype(rs.getInt("usertypeid"), rs.getString("usertypename"), rs.getBoolean("viewable"), rs.getBoolean("commentable"), rs.getBoolean("postable"), rs.getBoolean("deleteable"), rs.getBoolean("userable"));
				dto.setCountusers(rs.getInt("countusers"));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public boolean insert(Usertype dto) throws SQLException{
		try {
			String sql = "INSERT INTO TBLUSERTYPE VALUES(NEXTVAL('seq_usertype'), ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUsertypename());
			ps.setBoolean(2, dto.isViewable());
			ps.setBoolean(3, dto.isCommentable());
			ps.setBoolean(4, dto.isPostable());
			ps.setBoolean(5, dto.isDeleteable());
			ps.setBoolean(6, dto.isUserable());
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean update(Usertype dto) throws SQLException{
		try {
			String sql = "UPDATE TBLUSERTYPE SET usertypename=?, viewable=?, commentable=?, postable=?, deleteable=?, userable=? WHERE usertypeid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUsertypename());
			ps.setBoolean(2, dto.isViewable());
			ps.setBoolean(3, dto.isCommentable());
			ps.setBoolean(4, dto.isPostable());
			ps.setBoolean(5, dto.isDeleteable());
			ps.setBoolean(6, dto.isUserable());
			ps.setInt(7, dto.getUsertypeid());
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean delete(int usertypeid) throws SQLException{
		try {
			String sql = "DELETE FROM TBLUSERTYPE WHERE usertypeid="+usertypeid;
			Statement ps = con.createStatement();
			if(ps.executeUpdate(sql)>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public int countusertype() throws SQLException{
		try {
			String sql = "SELECT COUNT(usertypeid) FROM TBLUSERTYPE";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	
	
	public ResultSet seleteAllUserType() throws SQLException{
		try {
			ResultSet rs = null;			
			String sql = "SELECT usertypeid , usertypename  from tblusertype";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	
}
