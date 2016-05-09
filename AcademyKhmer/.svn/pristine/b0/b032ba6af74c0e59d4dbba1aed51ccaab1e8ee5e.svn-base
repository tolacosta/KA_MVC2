package Controller.MainCategoryAction;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MaincategoryDAO;
import model.dto.Maincategory;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionListMainCategory implements Action{

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
			dto.setMaincategoryid(li.getInt("Maincategoryid"));
			maincategories.add(dto);
		}
		li.close();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String news_json = new Gson().toJson(maincategories);
		response.getWriter().write(news_json);
		return null;
	}

}
