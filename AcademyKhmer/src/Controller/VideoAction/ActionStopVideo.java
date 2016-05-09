package Controller.VideoAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.LogDAO;
import model.dto.Log;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionStopVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int logid=0;
		if(request.getParameter("l")!=null)
			logid=Integer.parseInt(request.getParameter("l").toString());
		
		User u = (User) request.getSession().getAttribute("ka_user");
//						u = new User();u.setViewable(true);u.setUserid(2);
		
		Log dto = new Log();
		dto.setLogid(logid);
		dto.setUserid(u.getUserid());
		
		LogDAO dao = new LogDAO();
		
		System.err.println(dao.stopWatching(dto));
		return null;
	}
}
