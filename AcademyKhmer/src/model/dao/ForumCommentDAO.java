package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import ultilities.ClsPagination;
import model.dto.ForumComment;

public class ForumCommentDAO {
	Connection con;

	public ForumCommentDAO() throws ClassNotFoundException, NamingException,
			SQLException {
		con = new DBUtility().getConnect();
	}

	public static void main(String[] args) throws ClassNotFoundException, NamingException, SQLException {
 		ForumCommentDAO dao = new ForumCommentDAO();
		ResultSet rs = dao.listComment(1 , new ClsPagination());
		while (rs.next())
			System.out.println(rs.getString("commentid"));
	}
	
	
	// list all question, list question by keyword, list by userid, and list by categoryid
	public ResultSet listAllQuestion(String keyword, String userid, String cateid, ClsPagination pagin, String tag)throws SQLException {
		ResultSet rs = null;

		String sql = "SELECT DISTINCT(C1.*), U.Username, COUNT(C2.Commentid) COMMENTCOUNT, COUNT(FV.Commentid) VOTECOUNT"
				+ " FROM TBLFORUMCOMMENT C1 LEFT JOIN TBLFORUMCOMMENT C2 ON C1.Commentid=C2.Parentid"
				+ " INNER JOIN TBLUSER U ON C1.Userid=U.Userid"
				+ " LEFT JOIN TBLFORUMVOTE FV ON C1.Commentid=FV.Commentid AND FV.Votetype=1"
				+ " WHERE CAST(C1.Userid as VARCHAR) like ?"
				+ " AND CAST(C1.Categoryid as VARCHAR) like ?"
				+ " AND C1.title like ?"
				+ " AND C1.Parentid IS NULL"
				+ " AND C1.tag like ?"
				+ " GROUP BY C1.Commentid, C2.Commentid, U.Userid, FV.Commentid"
				+ " ORDER BY COMMENTID DESC offset ? limit ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+userid+"%");
			ps.setString(2, "%"+cateid+"%");
			ps.setString(3, "%"+keyword+"%");
			ps.setString(4, "%"+tag+"%");
			ps.setInt(5, pagin.offset()); 
			ps.setInt(6, pagin.getPerPage());
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}
	
	// count all question, count question by keyword, count by userid, and count by categoryid
	public int countQuestion(String keyword, String userid, String cateid)throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMCOMMENT WHERE Parentid IS NULL"
					+ " AND title like ?"
					+ " AND CAST(categoryid as VARCHAR) like ?"
					+ " AND CAST(userid as VARCHAR) like ?"; 

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+userid+"%");
			ps.setString(2, "%"+cateid+"%");
			ps.setString(3, "%"+keyword+"%");
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
	///////////////////////////////////////////////////////
	
	
	
	public ResultSet listQuestionByUser(int userid) throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT DISTINCT(C1.*), U.Username, COUNT(C2.Commentid) COMMENTCOUNT, COUNT(FV.Commentid) VOTECOUNT FROM TBLFORUMCOMMENT C1 LEFT JOIN TBLFORUMCOMMENT C2 ON C1.Commentid=C2.Parentid INNER JOIN TBLUSER U ON C1.Userid=U.Userid LEFT JOIN TBLFORUMVOTE FV ON C1.Commentid=FV.Commentid AND FV.Votetype=1 WHERE C1.Userid=? AND C1.Parentid IS NULL GROUP BY C1.Commentid, C2.Commentid, U.Userid, FV.Commentid ORDER BY COMMENTID DESC";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			con.close();
		}
		return null;
	}
	
	public ResultSet listQuestionByCategory(int categoryid)throws SQLException {
		ResultSet rs = null;

		String sql = "SELECT DISTINCT(C1.*), U.Username, U.UserImageUrl, COUNT(C2.Commentid) COMMENTCOUNT, SUM(VOTETYPE) VOTECOUNT FROM TBLFORUMCOMMENT C1 LEFT JOIN TBLFORUMCOMMENT C2 ON C1.Commentid=C2.Parentid INNER JOIN TBLUSER U ON C1.Userid=U.Userid LEFT JOIN TBLFORUMVOTE FV ON C1.Commentid=FV.Commentid AND FV.Votetype=1 WHERE C1.Categoryid=? AND C1.Parentid IS NULL GROUP BY C1.Commentid, U.Userid, FV.Commentid ORDER BY COMMENTID DESC";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, categoryid);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}
	
	
	public ResultSet listQuestion() throws SQLException {
		ResultSet rs = null;
 
		String sql = "SELECT DISTINCT(C1.*), U.Username, U.UserImageURL, COUNT(DISTINCT(C2.Commentid)) COMMENTCOUNT, SUM(VOTETYPE) VOTECOUNT FROM TBLFORUMCOMMENT C1 LEFT JOIN TBLFORUMCOMMENT C2 ON C1.Commentid=C2.Parentid INNER JOIN TBLUSER U ON C1.Userid=U.Userid LEFT JOIN TBLFORUMVOTE FV ON C1.Commentid=FV.Commentid WHERE C1.Parentid IS NULL GROUP BY C1.Commentid, U.Userid, FV.Commentid ORDER BY COMMENTID DESC LIMIT 10";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();		// WAS CLOSED
		}
		return null;
	}
	
	public ResultSet listComment(int parent_id , ClsPagination pagin)
			throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT DISTINCT(C1.*), U.Username, U.UserImageURL, C1.SELECTED, COUNT(C2.Commentid) COMMENTCOUNT, SUM(VOTETYPE) VOTECOUNT FROM TBLFORUMCOMMENT C1 LEFT JOIN TBLFORUMCOMMENT C2 ON C1.Commentid=C2.Parentid INNER JOIN TBLUSER U ON C1.Userid=U.Userid LEFT JOIN TBLFORUMVOTE FV ON C1.Commentid=FV.Commentid WHERE C1.Parentid=? GROUP BY C1.Commentid, U.Userid, FV.Commentid ORDER BY SELECTED DESC, COMMENTID DESC offset ? limit ?";
//		String sql = "SELECT DISTINCT(C1.*), U.Username, U.userimageurl, COUNT(C2.Commentid) COMMENTCOUNT, COUNT(DISTINCT(FV.Userid)) VOTECOUNT FROM TBLFORUMCOMMENT C1 LEFT JOIN TBLFORUMCOMMENT C2 ON C1.Commentid=C2.Parentid INNER JOIN TBLUSER U ON C1.Userid=U.Userid LEFT JOIN (SELECT * FROM TBLFORUMVOTE WHERE VOTETYPE=1) FV ON C1.Commentid=FV.Commentid WHERE C1.Parentid=? GROUP BY C1.Commentid, U.Userid, FV.Commentid ORDER BY SELECTED ASC, COMMENTID DESC offset ? limit ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, parent_id);
			ps.setInt(2, pagin.offset()); 
			ps.setInt(3, pagin.getPerPage());
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}
	
	
	
		
	public int countReplyCmtestion(int Parentid)throws SQLException {
		try {
			String sql = "SELECT COUNT(*)  FROM TBLFORUMCOMMENT  WHERE Parentid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,Parentid);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
			
	public ForumComment get(int comment_id)	throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT C1.*, U.Username, U.UserImageURL, COUNT(C2.Commentid) COMMENTCOUNT, COUNT(DISTINCT(V.Userid)) VOTECOUNT FROM TBLFORUMCOMMENT C1 INNER JOIN TBLUSER U ON C1.Userid=U.Userid LEFT JOIN (SELECT * FROM TBLFORUMCOMMENT WHERE Parentid=?) C2 ON C1.Commentid=C2.Parentid LEFT JOIN (SELECT * FROM TBLFORUMVOTE WHERE VOTETYPE=1) V ON C1.Commentid=V.Commentid WHERE C1.Commentid=? GROUP BY C1.Commentid, U.Userid";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, comment_id);
			ps.setInt(2, comment_id);
			rs = ps.executeQuery();
			if(rs!=null && rs.next()){
				ForumComment q=new ForumComment(rs.getInt("commentid"), rs.getDate("postdate"), rs.getString("title"), rs.getString("detail"), rs.getString("tag"), 0, rs.getInt("categoryid"), rs.getInt("userid"), false);
				q.setUsername(rs.getString("username"));
				q.setUserimageurl(rs.getString("userimageurl"));
				q.setCommentcount(rs.getInt("commentcount"));
				q.setVote(rs.getInt("VOTECOUNT"));
				return q;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}
	
//	ALREADY EXIST IN FORUMCATEGORYDAO
//	public ResultSet listQuestion(int category_id)
//			throws SQLException {
//		ResultSet rs = null;
//		String sql = "SELECT * from tblforumcomment where categoryid=?";
//		PreparedStatement ps;
//		try {
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, category_id);
//			rs = ps.executeQuery();
//			return rs;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//		return null;
//	}
	
	
	public boolean insert(ForumComment dto) throws Exception {
		try {
			String sql = "INSERT INTO tblforumcomment VALUES (NEXTVAL('seq_forumcomment'), ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, (Date) dto.getPostdate());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getDetail());
			ps.setString(4, dto.getTag());
			ps.setInt(5, dto.getParentid());
			ps.setInt(6, dto.getCategoryid());
			ps.setInt(7, dto.getUserid());
			ps.setBoolean(8, false);
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean insertAnswer(ForumComment dto) throws Exception {
		try {
			String sql = "INSERT INTO tblforumcomment VALUES (NEXTVAL('seq_forumcomment'), ?, ?, ?, ?, ?, NULL, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, (Date) dto.getPostdate());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getDetail());
			ps.setString(4, dto.getTag());
			ps.setInt(5, dto.getParentid());
			ps.setInt(6, dto.getUserid());
			ps.setBoolean(7, false);
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean insertQuestion(ForumComment dto) throws Exception {
		try {
			String sql = "INSERT INTO tblforumcomment (commentid, postdate, title, detail, tag, categoryid, userid, selected)"
					+ " values (NEXTVAL('seq_forumcomment'), ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, (Date) dto.getPostdate());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getDetail());
			ps.setString(4, dto.getTag());
			ps.setInt(5, dto.getCategoryid());
			ps.setInt(6, dto.getUserid());
			ps.setBoolean(7, dto.isSelected());
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean delete(int commentid) throws SQLException {
		try {
			String sql = "DELETE FROM TBLFORUMCOMMENT WHERE categoryid=" + commentid;
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
	
	
	
	public int countQuestionByUser(int userid) throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMCOMMENT WHERE Parentid IS NULL AND USERID="+userid;
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
	public int countQuestionByCategory(int categoryid) throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMCOMMENT WHERE Parentid IS NULL AND CATEGORYID="+categoryid;
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
	public int countComment() throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMCOMMENT WHERE Parentid IS NOT NULL";
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
	public int countCommentByUser(int userid) throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMCOMMENT WHERE Parentid IS NOT NULL AND USERID="+userid;
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
	public int countQuestion()throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMCOMMENT WHERE Parentid IS NULL";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
	public int countCommentByCategory(int categoryid) throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMCOMMENT WHERE Parentid IS NOT NULL AND CATEGORYID="+categoryid;
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return 0;
	}
	
	public String[] getAllTags() throws SQLException {
		try {
			String sql = "SELECT STRING_AGG(DISTINCT(TAGS), ', ') FROM (SELECT CAST(regexp_split_to_table(TAG, ', ') AS VARCHAR) AS TAGS FROM TBLFORUMCOMMENT) C";
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString(1).split(",");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

}
