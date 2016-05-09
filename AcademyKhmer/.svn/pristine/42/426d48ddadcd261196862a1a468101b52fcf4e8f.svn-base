package Controller.PlaylistAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import ultilities.ClsPagination;
import model.dao.PlaylistDAO;
import model.dto.Playlist;
import model.dto.User;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoLogger;

import Controller.Action;
import Controller.ActionForward;

public class ActionListPlaylist implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ArrayList<Playlist> a = new ArrayList<Playlist>();
		PlaylistDAO dao = new PlaylistDAO();
		Playlist dto = new Playlist();
		
		ClsPagination pagin = new ClsPagination();
		
		if(request.getParameter("page")!=null)
			pagin.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		if(request.getParameter("setNumrow")!=null){
			pagin.setPerPage(Integer.parseInt(request.getParameter("setNumrow").toString()));
		}
		else{
			pagin.setPerPage(8);
		}
		if(request.getParameter("tfsearch")!=null)
			dto.setPlaylistname(request.getParameter("tfsearch"));
		else
			dto.setPlaylistname("");
		
		
		dto.setUserid(((User) request.getSession().getAttribute("ka_user")).getUserid());
		ResultSet rs = dao.list(pagin , dto);
		pagin.setTotalCount(dao.countUserPlaylist(dto.getPlaylistname(),((User) request.getSession().getAttribute("ka_user")).getUserid()));
			
		while (rs.next()) {
			dto = new Playlist();
			dto.setPlaylistid(rs.getInt("playlistid"));
			dto.setPlaylistname(rs.getString("playlistname"));
			dto.setDescription(rs.getString("description"));
			dto.setThumbnailurl(rs.getString("thumbnailurl"));
			dto.setPublicview(rs.getBoolean("publicview"));
			dto.setUsername(rs.getString("username" ));
			dto.setUserid(rs.getInt("userid"));
			dto.setCountvideos(rs.getInt("countvideos"));
			a.add(dto);
		}
		rs.close();
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("playlist", a);
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
		return null;
	}

}
