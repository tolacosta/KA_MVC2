package Controller.Tutorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TutorialDAO;
import model.dto.Tutorial;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddTutorial implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Tutorial dto=new Tutorial();
		TutorialDAO dao=new TutorialDAO();
		
		User users = (User)request.getSession().getAttribute("ka_user");
		
		if(request.getParameter("txttitle")!=null){
			dto.setTitle(request.getParameter("txttitle").toString());
		}
		if(request.getParameter("txtdescription")!=null){
			dto.setDescription(request.getParameter("txtdescription"));
		}
		System.out.println(dto.getDescription());
		if(request.getParameter("txtindex")!=null){
			dto.setIndex(Integer.parseInt(request.getParameter("txtindex").toString()));
		}
		if(request.getParameter("txtcategory")!=null){
			dto.setCategoryid(Integer.parseInt(request.getParameter("txtcategory").toString()));
		}
		 
		dto.setUserid(users.getUserid());
		
		if(dao.insert(dto)){
			System.out.println("Inserted successfully.");
		}else{
			System.out.println("Inserting Fail!");
		}

		ActionForward forward = new ActionForward();
		forward.setPath("listtutorial.act");
		forward.setRedirect(true);
		return forward;
	}
}
