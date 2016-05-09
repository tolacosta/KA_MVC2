package Controller.VideoAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.HistoryDAO;
import model.dao.LogDAO;
import model.dao.PlaylistDAO;
import model.dao.VideoDAO;
import model.dao.VoteDAO;
import model.dto.Comment;
import model.dto.History;
import model.dto.Log;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionPlayVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int videoid=0; int playlistid=0; boolean userviewable=false;
		if(request.getParameter("v")!=null)
			{videoid=Integer.parseInt(request.getParameter("v").toString());
			
			}
		
		//PLAYLIST
		if(request.getParameter("p")!=null){
			playlistid=Integer.parseInt(request.getParameter("p").toString());
			ResultSet prs = new PlaylistDAO().listVideo(playlistid);
			ArrayList<Video> parr = new ArrayList<Video>();
			while(prs.next()){
				Video vdto = new Video(prs.getInt("videoid"), prs.getString("videoname"), prs.getString("description"), prs.getString("youtubeurl"), prs.getString("fileurl"), prs.getBoolean("publicview"), prs.getDate("postdate"), prs.getInt("userid"), prs.getInt("viewcount"));
				vdto.setCategorynames(prs.getString("categorynames"));
				vdto.setCountcomments(prs.getInt("countcomments"));
				vdto.setCountvoteminus(prs.getInt("countvoteminus"));
				vdto.setCountvoteplus(prs.getInt("countvoteplus"));
				vdto.setUsername(prs.getString("username"));
				
				parr.add(vdto);
			}
			
			
			
			HashMap<String, Object> p = new HashMap<String, Object>();
			p.put("p", request.getParameter("p"));
			p.put("playlist", parr);
			String playlist_json = new Gson().toJson(p);
			System.out.println(playlist_json);
			request.setAttribute("playlist_json", playlist_json);
			
			
			String pname = new PlaylistDAO().getPlaylistName(playlistid);
			
			request.setAttribute("pname", pname);
		}
		
		Video dto = new VideoDAO().get(videoid, true);
		User u = (User) request.getSession().getAttribute("ka_user");
//						u = new User();u.setViewable(true);u.setUserid(2);
		
		int uservote = 0;
		if(u!=null){
		//VOTE
			new VoteDAO().checkuservote(u.getUserid(), videoid);
			userviewable = u.isViewable();
		
		//HISTORY
			History hdto = new History();
			hdto.setUserid(u.getUserid());
			hdto.setVideoid(videoid);
			new HistoryDAO().insert(hdto);
		}
		
		
		
		//COMMENT
		ResultSet rs = new VideoDAO().listcomments(videoid);
		ArrayList<Comment> arr = new ArrayList<Comment>();
		while(rs.next()){
			Comment c =  new Comment(rs.getInt("commentid"), rs.getDate("commentdate"), rs.getString("commenttext"), rs.getInt("videoid"), rs.getInt("userid"));
			c.setUsername(rs.getString("username"));
			c.setRecommid(rs.getInt("replycomid"));
			c.setUserimageurl(rs.getString("userimageurl"));
			arr.add(c);
		}
		
		String comment_json = new Gson().toJson(arr);
		
//		String videoId = new Gson().toJson(videoid);
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(videoId);
//		System.out.println(videoId);
		System.err.println(userviewable);
		if(dto.isPublicview() || userviewable){
			Log l = new Log();
			l.setUserid(u.getUserid());
			l.setVideoid(videoid);
			request.setAttribute("logid", new LogDAO().insert(l));
			request.setAttribute("video", dto);
		}
		
		//Related Videos
		//Related Videos
		if(dto.getCategorynames()!=null)
			{
				 String categoryname= dto.getCategorynames();
				ResultSet relaters = new VideoDAO().getRelateVideos(categoryname);
				ArrayList<Video> p1 = new ArrayList<Video>();
				while(relaters.next()){
					Video vdto = new Video();
					vdto.setVideoid(relaters.getInt("videoid"));	
					vdto.setVideoname(relaters.getString("videoname"));
					vdto.setYoutubeurl(relaters.getString("youtubeurl"));
					vdto.setPublicview(relaters.getBoolean("publicview"));
					vdto.setPostdate(relaters.getDate("postdate"));
					vdto.setUserid(relaters.getInt("userid"));
					vdto.setUsername(relaters.getString("username"));
					vdto.setViewcounts(relaters.getInt("viewcount"));
					
					p1.add(vdto);
				}			
				request.setAttribute("relate",p1);
			}		
		
		request.setAttribute("uservote", uservote);
		request.setAttribute("comment_json", comment_json);
		
		forward.setPath("new_single_post.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
