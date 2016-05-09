package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import ultilities.ClsPagination;
import model.dto.Video;

public class VideoDAO {
	Connection con;
	public VideoDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
public ResultSet getRelateVideos(String v) throws SQLException{
	try {
		ResultSet rs = null;
		//String sql = "SELECT * FROM	tblvideo WHERE videoid IN (SELECT tblcategoryvideo.categoryid FROM tblcategoryvideo INNER JOIN tblcategory ON tblcategory.categoryid = tblcategoryvideo.categoryid WHERE tblcategoryvideo.videoid = ?) ORDER BY random() limit 10";
		
		String sql="SELECT videorelate.*,tbu.username,tbu.userimageurl,tbu.usertypeid, tbu.point,tbu.email, tbcat.categoryname from "
				+ "(SELECT tvd.videoname, tvd.videoid, tvd.youtubeurl, tcv.categoryid, tvd.postdate, tvd.viewcount, tvd.publicview, tvd.userid from tblvideo tvd "
				+ "INNER JOIN tblcategoryvideo tcv on tcv.videoid = tvd.videoid)as videorelate  "
				+ "INNER JOIN tbluser tbu on videorelate.userid= tbu.userid "
				+ "INNER JOIN tblcategory tbcat on videorelate.categoryid = tbcat.categoryid "
				+ "where tbcat.categoryname like '%"+v+"%' ORDER BY random() LIMIT 10";
		
		Statement stmt = con.createStatement();
		rs=stmt.executeQuery(sql);
		
		
		return rs;
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		con.close();
	}
	return null;

}
public int countCategoryVideos( int categoryid) throws SQLException{
	try {
		ResultSet rs = null;
		String sql = "SELECT COUNT(V.videoid) FROM TBLVIDEO V inner join tblcategoryvideo c on v.videoid=c.videoid where c.categoryid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, categoryid);
		rs=ps.executeQuery();
		if(rs.next())
			return rs.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		con.close();
	}
	return 0;
}	

public ResultSet categoryVideo(int current, int perpage, int categoryid) throws SQLException{
		
		try {
			ResultSet rs = null;
			String sql = "SELECT V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS " 
					+"FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
							+"inner JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT inner JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid where CV.categoryid=? GROUP BY CV.videoid) CC ON CC.videoid=V.videoid "  
							+"LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID  "
							+"LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
							+"LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID  "
							 +"GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES "
					+ "OFFSET ? LIMIT ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,categoryid);
			ps.setInt(2, (current-1)*perpage);
			ps.setInt(3, perpage);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}

public int countSearchVideos( String q) throws SQLException{
	try {
		ResultSet rs = null;
		String sql = "SELECT COUNT(V.videoid) FROM TBLVIDEO V WHERE  lower(V.Videoname) LIKE lower('%"+q+"%')";
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
	public ResultSet searchList(int current, int perpage, String keyword) throws SQLException{
		
		try {
			ResultSet rs = null;
			String sql = "SELECT V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON CC.videoid=V.videoid "
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "WHERE lower(V.VIDEONAME) LIKE lower(?) "
					+ "GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES "
					+ "OFFSET ? LIMIT ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, "%"+keyword+"%");
			ps.setInt(2, (current-1)*perpage);
			ps.setInt(3, perpage);
			
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	
	public ResultSet list(String keyword) throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON V.videoid=CC.videoid " 
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "WHERE V.VIDEONAME LIKE ? "
					+ "GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs=ps.executeQuery();
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
	
	public ResultSet list(int userid, int current, int perpage, String keyword) throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON V.videoid=CC.videoid "
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "WHERE V.USERID=? AND lower(V.VIDEONAME) LIKE lower(?) "
					+ "GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES "
					+ "OFFSET ? LIMIT ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, "%"+keyword+"%");
			ps.setInt(3, (current-1)*perpage);
			ps.setInt(4, perpage);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet list(int userid, int current, int perpage) throws SQLException{
		return list(userid, current, perpage, "");
	}
	
	public Video get(int videoid, boolean count) throws SQLException{
		try {
			ResultSet rs = null;
			Video dto = null;
			String sql = "SELECT V.*, U.USERNAME, U.USERIMAGEURL, CC.CATEGORYNAMES, COUNT(DISTINCT C.COMMENTID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON V.videoid=CC.videoid "
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "WHERE V.VIDEOID=?  "
					+ "GROUP BY V.VIDEOID, U.USERNAME, U.USERIMAGEURL, CC.CATEGORYNAMES";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, videoid);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new Video(videoid, rs.getString("videoname"), rs.getString("description"), rs.getString("youtubeurl"), rs.getString("fileurl"), rs.getBoolean("publicview"), rs.getDate("postdate"), rs.getInt("userid"), rs.getInt("viewcount"));
				dto.setCategorynames(rs.getString("categorynames"));				
				dto.setCountcomments(rs.getInt("countcomments"));
				dto.setCountvoteminus(rs.getInt("countvoteminus"));
				dto.setCountvoteplus(rs.getInt("countvoteplus"));
				dto.setUsername(rs.getString("username"));
				dto.setUserimageurl(rs.getString("userimageurl"));
				if(count){
					Statement s2 = con.createStatement();
					s2.executeUpdate("UPDATE TBLVIDEO SET VIEWCOUNT=VIEWCOUNT+1 WHERE videoid="+videoid);
				}
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public int insert(Video dto) throws SQLException{
		try {
			String sql = "INSERT INTO TBLVIDEO VALUES(nextval('seq_video'), ?, ?, ?, ?, ?, NOW(), ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getVideoname());
			ps.setString(2, dto.getDescription());
			ps.setString(3, dto.getYoutubeurl());
			ps.setString(4, dto.getFileurl());
			ps.setBoolean(5, dto.isPublicview());
			ps.setInt(6, dto.getUserid());
			ps.setInt(7, dto.getViewcounts());
			if(ps.executeUpdate()>0){
				ResultSet rs = ps.getGeneratedKeys();
		    	rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	
	public boolean update(Video dto) throws SQLException{
		try {
			String sql = "UPDATE TBLVIDEO SET videoname=?, description=?, youtubeurl=?, fileurl=?, publicview=?, viewcount=? WHERE videoid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getVideoname());
			ps.setString(2, dto.getDescription());
			ps.setString(3, dto.getYoutubeurl());
			ps.setString(4, dto.getFileurl());
			ps.setBoolean(5, dto.isPublicview());
			ps.setInt(6, dto.getViewcounts());
			ps.setInt(7, dto.getVideoid());
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean delete(int videoid) throws SQLException{
		try {
			String sql = "DELETE FROM TBLVIDEO WHERE videoid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, videoid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public int countvoteplus(int videoid) throws SQLException{
		try {
			String sql = "SELECT COUNT(userid) FROM TBLVOTE WHERE votetype=1 AND userid="+videoid;
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
	
	public int countvoteminus(int videoid) throws SQLException{
		try {
			String sql = "SELECT COUNT(userid) FROM TBLVOTE WHERE votetype=-1 AND userid="+videoid;
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
	
	public int count() throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT COUNT(V.videoid) FROM TBLVIDEO V";
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
	
	public int countcomments(int videoid) throws SQLException{
		try {
			String sql = "SELECT COUNT(videoid) FROM TBLCOMMENT WHERE videoid="+videoid;
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
	
	public ResultSet listcomments(int videoid) throws SQLException{
		try {
			ResultSet rs = null;			
			String sql = "SELECT CM.*, V.videoname, U.username, U.userimageurl FROM TBLCOMMENT CM INNER JOIN TBLVIDEO V ON CM.videoid=V.videoid INNER JOIN TBLUSER U ON CM.userid=U.userid WHERE CM.videoid=? ORDER BY commentdate DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, videoid);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	

	
	public boolean addtocategory(int videoid, int categoryid) throws SQLException{
		try {
			String sql = "INSERT INTO TBLCATEGORYVIDEO VALUES(?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, videoid);
			ps.setInt(2, categoryid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean removefromcategory(int videoid, int categoryid) throws SQLException{
		try {
			String sql = "DELETE FROM TBLCATEGORYVIDEO WHERE videoid=? AND categoryid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, videoid);
			ps.setInt(2, categoryid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean removefromcategory(int videoid) throws SQLException{
		try {
			String sql = "DELETE FROM TBLCATEGORYVIDEO WHERE videoid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, videoid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public ResultSet listplaylist(int videoid) throws SQLException{
		try {
			ResultSet rs = null;
			String sql = "SELECT P.*, U.username, COUNT(DISTINCT PD.videoid) countvideos FROM TBLPLAYLIST P INNER JOIN TBLUSER U ON P.userid=U.userid "
					+ "LEFT JOIN TBLPLAYlISTDETAIL PD ON P.playlistid=PD.playlistid "
					+ "WHERE PD.videoid=? GROUP BY P.playlistid, U.username";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, videoid);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	public ResultSet topvVoteNRecent() throws SQLException{
		try {
			ResultSet rs = null;
			//String sql = "select * from (select DISTINCT v.*,count(VO.videoid) from  tblvote vo inner join tblvideo v on vo.videoid=v.videoid where vo.votetype=1 group by v.videoid,VO.videoid order by count(VO.videoid) desc limit 10)  c1 union all (select DISTINCT v.*,1 as count from  tblvote vo inner join tblvideo v on vo.videoid=v.videoid order by postdate desc limit 10)";
			String sql="select * from (select DISTINCT v.*,count(VO.videoid), u.username, u.userimageurl from  "
					+ "tblvote vo inner join tblvideo v on vo.videoid=v.videoid inner join tbluser u on v.userid=u.userid where vo.votetype=1 "
					+ "group by v.videoid,VO.videoid,u.userid order by count(VO.videoid) desc limit 10)  c1 "
					+ "union all (select DISTINCT v.*,1 as count, u.username, u.userimageurl from  tblvote vo "
					+ "inner join tblvideo v on vo.videoid=v.videoid inner join tbluser u on v.userid=u.userid order by postdate desc limit 10)";
			
			Statement ps = con.createStatement();
			rs=ps.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}

	
	
	
	public int countplaylist(int videoid) throws SQLException{
		try {
			String sql = "SELECT COUNT(playlistid) FROM TBLPLAYLISTDETAIL WHERE videoid="+videoid;
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
	
	public boolean addtoplaylist(int videoid, int playlistid, int index) throws SQLException{
		try {
			String sql = "INSERT INTO TBLPLAYLISTDETAIL VALUES(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, playlistid);
			ps.setInt(2, videoid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean removefromplaylist(int videoid, int playlistid) throws SQLException{
		try {
			String sql = "DELETE FROM TBLPLAYLISTDETAIL WHERE playlistid=? AND videoid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, playlistid);
			ps.setInt(2, videoid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public boolean updatevideoindex(int videoid, int playlistid, int index) throws SQLException{
		try {
			String sql = "UPDATE TBLPLAYLISTDETAIL SET INDEX=? WHERE playlistid=? AND videoid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			ps.setInt(2, playlistid);
			ps.setInt(3, videoid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ResultSet listUserVideos(int userid , String keyword , ClsPagination p) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "SELECT V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON V.videoid=CC.videoid "
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "WHERE V.USERID=? AND lower(V.VIDEONAME) LIKE lower(?) "
					+ "GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES "
					+ "OFFSET ? LIMIT ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, "%"+keyword+"%");
			ps.setInt(3, p.offset());
			ps.setInt(4, p.getPerPage());
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public int countUserVideos( int userid , String search) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "SELECT COUNT(videoid) FROM tblvideo WHERE videoname like ? and userid = ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%"+search+"%");
			ps.setInt(2, userid);
			ResultSet rs = ps.executeQuery();
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
	
	
public ResultSet listAllVideoToAddToPlaylist(ClsPagination pagin, String keyword) throws SQLException, ClassNotFoundException, NamingException{
		
		try {
			con = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "SELECT V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON CC.videoid=V.videoid "
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "WHERE lower(V.VIDEONAME) LIKE lower(?) "
					+ "GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES "
					+ "OFFSET ? LIMIT ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, "%"+keyword+"%");
			ps.setInt(2, pagin.offset());
			ps.setInt(3, pagin.getPerPage());
			
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}

public int countVideoToAddToPlaylist( String search) throws SQLException, ClassNotFoundException, NamingException{
	try {
		con = new DBUtility().getConnect();
		String sql = "SELECT COUNT(videoid) FROM tblvideo WHERE videoname like ? ";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, "%"+search+"%");
		ResultSet rs = ps.executeQuery();
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

	public int countallvideos()throws SQLException, ClassNotFoundException, NamingException{
		try{
			con= new DBUtility().getConnect();
			String sql="Select count(*) from tblvideo";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	public int countAllUsers()throws SQLException, ClassNotFoundException, NamingException{
		try{
			con= new DBUtility().getConnect();
			String sql="Select count(*) from tbluser";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	public int countAllPlaylists()throws SQLException, ClassNotFoundException, NamingException{
		try{
			con= new DBUtility().getConnect();
			String sql="Select count(*) from tblplaylist";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	public int countAllForums()throws SQLException, ClassNotFoundException, NamingException{
		try{
			con= new DBUtility().getConnect();
			String sql="Select count(*) from tblforumcomment";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
}
