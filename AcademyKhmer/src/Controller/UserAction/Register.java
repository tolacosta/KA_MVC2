package Controller.UserAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ultilities.*;
import Controller.Action;
import Controller.ActionForward;
import model.dao.UserDAO;
import model.dto.User;
import model.dao.*;
public class Register implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String info = request.getSession().getServletContext().getRealPath("uploads/info.txt");
     String email=request.getParameter("exampleInputEmail1");
		    new SendMailSSL(info).sendBCC(email,"Korea Software HRD automatic response","Dear "+email +"  ");
		        response.getWriter().print("Success");
		return null;
	}

}
