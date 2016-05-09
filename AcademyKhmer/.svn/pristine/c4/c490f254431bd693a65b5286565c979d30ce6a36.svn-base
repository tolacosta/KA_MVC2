package Controller.Admin;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.dao.VideoDAO;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionAdminDashboard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward= new ActionForward();
		
		VideoDAO dao= new VideoDAO();
		
		int countalluser= dao.countAllUsers();
		int countallplaylist =  dao.countAllPlaylists();
		int countallquestion= dao.countAllForums();
		int countallvideo= dao.countallvideos();
		
		request.setAttribute("countalluser", countalluser);
		request.setAttribute("countallplaylist", countallplaylist);
		request.setAttribute("countallquestion", countallquestion);
		request.setAttribute("countallvideo", countallvideo);
		
		ResultSet rs = new VideoDAO().list();
		ArrayList<Video> arr = new ArrayList<Video>();
		
		while(rs.next()){
			Video dto =  new Video();			
			dto.setVideoid(rs.getInt("videoid"));
			dto.setVideoname(rs.getString("videoname"));
			dto.setYoutubeurl(rs.getString("youtubeurl"));
			dto.setPostdate(rs.getDate("postdate"));
			dto.setViewcounts(rs.getInt("viewcount"));
			dto.setUsername(rs.getString("username"));
			
			arr.add(dto);
		}
		rs.close();
		request.setAttribute("videodto", arr);
		
		
		ResultSet rsuser= new UserDAO().getLastestUsers();
		ArrayList<User> usr= new ArrayList<User>();
		
		while(rsuser.next()){
			User userdto= new User();
			userdto.setUserid(rsuser.getInt("userid"));
			userdto.setUsername(rsuser.getString("username"));
			userdto.setEmail(rsuser.getString("email"));
			userdto.setUserimageurl(rsuser.getString("userimageurl"));
			usr.add(userdto);
		}
		rsuser.close();
		request.setAttribute("listuserlastest", usr);
		
		forward.setPath("dashboard.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
