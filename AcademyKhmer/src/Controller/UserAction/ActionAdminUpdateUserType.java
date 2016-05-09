package Controller.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import model.dao.UserDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionAdminUpdateUserType implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			
			ActionForward forward = new ActionForward();
			UserDAO dao = new UserDAO();
			
			int userid = 0, page = 1 , typeid = 0;;
			if(request.getParameter("userid") != null){
				userid = Integer.parseInt(request.getParameter("userid").toString());
			}
			if(request.getParameter("typeid") != null){
				typeid = Integer.parseInt(request.getParameter("typeid").toString());
			}
			if(request.getParameter("page") != null){
				page = Integer.parseInt(request.getParameter("page").toString());
			}
			
			User dto = new User();
			dto.setUserid(userid);
			dto.setUsertypeid(typeid);
			
			if(dao.updateUserType(dto)){
				System.out.println("Success");
			}else{
				System.out.println("UNSuccess");
			}
			
			forward.setRedirect(false);
			forward.setPath("playlist.act?page="+page);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
