package Controller.University;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UniversityDAO;
import model.dto.University;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteUniversity implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		UniversityDAO dao = new UniversityDAO();
		
		if(request.getParameter("universityid")!=null){
			int universityId = Integer.parseInt(request.getParameter("universityid"));
			if(dao.deleteUniversity(universityId)){
				System.out.println("Deleted Successfully");
			}else{
				System.out.println("Deleting Failed!!!");
			}
		}
		forward.setPath("listuniversity.act");
		forward.setRedirect(false);
		return forward;
	}

}
