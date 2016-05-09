package Controller.Department;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DepartmentDAO;
import model.dto.Department;
import Controller.Action;
import Controller.ActionForward;

public class ActionAddDepartment implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String departmentName = request.getParameter("txtDepartmentName");
		ActionForward forward = new ActionForward();
		Department department = new Department();
		DepartmentDAO dao = new DepartmentDAO();
		department.setDepartmentName(departmentName);
		boolean status = dao.addDepartment(department);
		if(status)
			System.out.println("add department successfully!");
		else
			System.out.println("adding fail!");
		forward.setPath("listdepartment.act");
		forward.setRedirect(true);
		return forward;
	}
	
}
