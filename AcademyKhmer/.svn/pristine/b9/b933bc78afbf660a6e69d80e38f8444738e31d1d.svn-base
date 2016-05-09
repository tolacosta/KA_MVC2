package Controller.VideoAction;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.CategoryDAO;
import model.dao.VideoDAO;
import model.dto.Category;
import model.dto.User;
import model.dto.Video;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetVideo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		ResultSet rs = new CategoryDAO().list();

		ArrayList<Category> arr = new ArrayList<Category>();
		while(rs.next()){
			Category dto = new Category(rs.getInt("categoryid"), rs.getString("categoryname"), rs.getString("categorylogourl"));
			arr.add(dto);
		}
		
		String category_json=new Gson().toJson(arr);
		request.setAttribute("category_json", category_json);
		
		if(request.getParameter("v")!=null){
			int videoid=0;
			videoid=Integer.parseInt(request.getParameter("v").toString());
			Video dto = new VideoDAO().get(videoid, false);
			User u = (User) request.getSession().getAttribute("ka_user");
//						u = new User();u.setViewable(true);u.setUserid(2);
			if(dto.getUserid()==u.getUserid() || u.isDeleteable()){
				request.setAttribute("video", dto);
			}
		}
	
		forward.setPath("addvideo.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
