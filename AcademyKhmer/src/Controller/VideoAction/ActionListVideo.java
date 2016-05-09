package Controller.VideoAction;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.UserDAO;
import model.dao.VideoDAO;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionListVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		User u = (User) request.getSession().getAttribute("ka_user");
		int userid=u.getUserid();;
		int perpage=10, current=1, totalvideo=0, totalpage=0;
		if(request.getParameter("current")!=null){
			current = Integer.parseInt(request.getParameter("current").toString());
		}
		String q="";
		if(request.getParameter("q")!=null){
			q = request.getParameter("q").toString();
		}
		ResultSet rs = new VideoDAO().list(userid, current, perpage, q);
		totalvideo = new UserDAO().countvideos(userid, q);
		totalpage = (int) Math.ceil(Double.parseDouble(String.valueOf(totalvideo))/perpage);
		
		ArrayList<Video> arr = new ArrayList<Video>();
		while(rs.next()){
			Video dto =  new Video(rs.getInt("videoid"), rs.getString("videoname"), "", rs.getString("youtubeurl"), rs.getString("fileurl"), rs.getBoolean("publicview"), rs.getDate("postdate"), rs.getInt("userid"), rs.getInt("viewcount"));
			String description = (rs.getString("description").length()>50 ? rs.getString("description").substring(0, 49)+"..." : rs.getString("description"));
			dto.setDescription(description);
			dto.setCategorynames(rs.getString("categorynames"));
			dto.setCountcomments(rs.getInt("countcomments"));
			dto.setCountvoteminus(rs.getInt("countvoteminus"));
			dto.setCountvoteplus(rs.getInt("countvoteplus"));
			dto.setUsername(rs.getString("username"));
			arr.add(dto);
		}
		
		String video_json=new Gson().toJson(arr);
		request.setAttribute("video_json", video_json);
		request.setAttribute("totalvideo", totalvideo);
		request.setAttribute("totalpage", totalpage);
		forward.setPath("videolist.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
