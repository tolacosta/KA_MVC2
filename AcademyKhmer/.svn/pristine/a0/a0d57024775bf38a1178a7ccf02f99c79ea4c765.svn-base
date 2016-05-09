package Controller.University;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TutorialDAO;
import model.dao.UniversityDAO;
import model.dto.Tutorial;
import model.dto.University;
import Controller.Action;
import Controller.ActionForward;

public class ActionUpdateUniversity implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		int universityid =0;
		String unversityname = null ;
		
		if(request.getParameter("txtNumber")!=null){
			universityid = Integer.parseInt(request.getParameter("txtNumber"));
		}

		if(request.getParameter("txtUniversity")!=null){
			unversityname= request.getParameter("txtUniversity").toString();
		}
		 UniversityDAO dao = new UniversityDAO();
		 University dto = new University();
		 dto.setUniversityId(universityid);
		 dto.setUniversityName(unversityname);
		 
		if(dao.updateUniversity(dto)){
			System.out.println("Successfully Updated!");
		}else{
			System.out.println("Updated Fail!");
		}
		
		forward.setPath("listuniversity.act");
		forward.setRedirect(true);
		return forward;
	}

}
