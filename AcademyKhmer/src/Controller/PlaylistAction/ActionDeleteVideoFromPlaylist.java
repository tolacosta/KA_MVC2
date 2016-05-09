package Controller.PlaylistAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteVideoFromPlaylist implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	try{
			
			ActionForward forward = new ActionForward();
			PlaylistDAO dao = new PlaylistDAO();
			
			int playlistId = 0;
			if(request.getParameter("playlistid") != null){
				playlistId = Integer.parseInt(request.getParameter("playlistid").toString());
			}
			int vid = 0;
			if(request.getParameter("vid") != null){
				vid = Integer.parseInt(request.getParameter("vid").toString());
			}
			
			if(dao.deleteVideoFromPlaylist(playlistId,vid)){
				if(dao.countvideos(playlistId) == 0){
					dao.updateThumbnailToDefault(playlistId);
				}
				response.getWriter().write("s");
			}else{
				response.getWriter().write("u");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
