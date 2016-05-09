package Controller.CategoryAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.dto.Category;
import ultilities.ClsPagination;
import Controller.Action;
import Controller.ActionForward;

import com.google.gson.Gson;

public class ActionListCategory implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page=1;
		int limit=3;
	
		String  search = "";
		ArrayList<Category> list=new ArrayList<>();
		Category dto=new Category();
		CategoryDAO dao=new CategoryDAO();
		ClsPagination pagin = new ClsPagination();
		
		
		if(request.getParameter("page")!=null)
			page = Integer.parseInt(request.getParameter("page").toString());
		if(request.getParameter("setNumrow")!=null)
			pagin.setPerPage(Integer.parseInt(request.getParameter("setNumrow").toString()));
		if(request.getParameter("tfsearch")!=null)
			search =request.getParameter("tfsearch");
		
	
		
		
		pagin.setTotalCount(dao.countcategories());
		pagin.setCurrentPage(page);
		
		
		
		ResultSet rs=dao.list(search,pagin.getCurrentPage(),pagin.getPerPage());
		while(rs.next()){
			dto=new Category();
			dto.setCategoryid(rs.getInt("categoryid"));
			dto.setCategoryname(rs.getString("categoryname"));
			dto.setMaincategoryname(rs.getString("maincategoryname"));
			dto.setCategorylogourl(rs.getString("categorylogourl"));
			dto.setCountvideos(rs.getInt("countvideos"));
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
