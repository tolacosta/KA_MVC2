package Controller.Log;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.LogDAO;
import model.dto.Log;

import Controller.Action;
import Controller.ActionForward;

public class ActionShowLogOfUniversity implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ResultSet rs = new LogDAO().listUniversity();
		ArrayList<Log> logs = new ArrayList<Log>(); 
		while(rs.next()){
			Log log = new Log();
			log.setUniversityid(rs.getInt("universityid"));
			log.setUniversityname(rs.getString("universityname"));
			log.setUsers(rs.getInt("users"));
			log.setViews(rs.getInt("views"));
			log.setDuration(rs.getString("duration"));
			logs.add(log);
		}
		request.setAttribute("logs", logs);
		
		forward.setPath("log_listuniversity.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
