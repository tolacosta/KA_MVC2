package Controller.UserAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.ClsPagination;
import model.dao.UserDAO;
import model.dao.UsertypeDAO;
import model.dto.User;
import model.dto.Usertype;

import com.google.gson.Gson;

import Controller.Action;
import Controller.ActionForward;

public class ActionAdminListAllUser implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<User> a = new ArrayList<User>();
		UserDAO dao = new UserDAO();
		User dto = new User();
		
		ClsPagination pagin = new ClsPagination();
		
		if(request.getParameter("page")!=null)
			pagin.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		if(request.getParameter("setNumrow")!=null)
			pagin.setPerPage(Integer.parseInt(request.getParameter("setNumrow").toString()));
		if(request.getParameter("tfsearch")!=null)
			dto.setUsername(request.getParameter("tfsearch"));
		else
			dto.setUsername("");
		
		ResultSet rs = dao.addminListAllUser(dto.getUsername() ,pagin );
		pagin.setTotalCount(dao.adminCountUsers(dto.getUsername()));
		while (rs.next()) {
			dto = new User();
			dto.setUserid(rs.getInt("userid"));
			dto.setEmail(rs.getString("email"));
			dto.setPassword(rs.getString("password"));
			dto.setUsername(rs.getString("username"));
			dto.setGender(rs.getString("gender"));
			dto.setDateofbirth(rs.getDate("dateofbirth"));
			dto.setPhonenumber(rs.getString("phonenumber"));
			dto.setRegisterdate(rs.getDate("registerdate"));
			dto.setUserimageurl(rs.getString("userimageurl"));
			dto.setUsertypeid(rs.getInt("usertypeid"));
			dto.setUsertypename(rs.getString("usertypename"));
			dto.setViewable(rs.getBoolean("viewable"));
			dto.setCommentable(rs.getBoolean("commentable"));
			dto.setPostable(rs.getBoolean("postable"));
			dto.setDeleteable(rs.getBoolean("deleteable"));
			dto.setUserable(rs.getBoolean("userable"));
			dto.setCountvideos(rs.getInt("countvideos"));
			dto.setCountcomments(rs.getInt("countcomments"));
			dto.setCountvoteminus(rs.getInt("countvoteminus"));
			dto.setCountvoteplus(rs.getInt("countvoteplus"));
			dto.setCountplaylists(rs.getInt("COUNTPLAYLIST"));
			a.add(dto);
		}
		
		ArrayList<Usertype> usertype = new ArrayList<Usertype>();
		UsertypeDAO tdao = new UsertypeDAO();
		Usertype tdto = new Usertype();
		ResultSet rs1  =  tdao.seleteAllUserType(); 
		while (rs1.next()) {
			tdto = new Usertype();
			tdto.setUsertypeid(rs1.getInt("usertypeid"));
			tdto.setUsertypename(rs1.getString("usertypename"));
			usertype.add(tdto);
		}
		
		rs.close();
		rs1.close();
		
		HashMap<String, Object> hash = new HashMap<String,Object>();
		hash.put("adminListUser", a);
		hash.put("pagination",pagin);
		hash.put("totalpages",pagin.totalPages());
		hash.put("currentpage",pagin.getCurrentPage());
		hash.put("hasNext", pagin.hasNextPage());
		hash.put("hasPrevious", pagin.hasPreviousPage());
		hash.put("nextpage", pagin.nextPage());
		hash.put("previouspage", pagin.previousPage());
		hash.put("usertype", usertype);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String adminlistuser_json = new Gson().toJson(hash);
		System.out.println(adminlistuser_json);
		response.getWriter().write(adminlistuser_json);
		
		return null;
	}

}
