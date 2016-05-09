package Controller.PreCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.PreCourseDAO;
import model.dto.PreCourse;
import Controller.Action;
import Controller.ActionForward;

public class GetPreCourseStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("uid").trim());
		PreCourse preCourse = new PreCourseDAO().getPreCourseStudent(id);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String msg= new Gson().toJson(preCourse);
		response.getWriter().write(msg);

		return null;
	}

}
