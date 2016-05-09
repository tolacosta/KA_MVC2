package Controller.PreCourse;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PreCourseDAO;
import model.dto.PreCourse;
import Controller.Action;
import Controller.ActionForward;

public class ActionListPreCourse implements Action{

	public ActionListPreCourse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<PreCourse> preCourses = new PreCourseDAO().getAllPreCourses();
		ActionForward forward = new ActionForward();
		request.setAttribute("preCourses", preCourses);
		forward.setPath("listprecourse.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
