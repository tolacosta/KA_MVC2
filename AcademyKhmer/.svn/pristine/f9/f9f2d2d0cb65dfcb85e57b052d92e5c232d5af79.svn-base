package Controller.Tutorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TutorialDAO;
import model.dto.Tutorial;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetTutorial implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int tutorialid=  Integer.parseInt(request.getParameter("tutorialid"));
		Tutorial dto= new Tutorial();
		TutorialDAO dao= new TutorialDAO();
		
		dto = dao.get(tutorialid);
		request.setAttribute("tutorial", dto);
		
		
		ActionForward forward=new ActionForward();
		forward.setPath("addtutorial.act");
		forward.setRedirect(false);
		return forward;
	}

}
