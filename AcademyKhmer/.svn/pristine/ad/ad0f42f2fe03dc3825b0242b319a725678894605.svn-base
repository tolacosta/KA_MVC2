package Controller.ForumCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ForumCategoryDAO;
import model.dto.ForumCategory;
import Controller.Action;
import Controller.ActionForward;

public class ActionUpdateForumCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ForumCategory dto=new ForumCategory();
		ForumCategoryDAO dao=new ForumCategoryDAO();
		dto.setCategory_id(Integer.parseInt(request.getParameter("categoryid")));
		dto.setCategory_name(request.getParameter("categoryname"));
		if(dao.update(dto)){
			System.out.println("Updated successfully.");
		}else{
			System.out.println("Fail Updating!!!");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("forumcategory.act");
		forward.setRedirect(true);
		return forward;
	}
}
