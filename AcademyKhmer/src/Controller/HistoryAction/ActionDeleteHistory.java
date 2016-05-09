package Controller.HistoryAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HistoryDAO;
import model.dao.PlaylistDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteHistory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
try{
			
			HistoryDAO dao = new HistoryDAO();
			
			int historyid = 0, page = 1;
			if(request.getParameter("historyid") != null){
				historyid = Integer.parseInt(request.getParameter("historyid").toString());
			}
			if(request.getParameter("page") != null){
				page = Integer.parseInt(request.getParameter("page").toString());
			}
			
			if(dao.delete(historyid)){
				System.out.println("Success");
			}else{
				System.out.println("UNSuccess");
			}
			
			System.out.println(historyid);
			System.out.println(page);

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
