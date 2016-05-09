package Controller.Forum;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ForumCommentDAO;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionGetFooter implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		//Total Question
		int countQuestion = new ForumCommentDAO().countQuestion();
		
		//Total Reply/Comment
		int countComment = new ForumCommentDAO().countComment();
		
		//Tags
		String tags[] = new ForumCommentDAO().getAllTags();
		
		HashMap<String, Object> hash = new HashMap<String,Object>();		
		hash.put("countQuestion", countQuestion);
		hash.put("countComment", countComment);
		hash.put("tag", tags);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String playlist_json = new Gson().toJson(hash);
		System.out.println(playlist_json);
		response.getWriter().write(playlist_json);
		
		return null;
	}
}
