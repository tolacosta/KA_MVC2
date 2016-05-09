package Controller.shortcoure;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Action;
import Controller.ActionForward;
import model.dao.ShortCourseDao;
import model.dto.ShortCourse;
import model.dto.User;

public class ActionListApplicantCourses implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		try{
			
			ShortCourseDao dao = new ShortCourseDao();
			
			User users= (User)request.getSession().getAttribute("ka_user");
			
			ResultSet rs = dao.listApplicantCourses(users.getUserid());
			
			
			ArrayList<ShortCourse> arr = new ArrayList<ShortCourse>();
			ShortCourse dto = null;
			
			
			while(rs.next()){
				dto = new ShortCourse();
				dto.setCourseID(rs.getInt("id"));
				dto.setPaid(rs.getBoolean("ispaid"));
				dto.setRegisterDate(rs.getDate("register_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				dto.setCourseName(rs.getString("course"));
				dto.setShifName(rs.getString("shift"));
				dto.setTypeName(rs.getString("type"));
				dto.setUserid(rs.getInt("ka_userid"));
				dto.setId(rs.getInt("student_id"));
				arr.add(dto);
			}
			rs.close();
			request.setAttribute("s_id", dao.getStudentId(users.getUserid()));
			request.setAttribute("courses", arr);
			forward.setPath("list_applicant_courses.jsp");
			forward.setRedirect(false);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return forward;
	}
	
	

}
