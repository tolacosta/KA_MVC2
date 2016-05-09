package Controller.Forum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ForumCommentDAO;
import model.dto.ForumComment;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddAnswerQuestion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ForumComment dto=new ForumComment();
		ForumCommentDAO dao=new ForumCommentDAO();
		
		dto.setPostdate(new java.sql.Date(new java.util.Date().getTime()));
		dto.setTitle(request.getParameter("txttitle"));
		dto.setDetail(request.getParameter("answerdetail"));
		
		dto.setParentid(Integer.parseInt(request.getParameter("parentid")));
//		dto.setCategoryid(Integer.parseInt(request.getParameter("categoryid")));
		dto.setCategoryid(null);
		dto.setTag(request.getParameter("taganswer"));
		dto.setUserid(Integer.parseInt(request.getParameter("userid")));
		dto.setSelected(true);
		
		if(dao.insertAnswer(dto)){
			System.out.println("Inserted successfully.");
		}else{
			System.out.println("Fail Inserting!!!");
		}

		
		
		return null;
	}

}
