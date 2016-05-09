package Controller.ForumCategory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.ClsPagination;

import com.google.gson.Gson;

import model.dao.CategoryDAO;
import model.dao.ForumCategoryDAO;
import model.dto.Category;
import model.dto.ForumCategory;
import Controller.Action;
import Controller.ActionForward;

public class ActionListForumCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int page=1;
		int limit=3;
	
		String  search = "";
		ArrayList<ForumCategory> list=new ArrayList<ForumCategory>();
		ForumCategory dto=null;
		ForumCategoryDAO dao=new ForumCategoryDAO();
		ClsPagination pagin = new ClsPagination();
		
		
		if(request.getParameter("page")!=null)
			page = Integer.parseInt(request.getParameter("page").toString());
		if(request.getParameter("setNumrow")!=null)
			pagin.setPerPage(Integer.parseInt(request.getParameter("setNumrow").toString()));
		if(request.getParameter("tfsearch")!=null)
			search =request.getParameter("tfsearch");
		
	
		
		
		pagin.setTotalCount(dao.countcategories());
		pagin.setCurrentPage(page);
		//pagin.setPerPage(2);
		
		
		
		ResultSet rs=dao.list(search,pagin.getCurrentPage(),pagin.getPerPage());
		while(rs.next()){
			dto=new ForumCategory();
			dto.setCategory_id(rs.getInt("categoryid"));
			dto.setCategory_name(rs.getString("categoryname"));
			list.add(dto);
		}
		
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("categorylist", list);
		hash.put("pagination",pagin);
		hash.put("totalpages",pagin.totalPages());
		hash.put("currentpage",pagin.getCurrentPage());
		hash.put("hasNext", pagin.hasNextPage());
		hash.put("hasPrevious", pagin.hasPreviousPage());
		hash.put("nextpage", pagin.nextPage());
		hash.put("previouspage", pagin.previousPage());
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String json=new Gson().toJson(hash);
		System.out.println(json);
		response.getWriter().write(json);
		return null;

	}
}
