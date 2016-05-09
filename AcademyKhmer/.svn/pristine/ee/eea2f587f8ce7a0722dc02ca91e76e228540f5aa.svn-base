package Controller.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionSignupLogin implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User u=new User();
		UserDAO m=new UserDAO();
		ActionForward forward= new ActionForward();
		
		if(request.getParameter("email")!=null){
			u.setEmail(request.getParameter("email"));
		}
		
		if(request.getParameter("password")!=null){
			u.setPassword(request.getParameter("password"));
		}
		
		//System.out.println(u.getEmail()+u.getPassword());
		User x=m.Login(u.getEmail(),u.getPassword());
		
		if(x!=null){
			x.setCoverphoto("cover/defaultcover.jpg");	
			if(m.insertcover(x.getCoverphoto(), x.getUserid())){
			x.setCoverphoto(m.getCover(x.getUserid()));
			}
			request.getSession().setAttribute("ka_user",x);
			
			}
		else{
			
			
		}
		forward.setPath("index.act");
		forward.setRedirect(true);
			return forward;
	}

}
