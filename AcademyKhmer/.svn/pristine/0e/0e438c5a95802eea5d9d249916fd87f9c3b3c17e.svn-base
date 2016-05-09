package Controller.Department;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DepartmentDAO;
import model.dto.Department;
import Controller.Action;
import Controller.ActionForward;

public class ActionUpdateDepartment implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ActionForward forward = new ActionForward();
			Department department = new Department();
			department.setDepartmentId(Integer.parseInt(request.getParameter("departmentId").trim()));
			department.setDepartmentName(request.getParameter("txtDepartmentName").trim());
			DepartmentDAO dao = new DepartmentDAO();
			boolean status = dao.updateDepartment(department);
			if(status)
				System.out.println("update successfuly!");
			else
				System.out.println("update fail!");
			forward.setPath("listdepartment.act");
			forward.setRedirect(true);
		return forward;
	}

}
