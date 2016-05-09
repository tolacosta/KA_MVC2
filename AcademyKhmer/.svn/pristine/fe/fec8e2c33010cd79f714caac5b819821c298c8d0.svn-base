package Controller.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.dao.UserDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionChangePassword implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//ActionForward forward= new ActionForward();
		String op=request.getParameter("myoldpwd");
		String np=request.getParameter("mynewpwd");
		
		int id=((User)request.getSession().getAttribute("ka_user")).getUserid();
		UserDAO u=new UserDAO();
			if(u.changePassword(id, op, np)){
				response.getWriter().write("Success");
				/*forward.setPath("index.act");
				System.out.println("Complete change pwd");*/
			}	
			else{
				//forward.setPath("index.act");
				response.getWriter().write("Fail");
				System.out.println("fail change");
			}
		//forward.setRedirect(false);	
		return null;
	}

}
