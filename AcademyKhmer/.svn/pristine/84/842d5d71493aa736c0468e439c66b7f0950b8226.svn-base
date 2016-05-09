package Controller.UserAction;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddUserAdmin implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
ActionForward forward = new ActionForward();
		
		User dto = new User();
		UserDAO dao = new UserDAO();
		String day=null, month=null, year= null;
		
		
		if(request.getParameter("username")!=null){
			dto.setUsername(request.getParameter("username"));
		}
		if(request.getParameter("email")!=null){
			dto.setEmail(request.getParameter("email"));
		}
		if(request.getParameter("university")!=null){
			dto.setUniversityid((Integer.parseInt(request.getParameter("university"))));
		}
		if(request.getParameter("department")!=null){
			dto.setDepartmentid((Integer.parseInt(request.getParameter("department"))));
		}
		
		if(request.getParameter("password")!=null){
			dto.setPassword(request.getParameter("password"));
		}
		if(request.getParameter("gender")!=null){
			dto.setGender(request.getParameter("gender"));
		}
		/*if(request.getParameter("day")!=null){
			day = request.getParameter("day");
		}
		if(request.getParameter("month")!=null){
			month = request.getParameter("month");
		}
		if(request.getParameter("year")!=null){
			year = request.getParameter("year");
		}
		
		if(day !=null || month!=null || year!=null){
			dto.setDateofbirth(new SimpleDateFormat("dd-MM-yyyy").parse(day+"-"+month+"-"+year));
		}*/
		//User m= new User();
		if(request.getParameter("dateofbirth")!=null){
			dto.setDateofbirth(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("dateofbirth")));
		}
		
		
		
		if(request.getParameter("usertype")!=null){
			dto.setUsertypeid(Integer.parseInt(request.getParameter("usertype")));
		}else{
			dto.setUsertypeid(2);
		}
		
		if(request.getParameter("phonenumber")!=null){
			dto.setPhonenumber(request.getParameter("phonenumber"));
		}
		
		
		dto.setUserimageurl("user/avatar.jpg");
		
		if(dao.insert(dto)){
			request.setAttribute("email", dto.getEmail());
			request.setAttribute("password", dto.getPassword());
			forward.setPath("user.act");
			forward.setRedirect(false);
		}else{
			forward.setPath("index.act");
			forward.setRedirect(true);
			System.out.println("Error");
		}
		return forward;
	}

}
