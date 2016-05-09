package Controller.CategoryAction;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MaincategoryDAO;
import model.dto.Maincategory;
import Controller.Action;
import Controller.ActionForward;

public class ActionToAddCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MaincategoryDAO dao=new MaincategoryDAO();
		Maincategory dto=null;
		ArrayList<Maincategory> list=new ArrayList<Maincategory>();
		ResultSet rs=dao.list();
		while(rs.next()){
			dto=new Maincategory();
			dto.setMaincategoryid(rs.getInt("maincategoryid"));
			dto.setMaincategoryname(rs.getString("maincategoryname"));
			list.add(dto);			
		}
		request.setAttribute("list_main_category", list);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("addcategory.jsp");
		
		
		return forward;
	}

}
