package Controller.PlaylistAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeletePlaylist implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			
			ActionForward forward = new ActionForward();
			PlaylistDAO dao = new PlaylistDAO();
			
			int playlistId = 0, page = 1;
			if(request.getParameter("playlistid") != null){
				playlistId = Integer.parseInt(request.getParameter("playlistid").toString());
			}
			if(request.getParameter("page") != null){
				page = Integer.parseInt(request.getParameter("page").toString());
			}
			
			if(dao.delete(playlistId)){
				System.out.println("Success" + page);
			}else{
				System.out.println("UNSuccess");
			}
			
			System.out.println(playlistId);
			System.out.println(page);
			
			forward.setRedirect(true);
			forward.setPath("playlist.act");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
