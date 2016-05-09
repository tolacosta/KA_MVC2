package Controller.VideoAction;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VideoDAO;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddVideo implements Action {

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
		if(!((User) request.getSession().getAttribute("ka_user")).isPostable()){
			forward.setPath("videolist.act");
			forward.setRedirect(true);
			
			return forward;
		}
		if(request.getParameter("videoname")!=null)
			videoname=request.getParameter("videoname").toString();
		
		if(request.getParameter("description")!=null)
			description=request.getParameter("description").toString();
		
		if(request.getParameter("youtubeurl")!=null)
			youtubeurl=request.getParameter("youtubeurl").toString();
		
		if(request.getParameter("fileurl")!=null)
			fileurl=request.getParameter("fileurl").toString();
		
		if(request.getParameter("publicview")!=null)
			if(request.getParameter("publicview")=="1")
				publicview=true;		//parameter is set
		
		if(request.getParameter("postdate")!=null)
			postdate=new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("postdate").toString());
		
		if(request.getAttribute("fileurl")!=null)
			fileurl=request.getAttribute("fileurl").toString();
		
		userid=((User) request.getSession().getAttribute("ka_user")).getUserid();
		
		Video dto = new Video(0, videoname, description, youtubeurl, fileurl, publicview, postdate, userid, 0);
		int videoid = new VideoDAO().insert(dto);
		
		String categorylist[] = request.getParameterValues("category");
		for(int i=0; i<categorylist.length; i++){
			int categoryid = Integer.parseInt(categorylist[i]);
			new VideoDAO().addtocategory(videoid, categoryid);
		}
		
		forward.setPath("videolist.act");
		forward.setRedirect(true);
		
		return forward;
	}
}
