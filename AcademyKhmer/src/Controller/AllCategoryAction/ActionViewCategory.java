package Controller.AllCategoryAction;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Action;
import Controller.ActionForward;

public class ActionViewCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		

		int category = Integer.parseInt(request.getParameter("category"));
		request.setAttribute("category", category);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view_category.jsp");
		return forward;
	}

}
