package Controller.CategoryAction;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.sun.mail.handlers.multipart_mixed;

import model.dao.CategoryDAO;
import model.dto.Category;
import Controller.Action;
import Controller.ActionForward;

public class ActionUpdateCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Category dto=new Category();
		CategoryDAO dao=new CategoryDAO();
		
		String savePath = request.getSession().getServletContext().getRealPath("uploads/category");
		
		
				File path = new File(savePath);
				if(!path.exists()){
					path.mkdir();
				}
				
				MultipartRequest multi = new MultipartRequest(request,savePath,5*1024*1024); // Max = 5 MB in Three Files  

				Enumeration filenames = multi.getFileNames();
			
				if(filenames.hasMoreElements()){
					
					File file = multi.getFile((String) filenames.nextElement());
					
//					if(file!=null)	{
//						
//						
//						
//						
//						
//					//String extension = file.getName().substring(file.getName().length()-3);
//					 extension = file.getName().substring(file.getName().lastIndexOf(".")+1);
//					
//					 id = dao.countcategories()+1 ;
//					File newName = new File(savePath+"/"+ id + "."+ extension);
//					
//						
//						if(file.renameTo(newName)){
//							System.out.println("Has been rename successfully!!!");
//							System.out.println(newName.getName());
//							System.out.println(file.getAbsolutePath());
//						}else{
//							System.out.println("Cannot rename!!!");
//						}	
//					
//					dto.setCategorylogourl( "category" + "/" + id + "." +extension);
//					}else{
//						dto.setCategorylogourl(multi.getParameter("categorylogourl"));
//					}
					
					
					if(file == null){
						System.out.println("No file");
						dto.setCategoryid(Integer.parseInt(multi.getParameter("categoryid")));
						dto.setCategoryname(multi.getParameter("categoryname"));
						dto.setMaincategoryid(Integer.parseInt(multi.getParameter("maincategoryid")));
						dto.setCategorylogourl(multi.getParameter("categorylogourl"));
					}else{
						System.out.println("has file");
						
						String extension = file.getName().substring(file.getName().lastIndexOf(".")+1);
						
						int id = Integer.parseInt(multi.getParameter("categoryid"));
				
						File newName = new File(savePath+"/"+ id + "."+ extension);

						java.io.File f = new java.io.File(savePath+"/"+ java.io.File.separator + id + "."+ extension);
						if(f.delete()){
							System.out.println("Deleted file " );
						}else{
							System.out.println("Deleted file Unsuccess ");
						}
						
						if(file.renameTo(newName)){
							System.out.println("Has been rename successfully!!!");
							System.out.println(newName.getName());
							System.out.println(file.getAbsolutePath());
						}else{
							System.out.println("Cannot rename!!!");
						} 
						
						dto.setCategoryid(Integer.parseInt(multi.getParameter("categoryid")));
						dto.setCategoryname(multi.getParameter("categoryname"));
						dto.setMaincategoryid(Integer.parseInt(multi.getParameter("maincategoryid")));
						dto.setCategorylogourl( "category" + "/" + id + "." +extension);
						
						
					}
					
					
				}
				
			/*}*/
		
	
		if(dao.update(dto)){
			System.out.println("Update successfully.");
		}else{
			System.out.println("Fail Update!!!");
		}
		ActionForward forward =new ActionForward();
		forward.setPath("category.act");
		forward.setRedirect(true);
		return forward;
	}

}
