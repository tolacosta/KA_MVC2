package Controller.Forum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ForumCommentDAO;
import model.dto.ForumComment;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionComment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ForumComment dto=new ForumComment();
		ForumCommentDAO dao=new ForumCommentDAO();
		
		User users = (User)request.getSession().getAttribute("ka_user");
		
		dto.setPostdate(new java.sql.Date(new java.util.Date().getTime()));
		dto.setTitle(request.getParameter("txttitle"));
		dto.setDetail(request.getParameter("txtdetail"));
		dto.setTag(request.getParameter("txttag"));
		dto.setParentid(1);
		dto.setCategoryid(Integer.parseInt(request.getParameter("txtcategoryid")));
		dto.setUserid(users.getUserid());
		dto.setSelected(true);
		
		if(dao.insert(dto)){
			System.out.println("Inserted successfully.");
		}else{
			System.out.println("Fail Inserting!!!");
		}

		ActionForward forward = new ActionForward();
		forward.setPath("index.act");
		forward.setRedirect(true);
		return forward;
	}
}
