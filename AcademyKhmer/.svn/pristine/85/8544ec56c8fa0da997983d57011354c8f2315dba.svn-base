package Controller.Comment;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDAO;
import model.dto.Comment;
import Controller.Action;
import Controller.ActionForward;

public class ActionListComments implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		CommentDAO dao= new CommentDAO();
		ActionForward forward= new ActionForward();
		ArrayList<Comment> list= new ArrayList<Comment>();
		ResultSet rs = dao.listComment();
		while(rs.next()){
			Comment dto= new Comment();
			dto.setCommentid(rs.getInt("commentid"));
			dto.setCommentdate(rs.getDate("commentdate"));
			dto.setCommenttext(rs.getString("commenttext"));
			dto.setVideoid(rs.getInt("videoid"));
			dto.setUserid(rs.getInt("userid"));
			list.add(dto);
		}
		
		request.setAttribute("listcomment", list);
		forward.setPath("listcomments.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
