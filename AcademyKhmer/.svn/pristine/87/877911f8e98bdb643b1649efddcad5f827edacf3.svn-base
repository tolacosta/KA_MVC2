package Controller.UserAction;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.dao.UserDAO;
import model.dto.User;
import Controller.Action;
import Controller.ActionForward;

public class ActionChangeCoverPhoto implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward= new ActionForward();
		
		User dto = (User)request.getSession().getAttribute("ka_user");
		UserDAO dao = new UserDAO();
		
		
		String saveFolder = request.getSession().getServletContext().getRealPath("uploads/cover");
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024*2);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletContext c = request.getServletContext();
        String uploadFolder =saveFolder;
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024*1024*2);
		
		
		
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            boolean haspic=false;
            Iterator iter = items.iterator();
            File uploadedFile =null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                int iu= dto.getUserid();
                
                System.out.println(iu);
                
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
                    dto.setCoverphoto("cover/"+iu+ fileName);
                    
                    System.err.println(dto.getCoverphoto());
                    haspic=true;
                    //response.getWriter().print("save success.");
                    System.err.println("Save success");
                }else{
                	
                	
                	
                	// get value from form
                	String value = item.getString();
                	// get name of control of form.
                	String name = item.getFieldName();
                	if(name.equals("cover"))
     	            	dto.setCoverphoto(value);	

   	 	 
   	    	
}}
           
        
            if(dao.updatecover(dto)){
            	forward.setPath("mypublicprofile.act");
            	forward.setRedirect(true);
            	request.getSession().setAttribute("user", dto);
            	System.out.println(dto.getUsername());
            }else{
            	forward.setPath("mypublicprofile.act");
            	forward.setRedirect(true);
            }	
            
            
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex); 
        }
		
		return forward;
	}

}
