package Controller.UserAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ultilities.EncryptDecrypt;

import com.google.gson.Gson;
import com.lowagie.toolbox.plugins.Encrypt;

import model.dao.UserDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionSendMailToChPWD implements  Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String email = "";
		if(request.getParameter("forgotpwdemail")!= null){
			email = request.getParameter("forgotpwdemail");
		}
		
		UserDAO dao = new UserDAO();
		if(dao.checkEmailPWD(email)){
			
			System.err.println("has");
			
			String code =dao.getUserPwdByEmail(email);
		
			System.out.println("code: "+EncryptDecrypt.encrypt(code));
			if(dao.sendBCC(email,  "Password Resset" , "<h1>Khmer Academy</h1><p>Please preserve the subject! This is important for correct mail handling.</p>"
												   +"<p>Welcome!</p>"
												   +"<p>You've rquested password reset for your account with Khmer Academy</p> "
												   + "<p>To complete the process please copy the codes below:</p>"
												   + "<p>Code: <strong style='color:red'>"+EncryptDecrypt.encrypt(code)+"</strong></p>"
												   + "<p>Regards</p>"
												   + "<p>Khmer Academy support team<br/>"
												   + "<a href='www.khmeracademy.org' target='_blank'>www.khmeracademy.org</a>"
												   + "</p>")){
				
				response.getWriter().write(new Gson().toJson("sent"));
				System.err.println("sent");
			}else{
				response.getWriter().write(new Gson().toJson("send error"));
				System.err.println("send error");
			}
		
			
			
		}else{
			response.getWriter().write(new Gson().toJson("no"));
			System.err.println("no");
		}
		
		return null;
	}

}
