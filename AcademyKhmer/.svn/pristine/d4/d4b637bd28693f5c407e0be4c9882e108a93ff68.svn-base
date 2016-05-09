package Controller.HistoryAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.ClsPagination;

import com.google.gson.Gson;

import model.dao.HistoryDAO;
import model.dto.History;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionListHistory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int page=1;
		String search ="";
		ClsPagination pagin = new ClsPagination();
		
		if(request.getParameter("page")!=null)
			page = Integer.parseInt(request.getParameter("page").toString());
		if(request.getParameter("setNumrow")!=null)
			pagin.setPerPage(Integer.parseInt(request.getParameter("setNumrow").toString()));
		if(request.getParameter("tfsearch")!=null)
			search =request.getParameter("tfsearch");
		int userid = ((User) request.getSession().getAttribute("ka_user")).getUserid();
		
		HistoryDAO historydao = new HistoryDAO();
		ResultSet li = historydao.list(search, userid ,page, pagin.getPerPage() );
		ArrayList<History> history = new ArrayList<History>();
	
		while (li.next()){
			History dto = new History();
			dto.setHistoryid(li.getInt("historyid"));
			dto.setHistorydate(li.getDate("historydate"));
			dto.setUserid(li.getInt("userid"));
			dto.setUsername(li.getString("username"));
			dto.setVideoid(li.getInt("videoid"));
			dto.setVideoname(li.getString("videoname"));
			dto.setVideourl(li.getString("youtubeurl"));
			dto.setVideodescription(li.getString("description"));
			dto.setVideoviewcount(li.getString("viewcount"));
			history.add(dto);
			
		}
		li.close();
		
		pagin.setTotalCount(historydao.count(search, userid ));
		pagin.setCurrentPage(page);
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("history",history);
		hash.put("pagination",pagin);
		hash.put("totalpages",pagin.totalPages());
		hash.put("currentpage",pagin.getCurrentPage());
		hash.put("hasNext", pagin.hasNextPage());
		hash.put("hasPrevious", pagin.hasPreviousPage());
		hash.put("nextpage", pagin.nextPage());
		hash.put("previouspage", pagin.previousPage());
		
		//end pagination
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String hist_json = new Gson().toJson(hash);
		System.out.println(hist_json);
		response.getWriter().write(hist_json);
		return null;
	}

}
