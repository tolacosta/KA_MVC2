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

public class ActionUpdateUser implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	ActionForward forward = new ActionForward();
		
		String saveFolder = request.getSession().getServletContext().getRealPath("uploads/user");
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024*2);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletContext c = request.getServletContext();
        String uploadFolder =saveFolder;
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024*1024*2);
      
        
        
        UserDAO dao = new UserDAO();
//		User dto = new UserModel().getUser(((User)request.getSession().getAttribute("user")).getUser_id()); 		//Get dto from session instead
//        User dto = (User)request.getSession().getAttribute("user");
        User dto = (User)request.getSession().getAttribute("ka_user");
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            boolean haspic=false;
            Iterator iter = items.iterator();
            File uploadedFile =null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                int iu=dto.getUserid();//dao.getMaxCurrentUserid();
                
                
                
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
                	
                	
     	            if(name.equals("email"))
     	            	dto.setEmail(value);
     	            if(name.equals("gender"))
     	            	dto.setGender(value);
     	            
     	           if(name.equals("dateofbirth"))
     	        	
    	             dto.setDateofbirth(new SimpleDateFormat("dd-MM-yyyy").parse(value));
     	          
		     	    if(name.equals("phonenumber"))       
		    	    	dto.setPhonenumber(value);

//		     	   if(name.equals("school"))       
//		    	    	dto.setSchool(value);
//		     	    dto.setUniserityId(Integer.parseInt(request.getParameter("university")));
//		     	    dto.setDepartmentId(Integer.parseInt(request.getParameter("department")));
		     	    if(name.equals("department"))
		     	    	dto.setDepartmentid((Integer.parseInt(value)));
		     	   if(name.equals("university"))
		     	    	dto.setUniversityid(Integer.parseInt(value));
		     	    

//		     	   if(name.equals("school"))       
//		    	    	dto.setSchool(value);
			        

		     	    if(name.equals("usertype"))       
		     	    	dto.setUsertypeid(Integer.parseInt(value));	
            }}
           
           //dto.setUser_id(((User)request.getSession().getAttribute("user")).getUser_id());
          // if(haspic!=true)   dto.setUserimageurl("uploads/user/"+"avatar.jpg");
            
            dto.setUsertypeid(((User)request.getSession().getAttribute("ka_user")).getUsertypeid());
            dto.setUserid(((User)request.getSession().getAttribute("ka_user")).getUserid());
            
          // System.err.println("after "+dto.getUserimageurl());
            if(dao.update(dto)){
            	forward.setRedirect(true);
            	forward.setPath("account.act");
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
