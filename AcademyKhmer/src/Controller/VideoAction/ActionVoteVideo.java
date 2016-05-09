package Controller.VideoAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VoteDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionVoteVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User u = (User) request.getSession().getAttribute("ka_user");
//						u = new User();u.setViewable(true);u.setUserid(2);
		
		int videoid = Integer.parseInt(request.getParameter("v"));
		int type = Integer.parseInt(request.getParameter("type"));
		int plus=0, minus=0;
		if(type>0){
			type=1;
			plus = new VoteDAO().voteplus(u.getUserid(), videoid);
			minus = new VoteDAO().countminus(videoid);
		}else if(type<0){
			type=-1;
			minus = new VoteDAO().voteminus(u.getUserid(), videoid);
			plus = new VoteDAO().countplus(videoid);
		}else{
			new VoteDAO().unvote(u.getUserid(), videoid);
			plus = new VoteDAO().countplus(videoid);
			minus = new VoteDAO().countminus(videoid);
		}
		
		int uservote = new VoteDAO().checkuservote(u.getUserid(), videoid);
		
		String text = "";
		text += "<a href=\"javascript:votes('vote.act?v="+videoid+"&type=";
				if(uservote>0) text+="0"; else text+="1";
		text += "')\"><i class=\"fa fa-thumbs-up fa-2x\" style=\"padding:0px 10px; color: #3BAFDA;\"></i>"+plus+"</a>" + 
				
				"<a href=\"javascript:votes('vote.act?v="+videoid+"&type=";
				if(uservote<0) text+="0"; else text+="-1";
		text += "')\"><i class=\"fa fa-thumbs-down fa-2x\" style=\"padding:0px 10px; color: #3BAFDA;\"></i>"+minus+"</a>";
		response.getWriter().print(text);
		
		return null;
	}
}
