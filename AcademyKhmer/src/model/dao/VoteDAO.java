package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class VoteDAO {
	Connection con;
	public VoteDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	
	private int vote(int userid, int videoid, int votetype) throws SQLException{
		try {
			String sql = "INSERT INTO TBLVOTE VALUES(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setInt(2, videoid);
			ps.setInt(3, votetype);
			ps.executeUpdate();
		} catch (SQLException e) {
			try{
				String sql2 = "UPDATE TBLVOTE SET votetype=? WHERE userid=? AND videoid=?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, votetype);
				ps2.setInt(2, userid);
				ps2.setInt(3, videoid);
				ps2.executeUpdate();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}finally{
			String sql3 = "SELECT COUNT(*) FROM TBLVOTE WHERE videoid="+videoid+" AND votetype="+votetype;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql3);
			if(rs.next()){
				return rs.getInt(1); 
			}
			con.close();
		}
		return 0;
	}
	
	public int voteplus(int userid, int videoid)throws SQLException{
		return vote(userid, videoid, 1);
	}
	
	public int voteminus(int userid, int videoid)throws SQLException{
		return vote(userid, videoid, -1);
	}
	
	public int unvote(int userid, int videoid)throws SQLException{
		return vote(userid, videoid, 0);
	}
	
	public int countplus() throws SQLException{
		try {
			String sql = "SELECT COUNT(*) FROM TBLVOTE WHERE votetype=1";
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
	
	public int countminus() throws SQLException{
		try {
			String sql = "SELECT COUNT(*) FROM TBLVOTE WHERE votetype=-1";
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
	
	public int countplus(int videoid) throws SQLException{
		try {
			String sql = "SELECT COUNT(*) FROM TBLVOTE WHERE votetype=1 AND VIDEOID="+videoid;
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
	
	public int countminus(int videoid) throws SQLException{
		try {
			String sql = "SELECT COUNT(*) FROM TBLVOTE WHERE votetype=-1 AND VIDEOID="+videoid;
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
	
	public int checkuservote(int userid, int videoid) throws SQLException{
		try {
			String sql = "SELECT VP.*, VM.* FROM "
					+ "(SELECT COUNT(VIDEOID) COUNTMINUS FROM TBLVOTE WHERE userid="+userid+" AND videoid="+videoid+" AND votetype=1) VP, "
					+ "(SELECT COUNT(VIDEOID) COUNTPLUS FROM TBLVOTE WHERE userid="+userid+" AND videoid="+videoid+" AND votetype=-1) VM";
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
	
	
}
