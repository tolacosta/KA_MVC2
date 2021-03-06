package Controller.PreCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PreCourseDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeletePreCourse implements Action{

	public ActionDeletePreCourse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int id = Integer.parseInt(request.getParameter("id").trim());
		boolean status = new PreCourseDAO().deletePreCourse(id);
		if(status){
			forward.setPath("");
			forward.setRedirect(false);
		}else{
			System.out.println("Transaction fail!");
		}
		return null;
	}

}
