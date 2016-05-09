package Controller.CategoryAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.dto.Category;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetCategory implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int categoryid=Integer.parseInt(request.getParameter("categoryid"));
		CategoryDAO dao=new CategoryDAO();
		Category dto=new Category();
		dto=dao.get(categoryid);
		request.setAttribute("categorydto", dto);
		System.out.println(dto.getCategoryid());
		System.out.println(dto.getCategoryname());
		
		ActionForward forward=new ActionForward();
		forward.setPath("toaddcategory.act");
		forward.setRedirect(false);
		return forward;
	}

}
