package Controller.PlaylistAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import model.dto.Playlist;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddVideoToPlst implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PlaylistDAO dao = new PlaylistDAO();
		
		int pid = Integer.parseInt(request.getParameter("pid")); 
	    int vid =  Integer.parseInt(request.getParameter("vid")); 
	   
	    
		if(dao.addVideoToPlst(pid , vid )){
			if(dao.countvideos(pid) == 1){
				dao.updateThumbnail(vid, pid);
			}
			response.getWriter().write("s");
		}else{
			response.getWriter().write("u");
		}
		
		
		return null;
	}

}
