package Controller.Tutorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TutorialDAO;
import model.dao.VideoDAO;
import model.dto.Tutorial;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionEditTutorial implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		User users = (User)request.getSession().getAttribute("ka_user");
		
		int tutorialid= 0;
		String title= null;
		String description= null;
		int index=0;
		int categoryid= 0;
		
		
		
		if(request.getParameter("tutorialid")!=null){
			tutorialid = Integer.parseInt(request.getParameter("tutorialid").toString());
		}

		if(request.getParameter("txttitle")!=null){
			title= request.getParameter("txttitle").toString();
		}
		if(request.getParameter("txtdescription")!=null){
			description = request.getParameter("txtdescription").toString();
		}
		if(request.getParameter("txtindex")!=null){
			index = Integer.parseInt(request.getParameter("txtindex").toString());
		}
		if(request.getParameter("txtcategory")!=null){
			categoryid = Integer.parseInt(request.getParameter("txtcategory").toString());
		}
		
		User u = (User) request.getSession().getAttribute("ka_user");
			//		u = new User();u.setDeleteable(true);u.setUserid(2);
			Tutorial dto = new TutorialDAO().get(tutorialid); 
			
			if(dto.getUserid()==u.getUserid() || u.isDeleteable()){
			dto.setTitle(title);
			dto.setDescription(description);
			dto.setIndex(index);
			dto.setUserid(users.getUserid());
			dto.setCategoryid(categoryid);
			new TutorialDAO().update(dto);
			
	
			}
		

		forward.setPath("listtutorial.act");
		forward.setRedirect(false);
		
		return forward;
	}

}
