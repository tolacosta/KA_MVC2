package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.dto.Category;
import model.dto.Tutorial;

public class TutorialDAO {
	
	Connection con;
	public TutorialDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	
	public ResultSet lists(int userid) throws SQLException{
		try {
			ResultSet rs = null;			
			String sql = "SELECT T.tutorialid,T.index, T.title,T.userid,T.categoryid, C.CATEGORYNAME, U.USERNAME FROM TBLTUTORIAL T INNER JOIN TBLCATEGORY C ON T.CATEGORYID=C.CATEGORYID INNER JOIN TBLUSER U ON T.USERID=U.USERID where u.userid=? ORDER BY T.CATEGORYID, T.INDEX";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet list(int categoryid) throws SQLException{
		try {
			ResultSet rs = null;			
			String sql = "SELECT T.title, T.tutorialid, C.CATEGORYNAME, U.USERNAME FROM TBLTUTORIAL T INNER JOIN TBLCATEGORY C ON T.CATEGORYID=C.CATEGORYID INNER JOIN TBLUSER U ON T.USERID=U.USERID WHERE T.CATEGORYID=? ORDER BY T.INDEX ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryid);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public Tutorial get(int tutorialid) throws SQLException{
		try {
			ResultSet rs = null;
			Tutorial dto = null;
			String sql = "SELECT T.tutorialid, T.title, T.index, T.description, C.CATEGORYNAME, U.USERNAME FROM TBLTUTORIAL T INNER JOIN TBLCATEGORY C ON T.CATEGORYID=C.CATEGORYID INNER JOIN TBLUSER U ON T.USERID=U.USERID WHERE T.TUTORIALID=? ORDER BY T.INDEX ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, tutorialid);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new Tutorial();
				dto.setTutorialid(rs.getInt("tutorialid"));
				dto.setTitle(rs.getString("title"));
				dto.setDescription(rs.getString("description"));
				dto.setIndex(rs.getInt("index"));
				/*dto.setUserid(rs.getInt("userid"));*/
				/*dto.setCategoryid(rs.getInt("categoryid"));*/
				dto.setUsername(rs.getString("username"));
				dto.setCategoryname(rs.getString("categoryname"));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	
	public Tutorial getFirstDetail(int categoryid) throws SQLException{
		try {
			ResultSet rs = null;
			Tutorial dto = null;
			String sql = "SELECT T.*, C.CATEGORYNAME, U.USERNAME FROM TBLTUTORIAL T INNER JOIN TBLCATEGORY C ON T.CATEGORYID=C.CATEGORYID INNER JOIN TBLUSER U ON T.USERID=U.USERID WHERE C.Categoryid=? ORDER BY T.INDEX Limit 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryid);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new Tutorial();
				dto.setTutorialid(rs.getInt("tutorialid"));
				dto.setTitle(rs.getString("title"));
				dto.setDescription(rs.getString("description"));
				dto.setIndex(rs.getInt("index"));
				dto.setUserid(rs.getInt("userid"));
				dto.setCategoryid(rs.getInt("categoryid"));
				dto.setUsername(rs.getString("username"));
				dto.setCategoryname(rs.getString("categoryname"));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	public boolean insert(Tutorial dto) throws Exception{
		try {
			String sql = "INSERT INTO TBLTUTORIAL VALUES(NEXTVAL('seq_tutorial'), ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getDescription());
			ps.setInt(3, dto.getIndex());
			ps.setInt(4, dto.getUserid());
			ps.setInt(5, dto.getCategoryid());
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean update(Tutorial dto) throws Exception{
		try {
			String sql = "UPDATE TBLTUTORIAL SET title=?, description=?, index=?, userid=?, categoryid=? WHERE tutorialid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getDescription());
			ps.setInt(3, dto.getIndex());
			ps.setInt(4, dto.getUserid());
			ps.setInt(5, dto.getCategoryid());
			ps.setInt(6, dto.getTutorialid());
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean delete(int tutorialid) throws Exception{
		try {
			String sql = "DELETE FROM TBLTUTORIAL WHERE tutorialid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, tutorialid);
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public int count() throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(tutorialid) FROM TBLTUTORIAL";
			Statement stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	
	public int count(int categoryid) throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(tutorialid) FROM TBLTUTORIAL WHERE Categoryid="+categoryid;
			Statement stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	
	
}
