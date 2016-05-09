package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class ForumVoteDAO {
	Connection con;

	public ForumVoteDAO() throws ClassNotFoundException, NamingException,
			SQLException {
		con = new DBUtility().getConnect();
	}

	public int voteplus(int userid, int commentid) throws SQLException {
		return vote(userid, commentid, 1);
	}

	private int vote(int userid, int commentid, int votetype)
			throws SQLException {
		try {
			String sql = "INSERT INTO TBLFORUMVOTE VALUES(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setInt(2, commentid);
			ps.setInt(3, votetype);
			ps.executeUpdate();
		} catch (SQLException e) {
			try {
				String sql2 = "UPDATE TBLFORUMVOTE SET votetype=? WHERE userid=? AND commentid=?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, votetype);
				ps2.setInt(2, userid);
				ps2.setInt(3, commentid);
				ps2.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			String sql3 = "SELECT SUM(VoteType) FROM TBLFORUMVOTE WHERE commentid="
					+ commentid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql3);
			if (rs.next()) {
				return rs.getInt(1);
			}
			con.close();
		}
		return 0;
	}

	public int voteminus(int userid, int commentid) throws SQLException {
		return vote(userid, commentid, -1);
	}

	public int unvote(int userid, int commentid) throws SQLException {
		return vote(userid, commentid, 0);
	}

	public int countplus() throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMVOTE WHERE votetype=1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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

	public int countminus() throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM TBLFORUMVOTE WHERE votetype=-1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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
//	public int countplus(int commentid) throws SQLException{
//		try {
//			String sql = "SELECT COUNT(*) FROM TBLFORUMVOTE WHERE votetype=1 AND commentid="+commentid;
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			if(rs.next()){
//				return rs.getInt(1); 
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			con.close();
//		}
//		return 0;
//	}
//	public int countminus(int commentid) throws SQLException{
//		try {
//			String sql = "SELECT COUNT(*) FROM TBLFORUMVOTE WHERE votetype=-1 AND commentid="+commentid;
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			if(rs.next()){
//				return rs.getInt(1); 
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			con.close();
//		}
//		return 0;
//	}
	public int count(int commentid) throws SQLException{
		try {
			String sql = "SELECT SUM(VoteType) FROM TBLFORUMVOTE WHERE commentid="+commentid;
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
	public int checkuservote(int userid, int commentid) throws SQLException{
		try {
			String sql = "SELECT VP.*, VM.* FROM "
					+ "(SELECT COUNT(commentid) COUNTMINUS FROM TBLFORUMVOTE WHERE userid="+userid+" AND commentid="+commentid+" AND votetype=1) VP, "
					+ "(SELECT COUNT(commentid) COUNTPLUS FROM TBLFORUMVOTE WHERE userid="+userid+" AND commentid="+commentid+" AND votetype=-1) VM";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				if(rs.getInt(1)>0)
					return 1;	//already liked
				else if(rs.getInt(2)>0)
					return -1;	//already disliked
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	
	public boolean selectanswer(int commentid , int parentid) throws SQLException{
		try {
			
			String sql1 = "UPDATE TBLFORUMCOMMENT SET selected=false WHERE parentid = "+ parentid ;
			Statement stmt1 = con.createStatement();
			if(stmt1.executeUpdate(sql1)>0){
				String sql2 = "UPDATE TBLFORUMCOMMENT SET selected=true WHERE parentid = "+ parentid + " and commentid = "+  commentid;
				Statement stmt2 = con.createStatement();
				if(stmt2.executeUpdate(sql2)>0){
					return true;
				}
			}
			
			/*
			int parentid = 0;
			String sql = "SELECT Parentid From TBLFORUMCOMMENT WHERE selected=true AND commentid="+commentid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				parentid = rs.getInt("parentid");
				String sql1 = "UPDATE TBLFORUMCOMMENT SET selected=null WHERE parentid="+parentid;
				stmt.executeUpdate(sql1);
			}
			String sql2 = "UPDATE TBLFORUMCOMMENT SET selected=true WHERE COMMENTID="+commentid;
			Statement stmt2 = con.createStatement();
			if(stmt2.executeUpdate(sql2)>0){
				
				if(parentid != 0){
					String sql3 = "UPDATE TBLFORUMCOMMENT SET selected=false WHERE parentid = "+ parentid +" and commentid <>"+ commentid;
					Statement stmt3 = con.createStatement();
					if(stmt3.executeUpdate(sql3)>0){
						return true;
					}
				}else{
					return true;
				}
				
			}
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException, NamingException{
			//new ForumVoteDAO().selectanswer(42);
	}
	
}
