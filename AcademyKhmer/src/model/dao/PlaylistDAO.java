package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import ultilities.ClsPagination;
import model.dto.Playlist;

public class PlaylistDAO {
	Connection con;
	public PlaylistDAO() throws ClassNotFoundException, NamingException, SQLException{
		//con = new DBUtility().getConnect();
	}
	 
	public ResultSet list(ClsPagination pagin , Playlist dto) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "SELECT P.*, U.username, COUNT(DISTINCT PD.videoid) countvideos FROM TBLPLAYLIST P INNER JOIN TBLUSER U ON P.userid=U.userid "
					+ "LEFT JOIN TBLPLAYlISTDETAIL PD ON P.playlistid=PD.playlistid "
					+ "WHERE P.playlistname LIKE ? and  U.Userid = ? GROUP BY P.playlistid, U.username order by  P.playlistid desc offset ? limit ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+dto.getPlaylistname()+"%");
			ps.setInt(2, dto.getUserid());
			ps.setInt(3, pagin.offset());
			ps.setInt(4, pagin.getPerPage());
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	// List video in playlist
	public ResultSet listVideoInPlaylist(int playlistid , ClsPagination pagin ) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "SELECT PL.*, V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS, PD.INDEX ,V.publicview  ispublic "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON V.videoid=CC.videoid "
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "INNER JOIN TBLPLAYLISTDETAIL PD ON PD.VIDEOID=V.VIDEOID "
					+ "INNER JOIN tblplaylist PL ON PD.PLAYLISTID = PL.playlistid "
					+ "WHERE PD.PLAYLISTID=? "
					+ "GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES, PD.INDEX , PL.playlistid "
					+ "ORDER BY PD.INDEX  offset ? limit ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, playlistid);
			ps.setInt(2, pagin.offset());
			ps.setInt(3, pagin.getPerPage());
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public String getPlaylistName(int playlistid)throws SQLException, ClassNotFoundException, NamingException{
		try{	
		con = new DBUtility().getConnect();
			String pname= "";
			ResultSet rs = null;
			String sql = "select playlistname from tblplaylist where playlistid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, playlistid);
			rs = ps.executeQuery();
			if(rs.next()){
				pname = rs.getString("playlistname");
			}
			
			return pname;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet listVideo(int playlistid) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "SELECT V.*, U.USERNAME, CC.CATEGORYNAMES, COUNT(DISTINCT C.VIDEOID) COUNTCOMMENTS, COUNT(DISTINCT VP.*) COUNTVOTEPLUS, COUNT(DISTINCT VM.*) COUNTVOTEMINUS, PD.INDEX "
					+ "FROM TBLVIDEO V LEFT JOIN TBLUSER U ON V.USERID=U.USERID "
					+ "LEFT JOIN (SELECT CV.videoid, string_agg(CT.categoryname, ', ') CATEGORYNAMES FROM TBLCATEGORY CT LEFT JOIN TBLCATEGORYVIDEO CV ON CT.categoryid=CV.categoryid GROUP BY CV.videoid) CC ON V.videoid=CC.videoid "
					+ "LEFT JOIN TBLCOMMENT C ON V.VIDEOID=C.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=1) VP ON V.VIDEOID=VP.VIDEOID "
					+ "LEFT JOIN (SELECT * FROM TBLVOTE WHERE VOTETYPE=-1) VM ON V.VIDEOID=VM.VIDEOID "
					+ "INNER JOIN TBLPLAYLISTDETAIL PD ON PD.VIDEOID=V.VIDEOID "
					+ "WHERE PD.PLAYLISTID=? "
					+ "GROUP BY V.VIDEOID, U.USERNAME, CC.CATEGORYNAMES, PD.INDEX "
					+ "ORDER BY PD.INDEX";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, playlistid);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet listplaylistname(Playlist dto) throws SQLException, ClassNotFoundException, NamingException{
		con = new DBUtility().getConnect();
		try {
			ResultSet rs = null;
			String sql = "select playlistid , playlistname,publicview from tblplaylist where playlistname like  ? and userid = ?  order by playlistid desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+dto.getPlaylistname()+"%");
			ps.setInt(2, dto.getUserid());
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	
	//list public playlist
	public ResultSet listplaylistbyPublicView(boolean publicview) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "select playlistid,playlistname  from tblplaylist where publicview= ? order by playlistid desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1,publicview);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	//List admin video
	public ResultSet listplaylistbyAdmin(boolean publicview) throws SQLException, ClassNotFoundException, NamingException{
	try {
	con = new DBUtility().getConnect();
	String sql = "select playlistid, playlistname from tblplaylist P inner join tbluser U on P.userid=U.userid inner join tblusertype UT on U.usertypeid=UT.usertypeid where publicview=? and UT.userable=true order by playlistid desc";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setBoolean(1,publicview);
	ResultSet rs = ps.executeQuery();
	return rs;
	} catch (SQLException e) {
	e.printStackTrace();
	}finally{
	con.close();
	}
	return null;
	}
	
	
	// Validate add video to playlist
	public ResultSet listplaylistdetail(int userid) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "select D.playlistid , D.videoid , U.userid from TBLPLAYlISTDETAIL D "
					+ " INNER JOIN TBLPLAYLIST L ON D.playlistid = L.playlistid "
					+ " INNER JOIN TBLUSER U ON L.userid = L.userid "
					+ " Where U.Userid = ? order by D.Playlistid desc ";
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
	
	// Validate add video to playlist detail
		public ResultSet listplaylistdetail(int userid , int playlistid) throws SQLException, ClassNotFoundException, NamingException{
			try {
				con = new DBUtility().getConnect();
				ResultSet rs = null;
				String sql = "select D.playlistid , D.videoid , U.userid from TBLPLAYlISTDETAIL D "
						+ " INNER JOIN TBLPLAYLIST L ON D.playlistid = L.playlistid "
						+ " INNER JOIN TBLUSER U ON L.userid = L.userid "
						+ " Where U.Userid = ? and D.playlistid = ? order by D.Playlistid desc ";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, userid);
				ps.setInt(2, playlistid);
				rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				con.close();
			}
			return null;
		}
	
	public Playlist get(int playlistid) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "SELECT P.*, U.username, COUNT(DISTINCT PD.videoid) countvideos FROM TBLPLAYLIST P INNER JOIN TBLUSER U ON P.userid=U.userid "
					+ "LEFT JOIN TBLPLAYlISTDETAIL PD ON P.playlistid=PD.playlistid "
					+ "WHERE P.playlistid="+playlistid+" GROUP BY P.playlistid, U.username";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				Playlist dto = new Playlist(playlistid, rs.getString("playlistname"), rs.getString("description"), rs.getInt("userid"), rs.getString("thumbnailurl"), rs.getBoolean("publicview"));
				dto.setUsername(rs.getString("username"));
				dto.setCountvideos(rs.getInt("countvideos"));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	//get playplist for update
	public Playlist getPlaylistForUpdate(int playlistid) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "select * from tblplaylist where playlistid = "+playlistid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				Playlist dto = new Playlist(playlistid, rs.getString("playlistname"), rs.getString("description"), rs.getInt("userid"), rs.getString("thumbnailurl"), rs.getBoolean("publicview"));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public boolean addVideoToPlst(int pid , int vid ) throws SQLException, ClassNotFoundException, NamingException{
		try {  
			con = new DBUtility().getConnect();
			PreparedStatement pstmt=con.prepareStatement("select max(index) from tblplaylistdetail");
			ResultSet rs = pstmt.executeQuery();
			int num = 1;
			if(rs.next())
				num =rs.getInt(1)+1;
			String sql = "INSERT INTO tblplaylistdetail VALUES( ?, ? , "+num+" )";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,pid );
			ps.setInt(2, vid );
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean insert(Playlist dto) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "INSERT INTO TBLPLAYLIST VALUES(nextval('seq_playlist'), ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPlaylistname());
			ps.setString(2, dto.getDescription());
			ps.setInt(3, dto.getUserid());
			ps.setString(4, dto.getThumbnailurl());
			ps.setBoolean(5, dto.isPublicview());
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean update(Playlist dto) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "UPDATE TBLPLAYLIST SET playlistname=?, description=?, thumbnailurl=?, publicview=? WHERE playlistid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPlaylistname());
			ps.setString(2, dto.getDescription());
			ps.setString(3, dto.getThumbnailurl());
			ps.setBoolean(4, dto.isPublicview());
			ps.setInt(5, dto.getPlaylistid());
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean delete(int playlistid) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "DELETE FROM TBLPLAYLIST WHERE playlistid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, playlistid);
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
	
	
	
	public int count(String keyword) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "SELECT COUNT(playlistid) FROM TBLPLAYLIST where playlistname like ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
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
	
	// count user's playlist
		public int countUserPlaylist(String keyword, int userid) throws SQLException, ClassNotFoundException, NamingException{
			try {
				con = new DBUtility().getConnect();
				String sql = "SELECT COUNT(playlistid) FROM TBLPLAYLIST where playlistname like ? and userid = ?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, "%"+keyword+"%");
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
	
	public int countvideos(int playlistid) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "SELECT COUNT(videoid) FROM TBLPLAYLISTDETAIL WHERE playlistid="+playlistid;
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
	public ResultSet recommendPlaylist() throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			ResultSet rs = null;
			//String sql = "select * from tblplaylist p inner join tblplaylistdetail d on p.playlistid=d.playlistid where userid=1 limit 10";
			/*String sql= "select p.playlistid, p.playlistname,p.description, p.publicview,p.userid,p.thumbnailurl, d.videoid,u.username, u.userimageurl from tbluser u "
					+ "INNER JOIN tblplaylist p on u.userid= p.userid "
					+ "inner join tblplaylistdetail d on p.playlistid=d.playlistid where p.userid=1 limit 10";*/
			String sql= "select p.playlistid, p.playlistname,p.description, p.publicview,p.userid,p.thumbnailurl,u.username, u.userimageurl from tbluser u "
					+ "INNER JOIN tblplaylist p on u.userid= p.userid "
					+ "where p.userid=1 limit 10";
			PreparedStatement ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}	
	
	public boolean deleteVideoFromPlaylist(int playlistid , int vid) throws SQLException, ClassNotFoundException, NamingException{
		try {
			con = new DBUtility().getConnect();
			String sql = "DELETE FROM tblplaylistdetail WHERE playlistid=? and videoid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, playlistid);
			ps.setInt(2, vid);
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
	
	
	public boolean updateThumbnail(int vid , int pid) throws SQLException, ClassNotFoundException, NamingException{
		con = new DBUtility().getConnect();
		try {
			String sql = "UPDATE TBLPLAYLIST SET  thumbnailurl=(select youtubeurl from tblvideo where videoid=? ) WHERE playlistid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, vid);
			ps.setInt(2, pid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
	public boolean updateThumbnailToDefault(int pid) throws SQLException, ClassNotFoundException, NamingException{
		con = new DBUtility().getConnect();
		try {
			String sql = "UPDATE TBLPLAYLIST SET  thumbnailurl='default.png' WHERE playlistid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pid);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}
	
}
