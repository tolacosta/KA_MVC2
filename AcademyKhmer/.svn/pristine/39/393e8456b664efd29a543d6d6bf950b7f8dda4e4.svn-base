package Controller.PlaylistAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import model.dto.Playlist;
import model.dto.PlaylistDetail;
import model.dto.User;
import ultilities.ClsPagination;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionListPlaylistName  implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		String  search = "";
		
		if(request.getParameter("tfsearch")!=null)
			search =request.getParameter("tfsearch");
		int userid=0;
		if(request.getSession().getAttribute("ka_user") != null )
			userid = ((User) request.getSession().getAttribute("ka_user")).getUserid();
		
		ArrayList<Playlist> a = new ArrayList<Playlist>();
		PlaylistDAO dao = new PlaylistDAO();
		Playlist dto = new Playlist();
		dto.setPlaylistname(search);
		if(request.getSession().getAttribute("ka_user") != null )
			dto.setUserid(userid);
		ResultSet rs = dao.listplaylistname(dto);
		while (rs.next()) {
			dto = new Playlist();
			dto.setPlaylistid(rs.getInt("playlistid"));
			dto.setPlaylistname(rs.getString("playlistname"));
			dto.setPublicview(rs.getBoolean("publicview"));
			a.add(dto);
		}
		
		
		ArrayList<PlaylistDetail> ldetail = new ArrayList<PlaylistDetail>();
		PlaylistDetail dtodetail = new PlaylistDetail();
		 rs = dao.listplaylistdetail(userid);
		while (rs.next()) {
			dtodetail = new PlaylistDetail();
			dtodetail.setPlaylistid(rs.getInt("playlistid"));
			dtodetail.setVideoid(rs.getInt("videoid"));
			ldetail.add(dtodetail);
		}
		rs.close();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("playlistname", a);
		hash.put("listdetail",ldetail);
		
		String playlistname_json = new Gson().toJson(hash);
		System.out.println(playlistname_json);
		response.getWriter().write(playlistname_json);
		return null;
	}

}
