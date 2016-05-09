package Controller.PlaylistAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.ClsPagination;
import model.dao.PlaylistDAO;
import model.dto.Playlist;
import model.dto.Video;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionGetPlaylist implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int page=1;
		int playlistid=0;
		
		ClsPagination pagin = new ClsPagination();
		
		if(request.getParameter("page")!=null)
			pagin.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		if(request.getParameter("setNumrow")!=null)
			pagin.setPerPage(Integer.parseInt(request.getParameter("setNumrow").toString()));
		if(request.getParameter("playlistid")!=null)
			playlistid =Integer.parseInt(request.getParameter("playlistid"));

		pagin.setPerPage(5);
		
		System.out.println(playlistid);
		ArrayList<Video> a = new ArrayList<Video>();
		PlaylistDAO dao = new PlaylistDAO();
		Video dto = new Video();
		Playlist pldto = new Playlist();
		
		
		ResultSet rs = dao.listVideoInPlaylist(playlistid,pagin );
		pagin.setTotalCount(dao.countvideos(playlistid));
		System.out.println(pagin.offset());
		
		while (rs.next()) {
//			pldto = new Playlist();
//			pldto.setPlaylistid(rs.getInt("playlistid"));
//			pldto.setPlaylistname(rs.getString("playlistname"));
//			pldto.setDescription(rs.getString("description"));
//			pldto.setThumbnailurl(rs.getString("thumbnailurl"));
//			pldto.setPublicview(rs.getBoolean("publicview"));
			
			dto = new Video();
			dto.setVideoid(rs.getInt("videoid"));
			dto.setVideoname(rs.getString("videoname"));
			dto.setDescription(rs.getString("description"));
			dto.setYoutubeurl(rs.getString("youtubeurl"));
			dto.setFileurl(rs.getString("fileurl"));
			dto.setPublicview(rs.getBoolean("ispublic"));
			dto.setPostdate(rs.getDate("postdate"));
			dto.setUserid(rs.getInt("userid"));
			dto.setViewcounts(rs.getInt("viewcount"));
			dto.setUsername(rs.getString("username"));
			dto.setCountvoteplus(rs.getInt("countvoteplus"));
			dto.setCountvoteminus(rs.getInt("countvoteminus"));
			
			System.out.println(":::: "+ rs.getBoolean("ispublic"));
			
			a.add(dto);
		}
		rs.close();
		
		
		pldto = dao.get(playlistid );
		
		
	
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("getplaylist", a);
		hash.put("playlistdto", pldto);
		hash.put("pagination",pagin);
		hash.put("totalpages",pagin.totalPages());
		hash.put("currentpage",pagin.getCurrentPage());
		hash.put("hasNext", pagin.hasNextPage());
		hash.put("hasPrevious", pagin.hasPreviousPage());
		hash.put("nextpage",   pagin.nextPage());
		hash.put("previouspage", pagin.previousPage());
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String playlist_json = new Gson().toJson(hash);
		System.out.println("Video in playlist: "+playlist_json);
		response.getWriter().write(playlist_json);
		return null;
		
		
	}

}
