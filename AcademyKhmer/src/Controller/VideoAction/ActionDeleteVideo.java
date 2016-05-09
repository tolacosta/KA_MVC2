package Controller.VideoAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VideoDAO;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int videoid=0;
		if(request.getParameter("v")!=null)
			videoid=Integer.parseInt(request.getParameter("v").toString());
		
		User u = (User) request.getSession().getAttribute("ka_user");
//						u = new User();u.setDeleteable(true);u.setUserid(2);
		Video dto = new VideoDAO().get(videoid, false); 
		if(dto.getUserid()==u.getUserid() || u.isDeleteable()){
			new VideoDAO().delete(videoid);
		}
		forward.setPath("videolist.act");
		forward.setRedirect(true);
		
		return forward;
	}
}
