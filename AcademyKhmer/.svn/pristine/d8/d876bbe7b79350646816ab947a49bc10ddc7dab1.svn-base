package Controller.ForumCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.dao.ForumCategoryDAO;
import model.dto.Category;
import model.dto.ForumCategory;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetForumCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int categoryid=Integer.parseInt(request.getParameter("categoryid"));
		ForumCategory dto=new ForumCategory();
		ForumCategoryDAO dao=new ForumCategoryDAO();
		dto=dao.get(categoryid);
		request.setAttribute("categorydto", dto);
		System.out.println(dto.getCategory_id());
		System.out.println(dto.getCategory_name());
		
		ActionForward forward=new ActionForward();
		forward.setPath("addforumcategory.act");
		forward.setRedirect(false);
		return forward;
	}
}
