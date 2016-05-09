package Controller.Home;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DepartmentDAO;
import model.dao.UniversityDAO;
import model.dto.Department;
import model.dto.University;
import Controller.Action;
import Controller.ActionForward;

public class ActionGetInformation implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ArrayList<Department> listDepartment = new ArrayList<Department>();
			ArrayList<University> listUniversity = new ArrayList<University>();
			ResultSet rs = new DepartmentDAO().listDepartment();
			ResultSet rs1 = new UniversityDAO().listUniversity();
			while(rs.next()){
				Department department = new Department();
				department.setDepartmentId(rs.getInt(1));
				department.setDepartmentName(rs.getString(2));
				listDepartment.add(department);
			}
			
			
			while(rs1.next()){
				University university = new University();
				university.setUniversityId(rs1.getInt(1));
				university.setUniversityName(rs1.getString(2));
				listUniversity.add(university);
			}
			request.setAttribute("listDepartment", listDepartment);
			request.setAttribute("listUnivsersity", listUniversity);
			ActionForward forward = new ActionForward();
			forward.setPath("dashboard.jsp");
			forward.setRedirect(false);
		return forward;
	}
	
	

}
