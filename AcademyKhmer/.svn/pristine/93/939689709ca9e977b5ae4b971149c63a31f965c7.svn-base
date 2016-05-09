package Controller.CategoryAction;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import model.dao.CategoryDAO;
import model.dto.Category;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Category dto=new Category();
		CategoryDAO dao=new CategoryDAO();
		
		
String savePath = request.getSession().getServletContext().getRealPath("uploads/category");
String extension="";
int id=0;
		File path = new File(savePath);
		if(!path.exists()){
			path.mkdir();
		}
		MultipartRequest multi = new MultipartRequest(request,savePath,5*1024*1024); // Max = 5 MB in Three Files  

		Enumeration filenames = multi.getFileNames();
		
		if(filenames.hasMoreElements()){
			
			File file = multi.getFile((String) filenames.nextElement());
			if(file!=null){
			//String extension = file.getName().substring(file.getName().length()-3);
			 extension = file.getName().substring(file.getName().lastIndexOf(".")+1);
			
			 id = dao.getMaxcategoryId() ;
			
			
			File newName = new File(savePath+"/"+ id + "."+ extension);

			if(file.renameTo(newName)){
				System.out.println("Has been rename successfully!!!");
				System.out.println(newName.getName());
				System.out.println(file.getAbsolutePath());
			}else{
				System.out.println("Cannot rename!!!");
			}
			dto.setCategorylogourl( "category" + "/" + id + "." +extension);
			}	else{
				dto.setCategorylogourl("category/default.jpg");
			}
		}
		
		
		
		
		dto.setMaincategoryid(Integer.parseInt(multi.getParameter("maincategoryid")));
		dto.setCategoryname(multi.getParameter("categoryname"));
		
		
		if(dao.insert(dto)){
			System.out.println("Inserted successfully.");
		}else{
			System.out.println("Fail Inserting!!!");
		}
		ActionForward forward=new ActionForward();
		forward.setPath("category.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
