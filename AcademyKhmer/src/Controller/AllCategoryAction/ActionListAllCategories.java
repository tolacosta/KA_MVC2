package Controller.AllCategoryAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.CategoryDAO;
import model.dao.MaincategoryDAO;
import model.dao.PlaylistDAO;
import model.dto.Category;
import model.dto.Maincategory;
import model.dto.Playlist;
import Controller.Action;
import Controller.ActionForward;

public class ActionListAllCategories implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		MaincategoryDAO dao = new MaincategoryDAO();
		ResultSet li = dao.list();
		ArrayList<Maincategory> maincategories = new ArrayList<Maincategory>();
		while(li.next()){
			Maincategory dto = new Maincategory();
			dto.setMaincategoryname(li.getString("Maincategoryname"));
			dto.setMaincategorylogourl(li.getString("Maincategorylogourl"));
			dto.setMaincategoryorder(li.getInt("Maincategoryorder"));
			dto.setMaincategoryid(li.getInt("maincategoryid"));
			maincategories.add(dto);
		}
		li.close();
		CategoryDAO catdao = new CategoryDAO();
		ResultSet rs = catdao.list();
		ArrayList<Category> subcategories = new ArrayList<Category>();
		while(rs.next()){
			Category catdto = new Category();
			catdto.setCategoryid(rs.getInt("categoryid"));
			catdto.setCategoryname(rs.getString("categoryname"));
			catdto.setCategorylogourl(rs.getString("categorylogourl"));
			catdto.setMaincategoryid(rs.getInt("maincategoryid"));
			subcategories.add(catdto);
		}
		rs.close();
		
		ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		PlaylistDAO playlistdao = new PlaylistDAO();
		Playlist playlistdto = new Playlist();
//		ResultSet playlistrs = playlistdao.listplaylistbyPublicView(true);
		ResultSet playlistrs = playlistdao.listplaylistbyAdmin(true);
		while(playlistrs.next()){
			playlistdto = new Playlist();
			playlistdto.setPlaylistid(playlistrs.getInt("playlistid"));
			playlistdto.setPlaylistname(playlistrs.getString("playlistname"));
			playlists.add(playlistdto);
		}
		playlistrs.close();
		
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("maincategories", maincategories);
		hash.put("subcategories", subcategories);
		hash.put("playlist", playlists);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String news_json = new Gson().toJson(hash);
		response.getWriter().write(news_json);
		System.out.println(news_json);
		return null;
	}

}
