package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import model.dto.Category;

public class CategoryDAO {
	Connection con;
	public CategoryDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	/*public static void main(String[] args) throws ClassNotFoundException, NamingException, SQLException {
		Category dto=new Category();
		CategoryDAO dao=new CategoryDAO();
		ResultSet rs=dao.list(1,3);
		while(rs.next())
		System.out.println(rs.getString("categoryid"));
	}*/
	public ResultSet list(String keyword,int page,int limit) throws SQLException{
		try {
			ResultSet rs = null;			
			String sql = "SELECT C.*, MC.maincategoryname, COUNT(CV.categoryid) COUNTVIDEOS FROM TBLCATEGORY C LEFT JOIN TBLCATEGORYVIDEO CV ON C.categoryid=CV.categoryid LEFT JOIN TBLMAINCATEGORY MC ON MC.maincategoryid=C.maincategoryid WHERE C.categoryname LIKE ? GROUP BY C.categoryid, MC.maincategoryname ORDER BY categoryid DESC LIMIT ? OFFSET ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ps.setInt(2, limit);
			ps.setInt(3, (page-1)*limit);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet list(int page,int limit) throws SQLException{
		return list("",page,limit);
	}
	
	public ResultSet list(String keyword) throws SQLException{
		try {
			ResultSet rs = null;			
			String sql = "SELECT C.*, MC.maincategoryname, COUNT(CV.categoryid) COUNTVIDEOS FROM TBLCATEGORY C LEFT JOIN TBLCATEGORYVIDEO CV ON C.categoryid=CV.categoryid LEFT JOIN TBLMAINCATEGORY MC ON MC.maincategoryid=C.maincategoryid WHERE C.categoryname LIKE ? GROUP BY C.categoryid, MC.maincategoryname ORDER BY categoryid DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs = ps.executeQuery();
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
	
	public Category get(int categoryid) throws SQLException{
		try {
			ResultSet rs = null;
			Category dto = null;
			String sql = "SELECT C.*, MC.maincategoryname, COUNT(CV.categoryid) COUNTVIDEOS FROM TBLCATEGORY C LEFT JOIN TBLCATEGORYVIDEO CV ON C.categoryid=CV.categoryid INNER JOIN TBLMAINCATEGORY MC ON C.maincategoryid=MC.maincategoryid WHERE C.categoryid="+categoryid+" GROUP BY C.categoryid, MC.maincategoryname";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				dto = new Category(categoryid, rs.getString("categoryname"), rs.getString("categorylogourl"));
				dto.setCategoryid(categoryid);
				dto.setMaincategoryid(rs.getInt("maincategoryid"));
				dto.setMaincategoryname(rs.getString("maincategoryname"));
				dto.setCountvideos(rs.getInt("countvideos"));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public boolean insert(Category dto) throws Exception{
		try {
			String sql = "INSERT INTO TBLCATEGORY VALUES(NEXTVAL('seq_category'), ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCategoryname());
			ps.setString(2, dto.getCategorylogourl());
			ps.setInt(3, dto.getMaincategoryid());
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean update(Category dto) throws SQLException{
		try {
			String sql = "UPDATE TBLCATEGORY SET categoryname=?, categorylogourl=?, maincategoryid=? WHERE categoryid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCategoryname());
			ps.setString(2, dto.getCategorylogourl());
			ps.setInt(3, dto.getMaincategoryid());
			ps.setInt(4, dto.getCategoryid());
			if(ps.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean delete(int categoryid) throws SQLException{
		try {
			String sql = "DELETE FROM TBLCATEGORY WHERE categoryid="+categoryid;
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
	
	public int countcategories() throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(CATEGORYID) FROM TBLCATEGORY";
			Statement stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//con.close();
		}
		return 0;
	}
	
	public int getMaxcategoryId() throws SQLException{
		ResultSet rs=null;
		int r = 0;
		try{
			String sql="select LAST_VALUE from seq_category";
			PreparedStatement ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			r=rs.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
		}
	return r+1;
	}
	
	public int countvideos(int categoryid) throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(CV.categoryid) COUNTVIDEOS FROM TBLCATEGORY C LEFT JOIN TBLCATEGORYVIDEO CV ON C.categoryid=CV.categoryid WHERE C.categoryid="+categoryid+" GROUP BY C.categoryid";
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
	public ResultSet listVideosInCategory(int categoryid, int page, int maxview) throws SQLException{
		ResultSet rs = null;
		try{
			String sql = "select v.*, u.username, cat.categoryname from tblcategoryvideo cv join tblvideo v on v.videoid = cv.videoid join tbluser u on v.userid = u.userid join tblcategory cat on cat.categoryid = cv.categoryid where cv.categoryid = ? order by v.videoid DESC OFFSET ? LIMIT ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryid);
			ps.setInt(2, (page-1)*maxview);
			ps.setInt(3, maxview);
			rs=ps.executeQuery();
			return rs;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}

		System.out.println(rs);
		return null;
	}
}
