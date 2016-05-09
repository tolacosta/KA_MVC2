package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.dto.Maincategory;

public class MaincategoryDAO {
	Connection con;
	public MaincategoryDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	
	public ResultSet list(String keyword) throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT MC.*, COUNT(C.categoryid) COUNTCATEGORY FROM TBLMAINCATEGORY MC LEFT JOIN TBLCATEGORY C ON MC.maincategoryid=C.maincategoryid WHERE MC.maincategoryname LIKE ? GROUP BY MC.maincategoryid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}
	
	public ResultSet list() throws SQLException{
		return list("");
	}
	
	public Maincategory get(int maincategoryid) throws SQLException{
		Maincategory dto = null;
		try {
			ResultSet rs = null;
			String sql = "SELECT MC.*, COUNT(C.categoryid) COUNTCATEGORY FROM TBLMAINCATEGORY MC LEFT JOIN TBLCATEGORY C ON MC.maincategoryid=C.maincategoryid WHERE MC.maincategoryid=? GROUP BY MC.maincategoryid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, maincategoryid);
			rs = ps.executeQuery();
			
			if(rs.next()){
				dto = new Maincategory();
				dto.setMaincategoryid(rs.getInt("maincategoryid"));
				dto.setMaincategoryname(rs.getString("maincategoryname"));
				dto.setMaincategorylogourl(rs.getString("maincategorylogourl"));
				dto.setMaincategoryorder(rs.getInt("maincategoryorder"));
				dto.setCountcategory(rs.getInt("countcategory"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return dto;
	}
	
	public boolean insert(Maincategory dto) throws SQLException{
		try {
			PreparedStatement pstmt=con.prepareStatement("select max(maincategoryorder) from TBLMAINCATEGORY");
			ResultSet rs = pstmt.executeQuery();
			int num = 1;
			if(rs.next())
				num =rs.getInt(1)+1;
			
			String sql = "INSERT INTO TBLMAINCATEGORY VALUES(NEXTVAL('seq_maincategory'), ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getMaincategoryname());
			ps.setString(2, dto.getMaincategorylogourl());
			ps.setInt(3,num);
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean update(Maincategory dto) throws SQLException{
		try {
			String sql = "update tblmaincategory set maincategoryorder=(select maincategoryorder "
					+ "from tblmaincategory "
					+ "where maincategoryid=?) "
					+ "where maincategoryorder=?;"
					+ "update tblmaincategory set Maincategoryname=?, Maincategorylogourl=?, Maincategoryorder=? WHERE maincategoryid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getMaincategoryid());
			ps.setInt(2, dto.getMaincategoryorder());
			ps.setString(3, dto.getMaincategoryname());
			ps.setString(4, dto.getMaincategorylogourl());
			ps.setInt(5, dto.getMaincategoryorder());
			ps.setInt(6, dto.getMaincategoryid());
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean delete(int maincategoryid) throws SQLException{
		try {
			String sql = "DELETE FROM TBLMAINCATEGORY WHERE maincategoryid="+maincategoryid;
			Statement stmt = con.createStatement();
			if(stmt.executeUpdate(sql)>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public int countmaincategories() throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(MAINCATEGORYID) FROM TBLMAINCATEGORY";
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
	
	public int countcategory(int maincategoryid) throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(CATEGORYID) FROM TBLCATEGORY WHERE maincategoryid="+maincategoryid;
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
	
	
	public int getMaxMaincategoryId() throws SQLException{
		ResultSet rs=null;
		int r = 0;
		try{
			String sql="select LAST_VALUE from seq_maincategory";
			PreparedStatement ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			r=rs.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
		}
	return r+1;
	}
	
}
