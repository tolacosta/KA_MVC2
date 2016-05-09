package Controller.Tutorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TutorialDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteTutorial implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TutorialDAO dao=new TutorialDAO();
		
		User users = (User)request.getSession().getAttribute("ka_user");
		
		if(request.getParameter("tutorialid")!=null && users.isDeleteable()){
			if(dao.delete(Integer.parseInt(request.getParameter("tutorialid").toString()))){
				System.out.println("Inserted successfully.");
			}else{
				System.out.println("Fail Inserting!!!");
			}
		}
		
		
		return null;
	}
}
