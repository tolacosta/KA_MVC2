package Controller.Forum;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ForumCategoryDAO;
import model.dao.ForumCommentDAO;
import model.dto.ForumCategory;
import model.dto.ForumComment;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionListPost implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ForumComment q; ForumCategory c; int i=0;
		
		//Question
		ArrayList<ForumComment> a = new ArrayList<ForumComment>();
		ResultSet rs = new ForumCommentDAO().listQuestion();
		while(rs.next()){
			q = new ForumComment(rs.getInt("commentid"), rs.getDate("postdate"), rs.getString("title"), rs.getString("detail"), rs.getString("tag"), 0, rs.getInt("categoryid"), rs.getInt("userid"), false);
			q.setUsername(rs.getString("username"));
			q.setCommentcount(rs.getInt("commentcount"));
			q.setVote(rs.getInt("votecount"));
			a.add(q);
			i++;
			if(i>=20) break;
		}
		rs.close();
		
		//Category
		ArrayList<ForumCategory> ca = new ArrayList<ForumCategory>();
		ResultSet crs = new ForumCategoryDAO().list();
		while(crs.next()){
			c = new ForumCategory(crs.getInt("categoryid"), crs.getString("categoryname"));
			c.setComment_count(crs.getInt("commentcount"));
			ca.add(c);
		}
		crs.close();
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("question", a);
		hash.put("category", ca);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String playlist_json = new Gson().toJson(hash);
		System.out.println(playlist_json);
		response.getWriter().write(playlist_json);
		
		return null;
	}
}
