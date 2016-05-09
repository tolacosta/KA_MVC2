package Controller.Department;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DepartmentDAO;
import Controller.Action;
import Controller.ActionForward;

public class ActionDeleteDepartment implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//ActionForward forward = new ActionForward();
		int departmentId = Integer.parseInt(request.getParameter("departmentId").trim());
		DepartmentDAO department = new DepartmentDAO();
		boolean status = department.deleteDepartment(departmentId);
		if(status)
			System.out.println("delete successfuly!");
		else
			System.out.println("delete fail!");
		//forward.setPath("listdepartment.act");
		//forward.setRedirect(false);
		return null;
	}

}
