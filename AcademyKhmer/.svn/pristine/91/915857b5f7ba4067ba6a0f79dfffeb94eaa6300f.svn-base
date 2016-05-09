package Controller.CategoryAction;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VideoDAO;
import model.dto.Video;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;


public class ActionCategoryVideo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int categoryid=Integer.parseInt(request.getParameter("category"));
		int perpage=3, current=1, totalvideo=0, totalpage=0;
		if(request.getParameter("current")!=null){
			current = Integer.parseInt(request.getParameter("current").toString());
		}
		System.out.println(current+" "+perpage);
		ResultSet rs = new VideoDAO().categoryVideo( current, perpage, categoryid);
		totalvideo = new VideoDAO().countCategoryVideos(categoryid);
		totalpage = (int) Math.ceil(Double.parseDouble(String.valueOf(totalvideo))/perpage);
		ActionForward forward=new ActionForward();
		//totalvideo = new VideoDAO().
		//totalpage = (int) Math.ceil(Double.parseDouble(String.valueOf(totalvideo))/perpage);
		
		ArrayList<Video> arr = new ArrayList<Video>();
		while(rs.next()){
			System.out.println("has");
			Video dto =  new Video(rs.getInt("videoid"), rs.getString("videoname"), rs.getString("description"), rs.getString("youtubeurl"), rs.getString("fileurl"), rs.getBoolean("publicview"), rs.getDate("postdate"), rs.getInt("userid"), rs.getInt("viewcount"));
			dto.setCategorynames(rs.getString("categorynames"));
			dto.setCountcomments(rs.getInt("countcomments"));
			dto.setCountvoteminus(rs.getInt("countvoteminus"));
			dto.setCountvoteplus(rs.getInt("countvoteplus"));
			dto.setUsername(rs.getString("username"));
			
			arr.add(dto);
		}
		
		String video_json=new Gson().toJson(arr);
		System.out.println(video_json);
		request.setAttribute("video_json", video_json);
		request.setAttribute("totalvideo", totalvideo);
		request.setAttribute("totalpage", totalpage);
		forward.setPath("_category.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
