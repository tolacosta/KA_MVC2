package Controller.Log;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.LogDAO;
import model.dto.Log;

import Controller.Action;
import Controller.ActionForward;

public class ActionShowListCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ResultSet rs = new LogDAO().listCategory();
		ArrayList<Log> logs = new ArrayList<Log>(); 
		while(rs.next()){
			Log log = new Log();
			log.setCategoryid(rs.getInt("categoryid"));
			log.setCategoryname(rs.getString("categoryname"));
			log.setViews(rs.getInt("views"));
			log.setDuration(rs.getString("duration"));
			logs.add(log);
		}
		request.setAttribute("logs", logs);
		
		forward.setPath("log_listcategory.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
