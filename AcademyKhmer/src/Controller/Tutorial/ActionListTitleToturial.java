package Controller.Tutorial;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.TutorialDAO;
import model.dto.Tutorial;
import Controller.Action;
import Controller.ActionForward;

public class ActionListTitleToturial implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int catid= Integer.parseInt(request.getParameter("categoryid"));
		ResultSet rs= new TutorialDAO().list(catid);
		ArrayList<Tutorial> arr = new ArrayList<Tutorial>();
		while(rs.next()){
			Tutorial dto= new Tutorial();
			dto.setTutorialid(rs.getInt("tutorialid"));
			dto.setTitle(rs.getString("title"));
			dto.setCategoryname(rs.getString("categoryname"));
			arr.add(dto);
		}
		rs.close();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String tutorial = new Gson().toJson(arr);
		response.getWriter().write(tutorial);
		
		
		System.err.println(tutorial);
		return null;
	}

}
