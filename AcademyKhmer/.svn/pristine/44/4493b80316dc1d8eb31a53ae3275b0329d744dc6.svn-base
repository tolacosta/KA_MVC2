package Controller.Forum;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ForumCategoryDAO;
import model.dao.ForumCommentDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddQuestionPage implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ResultSet crs = new ForumCategoryDAO().list();
		ArrayList<String[]> categories = new ArrayList<String[]>();
		String category[];
		while(crs.next()){
			category=new String[2];
			category[0]=crs.getString("categoryid");
			category[1]=crs.getString("categoryname");
			categories.add(category);
		}
		String tags[]=new ForumCommentDAO().getAllTags();
		
		request.setAttribute("categories", categories);
		System.out.println(categories);
		request.setAttribute("tags", tags);
		System.err.println(tags);
		
		
		forward.setPath("addQuestion.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
