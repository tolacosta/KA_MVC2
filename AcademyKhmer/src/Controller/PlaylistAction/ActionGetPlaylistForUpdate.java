package Controller.PlaylistAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.PlaylistDAO;
import model.dto.Playlist;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetPlaylistForUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int playlistid = 0;
		if(request.getParameter("playlistid") != null)
			playlistid = Integer.parseInt(request.getParameter("playlistid"));
		
		Playlist dto = new Playlist();
		PlaylistDAO dao = new PlaylistDAO();
		dto = dao.getPlaylistForUpdate(playlistid);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String objplaylist = new Gson().toJson(dto);
		System.out.println(objplaylist);
		response.getWriter().write(objplaylist);
		
		return null;
	}

}
