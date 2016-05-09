package Controller.Comment;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDAO;
import model.dao.VideoDAO;
import model.dto.Comment;
import model.dto.User;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionAddReplyComment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int videoid=0;
		//int recomid=0;
		String commenttext = null;
		int comid=0;
		int userid;
		
		if(request.getParameter("v")!=null)
			videoid=Integer.parseInt(request.getParameter("v").toString());
		System.out.println(videoid + " - " + request.getParameter("commenttext"));
		if(request.getParameter("commenttext")!=null)
			commenttext=request.getParameter("commenttext").toString();
		System.out.println(request.getParameter("comid").toString());
		if(request.getParameter("comid")!=null)
			comid= Integer.parseInt(request.getParameter("comid").toString());
			
		userid=((User) request.getSession().getAttribute("ka_user")).getUserid();
//						userid=1;
		
		Comment dto = new Comment();
		dto.setUserid(userid);
		dto.setVideoid(videoid);
		dto.setRecommid(comid);
		dto.setCommenttext(commenttext);
		new CommentDAO().reply(dto);
		
		ResultSet rs = new VideoDAO().listcomments(videoid);
		ArrayList<Comment> arr = new ArrayList<Comment>();
		while(rs.next()){
			Comment c =  new Comment(rs.getInt("commentid"), rs.getDate("commentdate"), rs.getString("commenttext"), rs.getInt("videoid"), rs.getInt("userid"));
			c.setUsername(rs.getString("username"));
			c.setRecommid(rs.getInt("replycomid"));
			System.out.println(c.getRecommid());
			c.setUserimageurl(rs.getString("userimageurl"));
			arr.add(c);
		}
		
		
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String comment_json = new Gson().toJson(arr);
		System.out.println(comment_json);
		response.getWriter().write(comment_json);
		return null;
		
	}

}
