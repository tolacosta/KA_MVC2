package Controller.Tutorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.TutorialDAO;
import model.dto.Tutorial;
import Controller.Action;
import Controller.ActionForward;

public class ActionTutorialDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	
			int tutorialid=  Integer.parseInt(request.getParameter("tutorialid"));
			Tutorial dto= new Tutorial();
			TutorialDAO dao= new TutorialDAO();

			dto = dao.get(tutorialid);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String tutorial= new Gson().toJson(dto);
			
			response.getWriter().write(tutorial);
		
			System.out.println(tutorial);
		
		
	
		return null;
	}

}
