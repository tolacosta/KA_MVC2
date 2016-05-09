package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.dto.ForumCategory;

public class ForumCategoryDAO {
	Connection con;

	public ForumCategoryDAO() throws ClassNotFoundException, NamingException,
			SQLException {
		con = new DBUtility().getConnect();
	}

	public static void main(String[] args) throws ClassNotFoundException,
			NamingException, SQLException {
		
		ForumCategoryDAO dao = new ForumCategoryDAO();
		ResultSet rs = dao.list("", 1, 3);
		while (rs.next())
			System.out.println(rs.getString("categoryid"));
	}

	public ResultSet list(String keyword, int page, int limit)
			throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT CA.*, COUNT(C.categoryid) COUNTVIDEOS FROM TBLFORUMCATEGORY CA LEFT JOIN TBLFORUMCOMMENT C ON CA.categoryid=C.categoryid where lower(categoryname) like lower(?) GROUP BY C.categoryid, CA.categoryid order by categoryname asc limit ? offset ? ";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setInt(2, limit);
			ps.setInt(3, (page - 1) * limit);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}
	
	public ResultSet list()throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT CA.*, COUNT(CO.Commentid) COMMENTCOUNT FROM TBLFORUMCATEGORY CA LEFT JOIN TBLFORUMCOMMENT CO ON CA.Categoryid=CO.Categoryid GROUP BY CA.Categoryid";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

//	WHAT IS IT FOR?
//	public ResultSet list(int categoryid) throws SQLException {
//		try {
//			ResultSet rs = null;
//			String sql = "SELECT CA.* from tblforumcategory CA INNER JOIN tblforumcomment CO on CA.categoryid= CO.categoryid WHERE Co.categoryid=? and CO.parentid is null";
//			PreparedStatement ps;
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, categoryid);
//			rs = ps.executeQuery();
//			return rs;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//		return null;
//	}

	public ForumCategory get(int category_id) throws SQLException {

		try {
			ResultSet rs = null;
			ForumCategory dto = null;
			String sql = "SELECT CA.*, COUNT(C.categoryid) COUNTCOMMENTS FROM TBLFORUMCATEGORY CA LEFT JOIN TBLFORUMCOMMENT C ON CA.categoryid=C.categoryid WHERE CA.Categoryid="+category_id+" GROUP BY C.categoryid, CA.categoryid";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				dto = new ForumCategory(category_id, rs.getString("categoryname"));
				dto.setComment_count(rs.getInt("countcomments"));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return null;
	}

	public boolean insert(ForumCategory dto) throws Exception {

		try {
			String sql = "INSERT INTO tblforumcategory VALUES (NEXTVAL('seq_forumcategory'), ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCategory_name());
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}

	public boolean update(ForumCategory dto) throws Exception {
		try {
			String sql = "Update tblforumcategory set categoryname=? where categoryid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCategory_name());
			ps.setInt(2, dto.getCategory_id());
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}

	public boolean delete(int category_id) throws SQLException {
		try {
			String sql = "DELETE FROM TBLFORUMCATEGORY WHERE categoryid="+ category_id;
			Statement stmt = con.createStatement();
			if (stmt.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	public int countcategories() throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(CATEGORYID) FROM TBLFORUMCATEGORY";
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
}
