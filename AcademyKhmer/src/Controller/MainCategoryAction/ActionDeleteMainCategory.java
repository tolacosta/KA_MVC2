package Controller.MainCategoryAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MaincategoryDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteMainCategory implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int maincategoryid = Integer.parseInt(request.getParameter("txtmaincategoryid"));
		
		MaincategoryDAO maincategoryDAO = new MaincategoryDAO();
		maincategoryDAO.delete(maincategoryid);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("listmaincategorypro.act");
		return forward;
	}

}
