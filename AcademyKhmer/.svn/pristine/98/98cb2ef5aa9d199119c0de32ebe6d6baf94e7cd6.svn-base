package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import model.dto.Log;

public class LogDAO {

	Connection con;
	public LogDAO() throws ClassNotFoundException, NamingException, SQLException{
		con = new DBUtility().getConnect();
	}
	
	public int insert(Log dto) throws SQLException{
		try {
			String sql = "INSERT INTO TBLLOG VALUES(NEXTVAL('seq_log'), ?, ?, NOW(), NOW())";
			PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, dto.getUserid());
			ps.setInt(2, dto.getVideoid());
			if(ps.executeUpdate()>0){
				ResultSet rs=ps.getGeneratedKeys();
				if(rs.next()){
					return rs.getInt(1); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return 0;
	}
	
	
	public boolean stopWatching(Log dto) throws SQLException{
		try {
			String sql = "UPDATE TBLLOG Set stoptime=NOW() WHERE LOGID=? AND USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getLogid());
			ps.setInt(2, dto.getUserid());
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public ResultSet listUserInCategory(int categoryid) throws SQLException{
		try {
			String sql = "SELECT U.Userid, U.Username, COUNT(L.Videoid) Views, SUM(L.Stoptime-L.Starttime) Duration FROM TBLUser U INNER JOIN TBLLog L ON U.Userid=L.Userid INNER JOIN TBLVideo V ON L.Videoid=V.Videoid INNER JOIN TBLCategoryVideo CV ON V.Videoid=CV.Videoid WHERE CV.Categoryid=? GROUP BY U.Userid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryid);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet listCategory() throws SQLException{
		try {
			String sql = "SELECT C.Categoryid, C.CategoryName, COUNT(L.Videoid) Views, SUM(L.Stoptime-L.Starttime) Duration FROM TBLLog L INNER JOIN TBLVideo V ON L.Videoid=V.Videoid INNER JOIN TBLCategoryVideo CV ON V.Videoid=CV.Videoid INNER JOIN TBLCategory C ON C.Categoryid=CV.Categoryid GROUP BY C.Categoryid";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet listCategoryInUser(int userid) throws SQLException{
		try {
			String sql = "SELECT C.Categoryid, C.CategoryName, COUNT(L.Videoid) Views, SUM(L.Stoptime-L.Starttime) Duration FROM TBLLog L INNER JOIN TBLVideo V ON L.Videoid=V.Videoid INNER JOIN TBLCategoryVideo CV ON V.Videoid=CV.Videoid INNER JOIN TBLCategory C ON C.Categoryid=CV.Categoryid WHERE L.Userid=? GROUP BY C.Categoryid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet listUserInDepartmentAndUniversity(int departmentid, int universityid) throws SQLException{
		try {
			String sql = "SELECT U.Userid, U.Username, COUNT(L.Videoid) Views, SUM(L.Stoptime-L.Starttime) duration FROM TBLUser U INNER JOIN TBLLog L ON U.Userid=L.Userid WHERE U.Departmentid=? AND U.Universityid=? GROUP BY U.Userid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, departmentid);
			ps.setInt(2, universityid);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}

	public ResultSet listDeparmentByUniversity(int universityid) throws SQLException{
		try {
			String sql = "SELECT T1.*, Views, Duration FROM (SELECT D.Departmentid, D.Departmentname, Count(U.Userid) Users FROM TBLDepartment D LEFT JOIN TBLUser U ON D.Departmentid=U.Departmentid AND U.Universityid=? GROUP BY D.Departmentid) T1 LEFT JOIN (SELECT U2.DepartmentID, COUNT(L.Logid) Views, SUM(L.Stoptime-L.Starttime) Duration FROM TBLLog L INNER JOIN TBLUser U2 ON L.Userid=U2.Userid WHERE U2.Departmentid IN (SELECT Departmentid FROM TBLDepartment) AND U2.Universityid=? GROUP BY U2.Departmentid) T2 ON T1.Departmentid=T2.Departmentid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, universityid);
			ps.setInt(2, universityid);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	public ResultSet listUniversity() throws SQLException{
		try {
			String sql = "SELECT T1.*, Views, Duration FROM (SELECT Un.Universityid, Un.Universityname, Count(U.Userid) Users FROM TBLUniversity Un LEFT JOIN TBLUser U ON Un.Universityid=U.Universityid GROUP BY Un.Universityid) T1 LEFT JOIN (SELECT U2.Universityid, COUNT(L.Logid) Views, SUM(L.Stoptime-L.Starttime) Duration FROM TBLLog L INNER JOIN TBLUser U2 ON L.Userid=U2.Userid WHERE U2.Universityid IN (SELECT Universityid FROM TBLUniversity) GROUP BY U2.Universityid) T2 ON T1.Universityid=T2.Universityid";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	
	
	
	
}
