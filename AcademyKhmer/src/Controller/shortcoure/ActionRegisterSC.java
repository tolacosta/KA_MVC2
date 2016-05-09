package Controller.shortcoure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Action;
import Controller.ActionForward;
import model.dao.ShortCourseDao;
import model.dto.ShortCourse;
import model.dto.User;

public class ActionRegisterSC implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		ShortCourse dto = new ShortCourse();
		
		User users= (User)request.getSession().getAttribute("ka_user");
		
		if(new ShortCourseDao().isCourseDuplicate(Integer.parseInt(request.getParameter("course")),users.getUserid())){
			System.out.println("You cannot register this course again! Please check your registered list.");
			forward.setPath("shortcourse.act?s_id="+request.getParameter("s_id")+"&m=t");
			forward.setRedirect(true);
			return forward;
		}
		
		if(new ShortCourseDao().isShiftDuplicate(users.getUserid(),Integer.parseInt(request.getParameter("shift")))){
			System.out.println("You cannot register this course again! Please check your registered list.");
			forward.setPath("shortcourse.act?s_id="+request.getParameter("s_id")+"&m=t&t=f");
			forward.setRedirect(true);
			return forward;
		}
		
		dto.setCourse(Integer.parseInt(request.getParameter("course")));
		dto.setType(1);
		dto.setShift(Integer.parseInt(request.getParameter("shift")));
		dto.setUserid(users.getUserid());
		dto.setId(Integer.parseInt(request.getParameter("s_id")));
		
		response.setContentType("text/html;charset=UTF-8");
		if(new ShortCourseDao().registerSC(dto)){
			request.setAttribute("status", true);
			forward.setPath("list_applicant_courses.act");
			forward.setRedirect(false);
		}else{
			request.setAttribute("status", false);
			forward.setPath("list_applicant_courses.act");
			forward.setRedirect(false);
		}
		return forward;
	}

}
