package Controller.shortcoure;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;
import model.dao.ShortCourseDao;
import model.dto.ShortCourse;
import model.dto.User;

public class ActionGetShortCourseStudent implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User users= (User)request.getSession().getAttribute("ka_user");
		ShortCourse dto = new ShortCourseDao().getStudent(users.getUserid());
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("student", dto);
		hash.put("status", true);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String student = new Gson().toJson(hash);
		System.out.println(student);
		response.getWriter().write(student);
		return null;	}

}
