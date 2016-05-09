package Controller.Forum;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.ClsPagination;

import com.google.gson.Gson;

import model.dao.ForumCommentDAO;
import model.dto.ForumComment;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetPost implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ForumComment q, c;
		ArrayList<ForumComment> a = new ArrayList<ForumComment>();
		ClsPagination pagin = new ClsPagination();
		
		if(request.getParameter("page")!=null)
			pagin.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		
		if(request.getParameter("q")!=null){
			q = new ForumCommentDAO().get(Integer.parseInt(request.getParameter("q")));
//			pagin.setPerPage(5);
//			System.err.println("C " + q.getCommentcount());
			pagin.setTotalCount(new ForumCommentDAO().countReplyCmtestion(Integer.parseInt(request.getParameter("q"))));
			ResultSet rs = new ForumCommentDAO().listComment(Integer.parseInt(request.getParameter("q")) , pagin);
			while(rs.next()){
				c = new ForumComment(rs.getInt("commentid"), rs.getDate("postdate"), rs.getString("title"), rs.getString("detail"), rs.getString("tag"), 0, rs.getInt("categoryid"), rs.getInt("userid"), false);
				c.setUsername(rs.getString("username"));
				c.setUserimageurl(rs.getString("userimageurl"));
				c.setSelected(rs.getBoolean("selected"));
				c.setCommentcount(rs.getInt("commentcount"));
				c.setVote(rs.getInt("votecount"));
				c.setParentid(rs.getInt("parentid"));
				System.err.println(c.getUserimageurl());
				a.add(c);
			}
			rs.close();
			HashMap<String, Object> hash = new HashMap<String,Object>();
			hash.put("question", q);
			hash.put("comment", a);
			hash.put("pagination",pagin);
			hash.put("totalpages",pagin.totalPages());
			hash.put("currentpage",pagin.getCurrentPage());
			hash.put("hasNext", pagin.hasNextPage());
			hash.put("hasPrevious", pagin.hasPreviousPage());
			hash.put("nextpage", pagin.nextPage());
			hash.put("previouspage", pagin.previousPage());
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String playlist_json = new Gson().toJson(hash);
			System.out.println(playlist_json);
			response.getWriter().write(playlist_json);
		}
		
		return null;
	}
}
