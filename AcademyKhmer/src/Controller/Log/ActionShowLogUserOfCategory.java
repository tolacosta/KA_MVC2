package Controller.Log;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.LogDAO;
import model.dto.Log;

import Controller.Action;
import Controller.ActionForward;

public class ActionShowLogUserOfCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int categoryid = Integer.parseInt(request.getParameter("cateid"));
		ResultSet rs = new LogDAO().listUserInCategory(categoryid);
		ArrayList<Log> logs = new ArrayList<Log>(); 
		while(rs.next()){
			Log log = new Log();
			log.setUserid(rs.getInt("userid"));
			log.setUsername(rs.getString("username"));
			log.setViews(rs.getInt("views"));
			log.setDuration(rs.getString("duration"));
			logs.add(log);
		}
		request.setAttribute("logs", logs);

		forward.setPath("log_listuser.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
