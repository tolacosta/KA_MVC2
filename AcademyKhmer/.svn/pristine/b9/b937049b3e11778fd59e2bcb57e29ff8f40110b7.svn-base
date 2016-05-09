package Controller.University;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.dao.UniversityDAO;
import model.dto.University;
import Controller.Action;
import Controller.ActionForward;

public class ActionListUniversity implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
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

		request.setAttribute("university", university);
		forward.setPath("listuniversity.jsp");
		//rs.close();
		forward.setRedirect(false);
		return forward;
	}

}
