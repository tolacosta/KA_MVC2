package Controller.Forum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.ForumVoteDAO;
import model.dto.ForumVote;
import Controller.Action;
import Controller.ActionForward;

public class ActionSelectAnswer implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ForumVoteDAO dao = new ForumVoteDAO();
		ForumVote dto = new ForumVote();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int commentid = Integer.parseInt(request.getParameter("cid"));
		int parentid = Integer.parseInt(request.getParameter("pid"));
		
		if(dao.selectanswer(commentid ,parentid )){
			response.getWriter().write(new Gson().toJson("Success"));
		}else{
			response.getWriter().write(new Gson().toJson("Fail"));
		}
		
		return null;
	}
}
