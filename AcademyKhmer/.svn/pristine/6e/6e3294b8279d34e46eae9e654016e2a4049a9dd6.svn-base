package Controller.Forum;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.ClsPagination;

import com.google.gson.Gson;

import model.dao.ForumCommentDAO;
import model.dto.ForumComment;
import Controller.Action;
import Controller.ActionForward;

public class ActionListQuestion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ResultSet rs= null;
		
		ArrayList<ForumComment> a = new ArrayList<ForumComment>();
		ForumComment dto = new ForumComment();
		
		ClsPagination pagin = new ClsPagination();
		
		pagin.setPerPage(1);
		
		
		int page = 0;
		String userid = "";
		String cateid = "";
		String keyword = "";
		String tag = "";
		
		
		if(request.getParameter("page")!=null){
			pagin.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		}
		if(request.getParameter("userid") != null){
			userid = request.getParameter("userid");
		}
		if(request.getParameter("cateid") != null){
			cateid = request.getParameter("cateid");
		}
		if(request.getParameter("keyword") != null){
			keyword = request.getParameter("keyword");
		} 
		if(request.getParameter("tag") != null){
			tag = request.getParameter("tag").trim();
		} 
		
		pagin.setPerPage(5);
		rs = new ForumCommentDAO().listAllQuestion(keyword, userid, cateid , pagin, tag);
		pagin.setTotalCount(new ForumCommentDAO().countQuestion(keyword, userid, cateid));
		
		
		while (rs.next()) {
			dto = new ForumComment();
			dto.setCommentid(rs.getInt("commentid"));
			dto.setPostdate(rs.getDate("postdate"));
			dto.setTitle(rs.getString("title"));
			dto.setDetail(rs.getString("detail"));
			dto.setTag(rs.getString("tag"));
			dto.setParentid(rs.getInt("parentid"));
			dto.setCategoryid(rs.getInt("categoryid"));
			dto.setUserid(rs.getInt("userid"));
			dto.setUsername(rs.getString("username"));
			dto.setSelected(rs.getBoolean("selected"));
			dto.setCommentcount(rs.getInt("commentcount"));
			dto.setVote(rs.getInt("votecount"));
			a.add(dto);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("q", a);
		hash.put("p",pagin);
		hash.put("c",pagin.getCurrentPage());
		hash.put("h", pagin.hasNextPage());
		hash.put("n", pagin.nextPage());
		
		response.getWriter().write(new Gson().toJson(hash));
		System.out.println(new Gson().toJson(hash));
		
		return null;
	}
	

}
