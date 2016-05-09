package Controller.University;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UniversityDAO;
import model.dto.University;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddUniversity implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		University dto = new University();
		UniversityDAO dao = new UniversityDAO();
		
		if(request.getParameter("txtUniversity")!=null){
			dto.setUniversityName(request.getParameter("txtUniversity").toString());
		}
		if(dao.addUniversity(dto)){
			System.out.println("Inserted successfully.");
		}else{
			System.out.println("Inserting Fail!");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("listuniversity.act");
		forward.setRedirect(true);
		
		return forward;
	}

}
