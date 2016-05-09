package Controller.Admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.VideoDAO;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionCountUserVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		VideoDAO dao= new VideoDAO();
		int countalluser= dao.countAllUsers();
		int countallplaylist =  dao.countAllPlaylists();
		int countallquestion= dao.countAllForums();
		int countallvideo= dao.countallvideos();
		
		Video dto= new Video();
		dto.setCountallplaylist(countallplaylist);
		dto.setCountalluser(countalluser);
		dto.setCountallquestion(countallquestion);
		dto.setCountallvideo(countallvideo);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String json= new Gson().toJson(dto);
		
		response.getWriter().write(json);
		
		return null;
	}

}
