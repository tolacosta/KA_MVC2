package Controller.shortcoure;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;
import model.dao.ShortCourseDao;
import model.dto.ShortCourse;

public class GetRegisteredCourse implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ShortCourse dto = new ShortCourseDao().getRegisterdCoures(Integer.parseInt(request.getParameter("courseId")));
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("registered_course", dto);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String student = new Gson().toJson(hash);
		System.out.println(student);
		response.getWriter().write(student);
		
		return null;	
	}

}
