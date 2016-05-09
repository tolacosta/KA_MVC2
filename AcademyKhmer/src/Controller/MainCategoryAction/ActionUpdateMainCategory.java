package Controller.MainCategoryAction;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import model.dao.MaincategoryDAO;
import model.dto.Maincategory;
import Controller.Action;
import Controller.ActionForward;

public class ActionUpdateMainCategory implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Maincategory maincategory = new Maincategory();
		MaincategoryDAO maincategoryDAO = new MaincategoryDAO();

		String savePath = request.getSession().getServletContext().getRealPath("uploads/logo");
		
		File path = new File(savePath);
		if(!path.exists()){
			path.mkdir();
		}
		
		MultipartRequest multi = new MultipartRequest(request,savePath,5*1024*1024); // Max = 5 MB in Three Files  

		Enumeration filenames = multi.getFileNames();
		
		if(filenames.hasMoreElements()){
			
			File file = multi.getFile((String) filenames.nextElement());
			
			if(file == null){
				System.out.println("No file");
				maincategory.setMaincategoryid(Integer.parseInt(multi.getParameter("txtmaincategoryid")));
				maincategory.setMaincategoryname(multi.getParameter("txtmaincategoryname"));
				maincategory.setMaincategorylogourl(multi.getParameter("txtmaincategorylogourl"));
				maincategory.setMaincategoryorder(Integer.parseInt(multi.getParameter("txtmaincategoryorder")));
			}else{
				System.out.println("has file");
				String extension = file.getName().substring(file.getName().lastIndexOf(".")+1);
				
				int id = Integer.parseInt(multi.getParameter("txtmaincategoryid"));
		
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
				
				maincategory.setMaincategoryid(Integer.parseInt(multi.getParameter("txtmaincategoryid")));
				maincategory.setMaincategoryname(multi.getParameter("txtmaincategoryname"));
				maincategory.setMaincategorylogourl( "logo" + "/" + id + "." +extension); 
				maincategory.setMaincategoryorder(Integer.parseInt(multi.getParameter("txtmaincategoryorder")));
							
			}		
			
		}
		
		maincategoryDAO.update(maincategory);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("maincategory.act");
		return forward;
	}

}