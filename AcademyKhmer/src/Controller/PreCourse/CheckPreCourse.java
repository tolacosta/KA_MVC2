package Controller.PreCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.PreCourseDAO;
import Controller.Action;
import Controller.ActionForward;

public class CheckPreCourse implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int id= Integer.parseInt(request.getParameter("uid"));
		boolean b= false;
		PreCourseDAO dao= new PreCourseDAO();
		b= dao.checkPrecourseStudent(id);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String msg= "fail";
		if(b){
			msg= new Gson().toJson("Success");
		}
		
		response.getWriter().write(msg);
		System.out.println(msg);
		return null;
	}

}
