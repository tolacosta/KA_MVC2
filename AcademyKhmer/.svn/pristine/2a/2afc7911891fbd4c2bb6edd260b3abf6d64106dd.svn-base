package Controller.VideoAction;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VideoDAO;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionFrontAddVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		String videoname = null;
		String description = null;
		String youtubeurl = null;
		String fileurl = null;
		boolean publicview = false;
		java.util.Date postdate = null;
		int userid;
		
		if(request.getParameter("videoname")!=null)
			videoname=request.getParameter("videoname").toString();
		
		if(request.getParameter("description")!=null)
			description=request.getParameter("description").toString();
		
		if(request.getParameter("youtubeurl")!=null)
			youtubeurl=request.getParameter("youtubeurl").toString();
		
		if(request.getParameter("fileurl")!=null){
			fileurl=request.getParameter("fileurl").toString();
		}else{
			fileurl="#";
		}
		if(request.getParameter("publicview")!=null)
			publicview=true;		//parameter is set
		
		if(request.getParameter("postdate")!=null)
			postdate=new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("postdate").toString());
		
		if(request.getAttribute("fileurl")!=null)
			fileurl=request.getAttribute("fileurl").toString();
		
		userid=((User) request.getSession().getAttribute("ka_user")).getUserid();
//		userid=1;
		
		Video dto = new Video(0, videoname, description, youtubeurl, fileurl, publicview, postdate, userid, 0);
		int videoid = new VideoDAO().insert(dto);
		
		if(request.getParameterValues("category")!= null){
		String categorylist[] = request.getParameterValues("category");		
		for(int i=0; i<categorylist.length; i++){
			int categoryid = Integer.parseInt(categorylist[i]);
			new VideoDAO().addtocategory(videoid, categoryid);
		}
		
		forward.setPath("mypublicprofile.act");
		forward.setRedirect(true);
		
		System.err.println(request.getParameterValues("category") + " has");
		
		}else{
			System.err.println(request.getParameterValues("category") + " no");
			request.setAttribute("errorcat", "The file category can't be empty!");
			forward.setPath("video.act");
			forward.setRedirect(false);
			return forward;
		}
		
		
		return forward;
	}
}
