package Controller.AllCategoryAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.CategoryDAO;
import model.dao.MaincategoryDAO;
import model.dao.VideoDAO;
import model.dto.Category;
import model.dto.Maincategory;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionListVideosInCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		

		int category = Integer.parseInt(request.getParameter("category"));
		int	page = Integer.parseInt(request.getParameter("page"));
		int maxview = 10;
		ResultSet rs = new CategoryDAO().listVideosInCategory(category,page,maxview);
		ArrayList<Video> listVideosInCategory = new ArrayList<Video>();
		while(rs.next()){
			Video dto = new Video();
			dto.setVideoid(rs.getInt("videoid"));
			dto.setVideoname(rs.getString("videoname"));
			dto.setDescription(rs.getString("description"));
			dto.setYoutubeurl(rs.getString("youtubeurl"));
			dto.setUserid(rs.getInt("userid"));
			dto.setUsername(rs.getString("username"));
			dto.setViewcounts(rs.getInt("viewcount"));
			dto.setPostdate(rs.getDate("postdate"));
			dto.setCategorynames(rs.getString("categoryname"));
			dto.setPublicview(rs.getBoolean("publicview"));
			listVideosInCategory.add(dto);
		}
		

		int total_videos = new CategoryDAO().countvideos(category);
		
		rs.close();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("listVideosInCategory", listVideosInCategory);
		hash.put("total_videos", total_videos);
		hash.put("page", page);
		hash.put("maxview", maxview);
		hash.put("category", category);
		
		if( request.getSession().getAttribute("ka_user") == null){
			hash.put("isLogin", false);;
		}else{
			hash.put("isLogin", true);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String news_json = new Gson().toJson(hash);
		response.getWriter().write(news_json);
		System.out.println(news_json);
		return null;
	}

}
