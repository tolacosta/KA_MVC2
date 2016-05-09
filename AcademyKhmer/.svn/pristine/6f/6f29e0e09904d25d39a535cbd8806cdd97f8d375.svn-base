package Controller.Tutorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TutorialDAO;
import model.dto.Tutorial;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionTutorialDetailFirst implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		int cid=  Integer.parseInt(request.getParameter("categoryid"));
		Tutorial dto= new Tutorial();
		TutorialDAO dao= new TutorialDAO();

		dto = dao.getFirstDetail(cid);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String tutorial= new Gson().toJson(dto);
		
		response.getWriter().write(tutorial);
	
		System.out.println(tutorial);
	
	
		return null;
	}

}
