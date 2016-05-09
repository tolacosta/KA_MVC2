package Controller.VideoAction;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VideoDAO;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionUpdateVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		
		
		int videoid = 0;
		String videoname = null;
		String description = null;
		String youtubeurl = null;
		String fileurl = null;
		boolean publicview = false;
		java.util.Date postdate = null;
		
		if(request.getParameter("v")!=null)
			videoid=Integer.parseInt(request.getParameter("v").toString());
		
		if(request.getParameter("videoname")!=null)
			videoname=request.getParameter("videoname").toString();
		
		if(request.getParameter("description")!=null)
			description=request.getParameter("description").toString();
		
		if(request.getParameter("youtubeurl")!=null)
			youtubeurl=request.getParameter("youtubeurl").toString();
		
		if(request.getParameter("fileurl")!=null)
			fileurl=request.getParameter("fileurl").toString();
		
		publicview=false;
		if(request.getParameter("publicview")!=null)
			if(request.getParameter("publicview")=="1")
				publicview=true;		//parameter is set
		
		if(request.getAttribute("fileurl")!=null)
			fileurl=request.getAttribute("fileurl").toString();
		
		User u = (User) request.getSession().getAttribute("ka_user");
//						u = new User();u.setDeleteable(true);u.setUserid(2);
		Video dto = new VideoDAO().get(videoid, false); 
		if(dto.getUserid()==u.getUserid() || u.isDeleteable()){
			dto.setVideoname(videoname);
			dto.setDescription(description);
			dto.setYoutubeurl(youtubeurl);
			dto.setFileurl(fileurl);
			dto.setPublicview(publicview);
			dto.setPostdate(postdate);
			new VideoDAO().update(dto);
			
			String categorylist[] = request.getParameterValues("category");
			new VideoDAO().removefromcategory(videoid);
			for(int i=0; i<categorylist.length; i++){
				int categoryid = Integer.parseInt(categorylist[i]);
				new VideoDAO().addtocategory(videoid, categoryid);
			}
		}

		forward.setPath("videolist.act");
		forward.setRedirect(true);
		
		return forward;
	}
}
