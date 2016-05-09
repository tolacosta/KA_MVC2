package Controller.MainCategoryAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MaincategoryDAO;
import model.dto.Maincategory;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetMainCategory implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int maincategoryid = Integer.parseInt(request.getParameter("txtmaincategoryid"));
		MaincategoryDAO maincategoryDAO = new MaincategoryDAO();
		Maincategory maincategory = maincategoryDAO.get(maincategoryid);
		
		
		request.setAttribute("maincategory", maincategory);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("addmaincategory.act");
		return forward;
	}

}
