package Controller.shortcoure;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Action;
import Controller.ActionForward;
import model.dao.ShortCourseDao;
import model.dto.ShortCourse;
import model.dto.User;

public class ActionListAllApplicantsCoruses implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		try{
			
			ShortCourseDao dao = new ShortCourseDao();
			
			ResultSet rs = dao.listApplicantCourses();
			
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
				dto.setGender(rs.getString("gender"));
				dto.setFullname(rs.getString("fullname"));
				dto.setEmail(rs.getString("email"));
				dto.setPhonenumber(rs.getString("phonenumber"));
				arr.add(dto);
			}
			request.setAttribute("courses", arr);
			forward.setPath("list_all_applicants_coursess.jsp");
			forward.setRedirect(false);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return forward;
	}
	
	

}