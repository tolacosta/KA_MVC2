package Controller.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.EncryptDecrypt;
import model.dao.UserDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionRessetPwd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String oldpwd =  request.getParameter("oldpwd");
		String newpwd =  request.getParameter("newpwd");
		String email  =  request.getParameter("email");
		
		UserDAO dao=new UserDAO();
		if(dao.ressetPassword(email, EncryptDecrypt.decrypt(oldpwd), newpwd)){
			response.getWriter().write("Resset Success");
			System.out.println("Resset Success");
		}else{
			response.getWriter().write("Resset fail");
			System.out.println("Resset fail");
		}
		
		return null;
	}
	
	

}
