package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.PlaylistDAO;
import model.dao.VideoDAO;
import model.dto.PlayListForDashboard;
import model.dto.Playlist;
import model.dto.User;
import model.dto.Video;

public class ActionDashboard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	PlaylistDAO a=new PlaylistDAO();
	ResultSet rs=a.recommendPlaylist();
	ArrayList<PlayListForDashboard> arr = new ArrayList<PlayListForDashboard>();
	PlayListForDashboard dto = new PlayListForDashboard();
	while(rs.next()){
		//System.out.println("123"); 
		dto = new PlayListForDashboard();
		dto.setPlaylistid(rs.getInt("playlistid"));
		dto.setPlaylistname(rs.getString("playlistname"));
		dto.setDescription(rs.getString("description"));
		dto.setUserid(rs.getInt("userid"));
		dto.setThumbnailurl(rs.getString("thumbnailurl"));
		dto.setPublicview(rs.getBoolean("publicview"));
		//dto.setVideoid(rs.getInt("videoid"));
		dto.setUsername(rs.getString("username" ));
		dto.setUserimageurl(rs.getString("userimageurl"));
		
		arr.add(dto);
		}
	
	
	ResultSet rs1=new VideoDAO().topvVoteNRecent();
	
	ArrayList<Video> arr1 = new ArrayList<Video>();
	Video dto1 = new Video();
	
	while(rs1.next()){
		 dto1 =  new Video(rs1.getInt("videoid"), rs1.getString("videoname"), rs1.getString("description"), rs1.getString("youtubeurl"), rs1.getString("fileurl"), rs1.getBoolean("publicview"), rs1.getDate("postdate"), rs1.getInt("userid"), rs1.getInt("viewcount"));
		 
		 dto1.setUsername(rs1.getString("username"));
		 dto1.setUserimageurl(rs1.getString("userimageurl"));
		 
		 
		 
		arr1.add(dto1);
		}
	
	
	
	HashMap<String, Object> hash = new HashMap<String,Object>();
	hash.put("playlist", arr);
	
	hash.put("video", arr1);
	/*******/

	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	
	String news_json=new Gson().toJson(hash);
	System.out.println(news_json);
	response.getWriter().write(news_json);
	
	
	
	
		return null;
	}

}
