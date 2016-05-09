package Controller.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Action;
import Controller.ActionForward;
import model.dao.UserDAO;
import model.dto.User;

public class ActionLogIn implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User u=new User();
		UserDAO m=new UserDAO();
		
		
		if(request.getParameter("exampleInputEmail1")!=null){
			u.setEmail(request.getParameter("exampleInputEmail1"));
		}
		
		if(request.getParameter("exampleInputPassword1")!=null){
			u.setPassword(request.getParameter("exampleInputPassword1"));
		}
		//System.out.println(u.getEmail()+u.getPassword());
		User x=m.Login(u.getEmail(),u.getPassword());

		if(x!=null){
			x.setCoverphoto(m.getCover(x.getUserid()));
			System.err.println(x.getCoverphoto());
			request.getSession().setAttribute("ka_user",x);
			response.getWriter().write("Success");
			}
		else{
			response.getWriter().write("Fail");
			}
			return null;
		
	}

}
