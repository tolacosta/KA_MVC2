package Controller.PlaylistAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import model.dto.Playlist;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionCreatePlaylist implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PlaylistDAO dao = new PlaylistDAO();
		Playlist dto = new Playlist();
		
		dto.setPlaylistname(request.getParameter("playlistname"));
		dto.setDescription(request.getParameter("description"));
		dto.setPublicview(Boolean.parseBoolean(request.getParameter("publicview")));
		dto.setThumbnailurl("default.png");
		dto.setUserid(((User) request.getSession().getAttribute("ka_user")).getUserid());
		
		if(dao.insert(dto)){
			response.getWriter().write("Success");
		}else{
			response.getWriter().write("Unsuccess");
		}
	
		return null;
	}
	

}
