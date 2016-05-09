package Controller.VideoAction;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VideoDAO;
import model.dto.Video;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionListAllVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResultSet rs = new VideoDAO().list();
		ArrayList<Video> arr = new ArrayList<Video>();
		while(rs.next()){
			Video dto =  new Video(rs.getInt("videoid"), rs.getString("videoname"), rs.getString("description"), rs.getString("youtubeurl"), rs.getString("fileurl"), rs.getBoolean("publicview"), rs.getDate("postdate"), rs.getInt("userid"), rs.getInt("viewcount"));
			dto.setCategorynames(rs.getString("categorynames"));
			dto.setCountcomments(rs.getInt("countcomments"));
			dto.setCountvoteminus(rs.getInt("countvoteminus"));
			dto.setCountvoteplus(rs.getInt("countvoteplus"));
			dto.setUsername(rs.getString("username"));
			
			arr.add(dto);
		}
		rs.close();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String video_json = new Gson().toJson(arr);
		response.getWriter().write(video_json);
		return null;
	}
}
