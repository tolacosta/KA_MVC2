package Controller.ForumCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.dao.ForumCategoryDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteForumCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int categoryid=Integer.parseInt(request.getParameter("categoryid"));
		ForumCategoryDAO dao=new ForumCategoryDAO();
		if(dao.delete(categoryid)){
			System.out.println("Delete successfully.");
		}else{
			System.out.println("Fail deleting!!!");
		}
		ActionForward forward=new ActionForward();
		forward.setPath("forumcategorypro.act");
		forward.setRedirect(true);
		return forward;
	}
}
