package Controller.PreCourse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PreCourseDAO;
import model.dto.PreCourse;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddPreCourse implements Action{

	public ActionAddPreCourse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		PreCourse preCourse = new PreCourse();
		preCourse.setUsername(new String(request.getParameter("username").getBytes("8859_1"), "UTF-8"));
		preCourse.setEmail(request.getParameter("email").trim());
		preCourse.setTelephone(request.getParameter("phonenumber").trim());
		preCourse.setUniversity(request.getParameter("university").trim());
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date date = format.parse(request.getParameter("dateofbirth").trim());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		preCourse.setDob(sqlDate);
		preCourse.setPob(new String(request.getParameter("address").getBytes("8859_1"), "UTF-8"));
		String java = request.getParameter("java").trim();
		String web = request.getParameter("web").trim();
		preCourse.setJavaCourse(java);
		preCourse.setWebCourse(web);
		preCourse.setPayment(0);
		preCourse.setComment(new String(request.getParameter("comment").getBytes("8859_1"), "UTF-8"));
		preCourse.setGender(request.getParameter("gender"));
		User users= (User)request.getSession().getAttribute("ka_user");
		//System.err.println(users.getUserid());
		preCourse.setUserId(users.getUserid());
		preCourse.setYear(request.getParameter("year"));
		boolean status = new PreCourseDAO().addPreCourse(preCourse);
		response.setContentType("text/html;charset=UTF-8");
		if(status){
			request.setAttribute("afteradd", "success");
			forward.setPath("precourse.jsp");
			forward.setRedirect(false);
		}else{
			request.setAttribute("afteradd", "failed");
			forward.setPath("precourse.jsp");
			forward.setRedirect(true);
			System.out.println("Transaction fail!");
		}
		return forward;
	}

}
