package Controller.shortcoure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Action;
import Controller.ActionForward;
import model.dao.ShortCourseDao;

public class DeleteRegisteredCourse implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		new ShortCourseDao().deleteRegisteredCourse(Integer.parseInt(request.getParameter("c_id")));
		forward.setPath("list_applicant_courses.act");
		forward.setRedirect(false);
		return forward;
	}

}
