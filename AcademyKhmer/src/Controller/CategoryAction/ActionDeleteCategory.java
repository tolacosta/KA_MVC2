package Controller.CategoryAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteCategory implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int categoryid=Integer.parseInt(request.getParameter("categoryid"));
		CategoryDAO dao=new CategoryDAO();
		if(dao.delete(categoryid)){
			System.out.println("Delete successfully.");
		}else{
			System.out.println("Fail deleting!!!");
		}
		ActionForward forward=new ActionForward();
		forward.setPath("listcategorypro.act");
		forward.setRedirect(true);
		return forward;
	}

}
