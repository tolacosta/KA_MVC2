package Controller.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionCheckEmail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User dto= new User();
		UserDAO dao= new UserDAO();
		
		if(request.getParameter("txtemail")!=null){
			dto.setEmail(request.getParameter("txtemail"));
		}
		
		boolean mdto = dao.checkEmail(dto.getEmail());
		if(mdto==true){
			response.getWriter().write("Success");
			
		}else{
			response.getWriter().write("Fail");
			
		}
		return null;
	}

}
