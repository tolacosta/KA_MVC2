package Controller.PreCourse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PreCourseDAO;
import model.dto.PreCourse;
import Controller.Action;
import Controller.ActionForward;

public class ActionEditPreCourse implements Action{

	public ActionEditPreCourse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		PreCourse preCourse = new PreCourse();
		preCourse.setId(Integer.parseInt(request.getParameter("id").trim()));
		//preCourse.setUserId(Integer.parseInt(request.getParameter("").trim()));
		preCourse.setUsername(new String(request.getParameter("username").getBytes("8859_1"), "UTF-8"));
		preCourse.setEmail(request.getParameter("email").trim());
		preCourse.setTelephone(request.getParameter("phonenumber").trim());
		preCourse.setUniversity(request.getParameter("university").trim());
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date date = format.parse(request.getParameter("dateofbirth").trim());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		preCourse.setDob(sqlDate);
		preCourse.setPob(new String(request.getParameter("address").getBytes("8859_1"), "UTF-8"));
		//preCourse.setUserImage(request.getParameter("").trim());
		preCourse.setJavaCourse(request.getParameter("java").trim());
		preCourse.setWebCourse(request.getParameter("web").trim());
		preCourse.setPayment(Integer.parseInt(request.getParameter("payment").trim()));
		preCourse.setComment(new String(request.getParameter("comment").getBytes("8859_1"), "UTF-8"));
		preCourse.setGender(request.getParameter("gender").trim());
		preCourse.setYear(request.getParameter("year"));
		boolean status = new PreCourseDAO().editPreCourse(preCourse);
		response.setContentType("text/html;charset=UTF-8");
		if(status){
			forward.setPath("listprecourse.act");
			forward.setRedirect(false);
		}else{
			System.out.println("Transaction fail!");
		}
		return forward;
	}

}
