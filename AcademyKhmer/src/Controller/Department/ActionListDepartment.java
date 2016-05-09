package Controller.Department;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DepartmentDAO;
import model.dto.Department;
import Controller.Action;
import Controller.ActionForward;

public class ActionListDepartment implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DepartmentDAO dao = new DepartmentDAO();
		ActionForward forward = new ActionForward();
		ArrayList<Department> list = new ArrayList<Department>();
		ResultSet rs = dao.listDepartment();
		while(rs.next()){
			Department department = new Department();
			department.setDepartmentId(rs.getInt(1));
			department.setDepartmentName(rs.getString(2));
			list.add(department);
		}
		request.setAttribute("listDepartment", list);
		forward.setPath("listdepartment.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
