package Controller.shortcoure;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;
import model.dao.UniversityDAO;
import model.dto.University;

public class ActionListUni implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ArrayList<University> university= new ArrayList<University>();
		//University dto= new University();
		UniversityDAO dao= new UniversityDAO();
		ResultSet rs = dao.listUniversity();
		
		while(rs.next()){
			University dto = new University();
			dto.setUniversityId(rs.getInt("universityId"));
			dto.setUniversityName(rs.getString("universityName"));
			university.add(dto);
		}
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("uni", university);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String universityS = new Gson().toJson(hash);
		System.out.println(universityS);
		response.getWriter().write(universityS);
		
		rs.close();
		return null;
	}
	
	
	

}
