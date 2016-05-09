package Controller.Forum;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.ForumVoteDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionVote implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User u = (User) request.getSession().getAttribute("ka_user");
//		u = new User();u.setViewable(true);u.setUserid(2);

		int commentid = Integer.parseInt(request.getParameter("c"));
		int type = Integer.parseInt(request.getParameter("type"));
		int vote=0;
		if(type>0){
			type=1;
			vote = new ForumVoteDAO().voteplus(u.getUserid(), commentid);
			System.out.println("Voteplus success");
		}else if(type<0){
			type=-1;
			vote = new ForumVoteDAO().voteminus(u.getUserid(), commentid);
		}else{
			type=0;
			vote = new ForumVoteDAO().unvote(u.getUserid(), commentid);
		}
		
		
		/*HashMap<String, String> v = new HashMap<String, String>();
		v.put("count", vote+"");
		v.put("type", type+"");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String json = new Gson().toJson(v);
		response.getWriter().write(json);*/
		
		response.getWriter().write("Success");
		return null;
	}
}
