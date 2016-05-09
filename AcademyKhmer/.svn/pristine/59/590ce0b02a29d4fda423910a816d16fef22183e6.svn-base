package Controller.Tutorial;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PlaylistDAO;
import model.dao.TutorialDAO;
import model.dto.Playlist;
import model.dto.Tutorial;
import model.dto.User;
import ultilities.ClsPagination;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionListTutorial implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		ArrayList<Tutorial> tutorial= new ArrayList<Tutorial>();
		
		User user= (User)request.getSession().getAttribute("ka_user");
		System.out.println(user.getUserid());
		Tutorial dto= new Tutorial();
		TutorialDAO dao= new TutorialDAO();
		ResultSet rs = dao.lists(user.getUserid());
		
		while(rs.next()){
			dto = new Tutorial();
			dto.setTutorialid(rs.getInt("tutorialid"));
			dto.setTitle(rs.getString("title"));
			dto.setCategoryid(rs.getInt("categoryid"));
			dto.setCategoryname(rs.getString("categoryname"));
			dto.setUsername(rs.getString("username"));
			dto.setIndex(rs.getInt("index"));
			
			tutorial.add(dto);
		}

		request.setAttribute("tutorial", tutorial);
		forward.setPath("listtutorial.jsp");
		rs.close();
		forward.setRedirect(false);
		return forward;
	}

}
