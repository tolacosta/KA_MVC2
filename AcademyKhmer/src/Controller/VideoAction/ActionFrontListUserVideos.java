package Controller.VideoAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;
import model.dto.PlaylistDetail;
import model.dto.User;
import model.dto.Video;
import ultilities.ClsPagination;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionFrontListUserVideos implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<Video> a = new ArrayList<Video>();
		VideoDAO dao = new VideoDAO();
		Video dto = new Video();
		
		ClsPagination pagin = new ClsPagination();
		
		if(request.getParameter("page")!=null)
			pagin.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		if(request.getParameter("setNumrow")!=null)
			pagin.setPerPage(Integer.parseInt(request.getParameter("setNumrow").toString()));
		if(request.getParameter("tfsearch")!=null)
			dto.setVideoname(request.getParameter("tfsearch"));
		else
			dto.setVideoname("");
		
		pagin.setPerPage(5);
		
		ResultSet rs = dao.listUserVideos( ((User) request.getSession().getAttribute("ka_user")).getUserid() , dto.getVideoname() , pagin );
		pagin.setTotalCount(dao.countUserVideos( ((User) request.getSession().getAttribute("ka_user")).getUserid() , dto.getVideoname() ));
		while (rs.next()) {
			dto =  new Video(rs.getInt("videoid"), rs.getString("videoname"), "", rs.getString("youtubeurl"), rs.getString("fileurl"), rs.getBoolean("publicview"), rs.getDate("postdate"), rs.getInt("userid"), rs.getInt("viewcount"));
			String description = (rs.getString("description").length()>50 ? rs.getString("description").substring(0, 49)+"..." : rs.getString("description"));
			dto.setDescription(description);
			dto.setCategorynames(rs.getString("categorynames"));
			dto.setCountcomments(rs.getInt("countcomments"));
			dto.setCountvoteminus(rs.getInt("countvoteminus"));
			dto.setCountvoteplus(rs.getInt("countvoteplus"));
			dto.setUsername(rs.getString("username"));
			a.add(dto);
		}
		
		
		int playlistid = 0;
		if(request.getParameter("playlistid")!=null)
			playlistid = Integer.parseInt(request.getParameter("playlistid"));
		
		ArrayList<PlaylistDetail> ldetail = new ArrayList<PlaylistDetail>();
		PlaylistDAO dao1 = new PlaylistDAO();
		PlaylistDetail dtodetail = new PlaylistDetail();
		ResultSet rs1 = dao1.listplaylistdetail(((User) request.getSession().getAttribute("ka_user")).getUserid() , playlistid);
		while (rs1.next()) {
			dtodetail = new PlaylistDetail();
			dtodetail.setPlaylistid(rs1.getInt("playlistid"));
			dtodetail.setVideoid(rs1.getInt("videoid"));
			ldetail.add(dtodetail);
		}
		rs.close();
		rs1.close();
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("listuservideos", a);
		hash.put("paginationvideos",pagin);
		hash.put("totalpages",pagin.totalPages());
		hash.put("currentpage",pagin.getCurrentPage());
		hash.put("hasNext", pagin.hasNextPage());
		hash.put("hasPrevious", pagin.hasPreviousPage());
		hash.put("nextpage", pagin.nextPage());
		hash.put("previouspage", pagin.previousPage());
		hash.put("PlaylistDetail", ldetail);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String listuservideos_json = new Gson().toJson(hash);
		System.out.println("listuservideos_json "+ listuservideos_json);
		response.getWriter().write(listuservideos_json);
		
		return null;
	}

}
