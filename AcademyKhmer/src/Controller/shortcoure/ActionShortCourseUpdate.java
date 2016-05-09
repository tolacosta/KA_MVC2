package Controller.shortcoure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PreCourseDAO;
import model.dao.ShortCourseDao;
import model.dto.PreCourse;
import model.dto.ShortCourse;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionShortCourseUpdate implements Action{

	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		try{
			
//			if(new ShortCourseDao().isCourseDuplicateUpdate(
//					Integer.parseInt(request.getParameter("hide_c_id")),
//					Integer.parseInt(request.getParameter("shift")))
//			){
//				System.out.println("You cannot register this course again! Please check your registered list.");
//				forward.setPath("shortcourse.act?id="+request.getParameter("id")+"&m=t");
//				forward.setRedirect(true);
//				return forward;
//			}
			
			ShortCourse dto = new ShortCourse();
			
//			dto.setFullname(request.getParameter("fullname").trim());
//			dto.setEmail(request.getParameter("email").trim());
//			dto.setPhonenumber(request.getParameter("phonenumber").trim());
//			dto.setUniversity(request.getParameter("university").trim());
//			dto.setYear(request.getParameter("year").trim());
			
//			DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
//			Date date = format.parse(request.getParameter("dob").trim());
//			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//			dto.setDob(sqlDate);
			
//			dto.setGender(request.getParameter("gender"));
//			dto.setDescirption(request.getParameter("comment"));
//			dto.setAddress(request.getParameter("address"));
			
			User users= (User)request.getSession().getAttribute("ka_user");
			
			if(Integer.parseInt(request.getParameter("old_shift")) != Integer.parseInt(request.getParameter("shift")) ){
				if(new ShortCourseDao().isShiftDuplicate(users.getUserid(),Integer.parseInt(request.getParameter("shift")))){
					System.out.println("You cannot register this time again! Please check your registered list.");
					forward.setPath("shortcourse.act?id="+request.getParameter("id")+"&m=t&t=f");
					forward.setRedirect(true);
					return forward;
				}
			}
			
			
			
			dto.setCourse(Integer.parseInt(request.getParameter("hide_c_id")));
			dto.setShift(Integer.parseInt(request.getParameter("shift")));
			dto.setId(Integer.parseInt(request.getParameter("id")));
			
			response.setContentType("text/html;charset=UTF-8");
			if(new ShortCourseDao().updateShortCourse(dto)){
				request.setAttribute("status", true);
				forward.setPath("list_applicant_courses.act");
				forward.setRedirect(false);
			}else{
				request.setAttribute("status", false);
				forward.setPath("list_applicant_courses.act");
				forward.setRedirect(false);
				
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return forward;
	}

}
