package Controller.PlaylistAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import model.dto.Playlist;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionUpdatePlaylist implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try{
		int userid = ((User) request.getSession().getAttribute("ka_user")).getUserid();
		
		int playlistid = 0;
		if(request.getParameter("playlistid")!= null)
			playlistid = Integer.parseInt(request.getParameter("playlistid"));
		
		System.out.println(playlistid);
		
		String playlistname = "";
		if(request.getParameter("playlistname")!= null)
			playlistname = request.getParameter("playlistname");
		
		String description = "";
		if(request.getParameter("description")!= null)
			description = request.getParameter("description");
		
		boolean publicview = false;
			if(request.getParameter("publicview")!= null)
				publicview = Boolean.parseBoolean(request.getParameter("publicview"));
	
		String thumbnail = "";
		if(request.getParameter("thumbnail")!= null)
			thumbnail = request.getParameter("thumbnail");
				
			
		PlaylistDAO dao = new PlaylistDAO();
		Playlist dto = new Playlist();
		
		dto.setPlaylistid(playlistid);
		dto.setPlaylistname(playlistname);
		dto.setDescription(description);
		dto.setPublicview(publicview);
		dto.setThumbnailurl(thumbnail);
		dto.setUserid(userid);
		
		System.out.println(playlistname);
		
		if(dao.update(dto)) System.out.println(1);
		else System.out.println(0);
		
		
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

}
