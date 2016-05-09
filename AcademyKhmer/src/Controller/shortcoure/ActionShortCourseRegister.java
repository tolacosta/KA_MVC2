package Controller.shortcoure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Action;
import Controller.ActionForward;
import model.dao.ShortCourseDao;
import model.dto.ShortCourse;
import model.dto.User;

public class ActionShortCourseRegister implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = new ActionForward();
		
		ShortCourse dto = new ShortCourse();

		User users= (User)request.getSession().getAttribute("ka_user");
		
		if(new ShortCourseDao().isCourseDuplicate(Integer.parseInt(request.getParameter("course")),users.getUserid())){
			System.out.println("You cannot register this course again! Please check your registered list.");
			forward.setPath("shortcourse.act?m=t");
			forward.setRedirect(true);
			return forward;
		}
		
		if(new ShortCourseDao().isShiftDuplicate(users.getUserid(),Integer.parseInt(request.getParameter("shift")))){
			System.out.println("You cannot register this course again! Please check your registered list.");
			forward.setPath("shortcourse.act?m=t&t=f");
			forward.setRedirect(true);
			return forward;
		}
		
		dto.setFullname(request.getParameter("fullname").trim());
		dto.setEmail(request.getParameter("email").trim());
		dto.setPhonenumber(request.getParameter("phonenumber").trim());
		dto.setUniversity(request.getParameter("university").trim());
		dto.setYear(request.getParameter("year").trim());
		
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date date = format.parse(request.getParameter("dob").trim());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		dto.setDob(sqlDate);
		
		dto.setGender(request.getParameter("gender"));
		dto.setDescirption(request.getParameter("comment"));
		dto.setAddress(request.getParameter("address"));
		
		dto.setCourse(Integer.parseInt(request.getParameter("course")));
		dto.setType(1);
		dto.setShift(Integer.parseInt(request.getParameter("shift")));
		dto.setUserid(users.getUserid());
		
		response.setContentType("text/html;charset=UTF-8");
		if(new ShortCourseDao().registerShortCourse(dto)){
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
