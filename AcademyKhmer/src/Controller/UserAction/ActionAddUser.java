package Controller.UserAction;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.dto.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Controller.Action;
import Controller.ActionForward;

public class ActionAddUser implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		String saveFolder = request.getSession().getServletContext().getRealPath("uploads");
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024*2);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletContext c = request.getServletContext();
        String uploadFolder =saveFolder;
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024*1024*2);
        String fname="",flname="";
        String mname="",mlname="";
        
        
        UserDAO dao = new UserDAO();
//		User dto = new UserModel().getUser(((User)request.getSession().getAttribute("user")).getUser_id()); 		//Get dto from session instead
//        User dto = (User)request.getSession().getAttribute("user");
        User dto = new User();
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            boolean haspic=false;
            Iterator iter = items.iterator();
            File uploadedFile =null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                int iu=dao.getMaxCurrentUserid();
                if (!item.isFormField()) {
                	if(item.getName()==""||item.getName()==null) continue;
                		
                    String fileName = new File(item.getName()).getName().substring(item.getName().lastIndexOf("."),item.getName().length());

                    
                    System.out.println(iu);
                    //String filePath = uploadFolder + File.separator +((User)request.getSession().getAttribute("user")).getUserid()+ fileName;
                    String filePath = uploadFolder + File.separator +iu+ fileName;
                    uploadedFile = new File(filePath);
                    
                    // saves the file to upload directory
                    item.write(uploadedFile);
                    //dto.setPhoto("user/"+((User)request.getSession().getAttribute("user")).getUser_id()+ fileName);
                    dto.setUserimageurl("user/"+iu+ fileName);
                    
                    System.err.println(dto.getUserimageurl());
                    haspic=true;
                    //response.getWriter().print("save success.");
                    System.err.println("Save success");
                }else{
                	
                	
                	
                	// get value from form
                	String value = item.getString();
                	// get name of control of form.
                	String name = item.getFieldName();
                	if(name.equals("username"))
     	            	dto.setUsername(value);
                	
                	//System.out.println(dto.getFirst_name());
                	
     	            if(name.equals("password"))
	            			dto.setPassword(value);
     	            
     	            if(name.equals("email"))
     	            	dto.setEmail(value);
     	            if(name.equals("gender"))
     	            	dto.setGender(value);
     	            
     	           if(name.equals("dateofbirth"))
     	        	
    	             dto.setDateofbirth(new SimpleDateFormat("dd-MM-yyyy").parse(value));
     	          
                
		     	    if(name.equals("phonenumber"))       
		    	    	dto.setPhonenumber(value);
		     	   
			        
		     	    if(name.equals("usertype"))       
		     	    	dto.setUsertypeid(Integer.parseInt(value));;
		     	   
		    	
		  	
   	 	 
   	    	
}}
           
           //dto.setUser_id(((User)request.getSession().getAttribute("user")).getUser_id());
           if(haspic!=true)   dto.setUserimageurl("uploads/user/"+"avatar.jpg");
            
     
           System.err.println("after "+dto.getUserimageurl());
            if(dao.insert(dto)){
            	forward.setRedirect(true);
            	forward.setPath("_dashboard.jsp");
            	request.getSession().setAttribute("user", dto);
            }	
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex); 
        }
		
		return forward;
	}

}
