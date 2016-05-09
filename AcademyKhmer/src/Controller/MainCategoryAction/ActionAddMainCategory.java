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

public class ActionAddMainCategory implements Action{

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
		
			String extension = file.getName().substring(file.getName().lastIndexOf(".")+1);
			
			int id = maincategoryDAO.getMaxMaincategoryId() ;
						
			File newName = new File(savePath+"/"+ id + "."+ extension);

			if(file.renameTo(newName)){
				System.out.println("Has been rename successfully!!!");
				System.out.println(newName.getName());
				System.out.println(file.getAbsolutePath());
			}else{
				System.out.println("Cannot rename!!!");
			} 
		
			maincategory.setMaincategoryname(multi.getParameter("txtmaincategoryname"));
			maincategory.setMaincategorylogourl( "logo" + "/" + id + "." +extension);  
			
			maincategoryDAO.insert(maincategory);
						
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("maincategory.act");
		return forward;
	}

}
