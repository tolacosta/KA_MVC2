package Controller.Log;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.LogDAO;
import model.dto.Log;

import Controller.Action;
import Controller.ActionForward;

public class ActionShowLogOfDepartmentInUniversity implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int universityid = Integer.parseInt(request.getParameter("univid"));
		ResultSet rs = new LogDAO().listDeparmentByUniversity(universityid);

		ArrayList<Log> logs = new ArrayList<Log>(); 
		while(rs.next()){
			Log log = new Log();
			log.setDepartmentid(rs.getInt("departmentid"));
			log.setDepartmentname(rs.getString("departmentname"));
			log.setUsers(rs.getInt("users"));
			log.setViews(rs.getInt("views"));
			log.setDuration(rs.getString("duration"));
			logs.add(log);
		}
		request.setAttribute("logs", logs);

		forward.setPath("log_listdepartment.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
