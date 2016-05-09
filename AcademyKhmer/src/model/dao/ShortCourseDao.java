package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.naming.NamingException;

import model.dto.Playlist;
import model.dto.ShortCourse;
import ultilities.ClsPagination;

public class ShortCourseDao {

	private Connection cnn = null;
	
	public ShortCourseDao() throws ClassNotFoundException, NamingException, SQLException {
		cnn = new DBUtility().getConnect();
	}
	
	public boolean registerShortCourse(ShortCourse course) throws SQLException{
		String sql = "INSERT INTO shortcourse.tblstudent (fullname,email,phonenumber,university,year,dob,gender,description,address,ka_userid) VALUES(?,?,?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement ps = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, course.getFullname());
			ps.setString(2,course.getEmail());
			ps.setString(3, course.getPhonenumber());
			ps.setString(4, course.getUniversity());
			ps.setString(5, course.getYear());
			ps.setDate(6, (Date)course.getDob());
			ps.setString(7, course.getGender());
			ps.setString(8, course.getDescirption());
			ps.setString(9, course.getAddress());
			ps.setInt(10, course.getUserid());
			if(ps.executeUpdate() > 0){
				ResultSet rs= ps.getGeneratedKeys();
				if (rs.next()) {
					int studentid = rs.getInt(1);
					System.out.println(studentid);
	                String sql1 = "INSERT INTO shortcourse.tblstudent_detail(student_id,course_id,shift_id,type_id,ka_userid,register_date,ispaid,pay) VALUES(?,?,?,?,?,NOW(),false,0);";
					PreparedStatement ps1 = cnn.prepareStatement(sql1);
					ps1.setInt(1, studentid);
					ps1.setInt(2, course.getCourse());
					ps1.setInt(3, course.getShift());
					ps1.setInt(4, course.getType());
					ps1.setInt(5, course.getUserid());
					if(ps1.executeUpdate() > 0){
						return true;
					}
	            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cnn.close();
		}
		return false;
	}
	
	public boolean registerSC(ShortCourse course) throws SQLException{
	     String sql = "INSERT INTO shortcourse.tblstudent_detail(student_id,course_id,shift_id,type_id,ka_userid,register_date,ispaid,pay) VALUES(?,?,?,?,?,NOW(),false,0);";
		try{			
	     	PreparedStatement ps1 = cnn.prepareStatement(sql);
			ps1.setInt(1, course.getId());
			ps1.setInt(2, course.getCourse());
			ps1.setInt(3, course.getShift());
			ps1.setInt(4, course.getType());
			ps1.setInt(5, course.getUserid());
			if(ps1.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cnn.close();
		}
		return false;
	}
	
	public boolean updateShortCourse(ShortCourse course) throws SQLException{
//		String sql = "UPDATE shortcourse.tblstudent SET fullname=? , email=?, phonenumber=?,university=?,year=?,dob=?,gender=?,description=?,address=? WHERE id=?;";
		String sql1= "UPDATE shortcourse.tblstudent_detail SET course_id=?,shift_id=?,update_date=NOW() WHERE id=?";
		try {
//			PreparedStatement ps = cnn.prepareStatement(sql);
//			ps.setString(1, course.getFullname());
//			ps.setString(2,course.getEmail());
//			ps.setString(3, course.getPhonenumber());
//			ps.setString(4, course.getUniversity());
//			ps.setString(5, course.getYear());
//			ps.setDate(6, (Date)course.getDob());
//			ps.setString(7, course.getGender());
//			ps.setString(8, course.getDescirption());
//			ps.setString(9, course.getAddress());
//			ps.setInt(10, course.getId());
//			if(ps.executeUpdate() > 0){
				PreparedStatement ps1 = cnn.prepareStatement(sql1);
				ps1.setInt(1, course.getCourse());
				ps1.setInt(2, course.getShift());
				ps1.setInt(3, course.getId());
				if(ps1.executeUpdate() > 0){
					return true;
				}
//	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cnn.close();
		}
		return false;
	}
	
	
	
	public ShortCourse getStudent(int userid) throws ClassNotFoundException, NamingException{
		String sql = "SELECT SD.* , S.*FROM shortcourse.tblstudent_detail SD LEFT JOIN shortcourse.tblstudent S ON S.id = SD.student_id WHERE  SD.ka_userid=?;";
		try {
			cnn = new DBUtility().getConnect();
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ShortCourse dto = new ShortCourse();
				dto.setFullname(rs.getString("fullname"));
				dto.setEmail(rs.getString("email"));
				dto.setPhonenumber(rs.getString("phonenumber"));
				dto.setUniversity(rs.getString("university"));
				dto.setYear(rs.getString("year"));
				dto.setDob(rs.getDate("dob"));
				dto.setGender(rs.getString("gender"));
				dto.setDescirption(rs.getString("description"));
				dto.setAddress(rs.getString("address"));
				dto.setCourse(rs.getInt("course_id"));
				dto.setType(rs.getInt("type_id"));
				dto.setShift(rs.getInt("shift_id"));
				dto.setId(rs.getInt("id"));
				dto.setUserid(rs.getInt("ka_userid"));
				dto.setPaid(rs.getBoolean("ispaid"));
				dto.setPay(rs.getLong("pay"));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ShortCourse getRegisterdCoures(int courseId) throws ClassNotFoundException, NamingException{
		String sql = "SELECT id,shift_id,course_id,type_id,student_id,ispaid FROM shortcourse.tblstudent_detail WHERE  id=?;";
		try {
			cnn = new DBUtility().getConnect();
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ShortCourse dto = new ShortCourse();
				dto.setCourse(rs.getInt("course_id"));
				dto.setType(rs.getInt("type_id"));
				dto.setShift(rs.getInt("shift_id"));
				dto.setId(rs.getInt("id"));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean isUserRegisterdSC(int userId){
		String sql = "SELECT id FROM shortcourse.tblstudent_detail WHERE ka_userid=?";
		try{
			cnn = new DBUtility().getConnect();
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean isCourseDuplicateUpdate(int courseId , int userId){
		String sql = "SELECT id FROM shortcourse.tblstudent_detail WHERE course_id=? AND  ka_userid=? AND status=true";
		try{
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, courseId);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean isCourseDuplicate(int courseId , int userId ){
		String sql = "SELECT id FROM shortcourse.tblstudent_detail WHERE course_id=? AND  ka_userid = ?  AND status=true ";
		try{
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, courseId);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean isShiftDuplicate(int userId , int shiftId){
		String sql = "SELECT id FROM shortcourse.tblstudent_detail WHERE   ka_userid = ? AND shift_id=? AND status=true ";
		try{
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, shiftId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
		
	public ResultSet listApplicantCourses(int studentId) throws ClassNotFoundException, NamingException, SQLException {
		try {
			cnn = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "SELECT"
					+ " SD.id, SD.student_id,  SD.ispaid, SD.register_date, SD.update_date  ,"
					+ " SC.course, SS.shift, ST.type, SD.ka_userid"
					+ " FROM shortcourse.tblstudent_detail SD"
					+ " LEFT JOIN shortcourse.tblstudent S ON S.id = SD.student_id"
					+ " LEFT JOIN shortcourse.tblcourse SC ON SD.course_id = SC.id"
					+ " LEFT JOIN shortcourse.tblshift SS ON SD.shift_id = SS.id"
					+ " LEFT JOIN shortcourse.tbltype ST ON SD.type_id = ST.id"
					+ " WHERE  SD.ka_userid=? AND SD.status = true;";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cnn.close();
		}
		return null;
	}
	
	public ResultSet listApplicantCourses() throws ClassNotFoundException, NamingException, SQLException {
		try {
			cnn = new DBUtility().getConnect();
			ResultSet rs = null;
			String sql = "SELECT"
					+ " SD.id, SD.student_id,  SD.ispaid, SD.register_date, SD.update_date,S.gender, S.fullname, S.email, S.phonenumber ,"
					+ " SC.course, SS.shift, ST.type, SD.ka_userid"
					+ " FROM shortcourse.tblstudent_detail SD"
					+ " LEFT JOIN shortcourse.tblstudent S ON S.id = SD.student_id"
					+ " LEFT JOIN shortcourse.tblcourse SC ON SD.course_id = SC.id"
					+ " LEFT JOIN shortcourse.tblshift SS ON SD.shift_id = SS.id"
					+ " LEFT JOIN shortcourse.tbltype ST ON SD.type_id = ST.id"
					+ " WHERE SD.status = true;";
			PreparedStatement ps = cnn.prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cnn.close();
		}
		return null;
	}
	
	public boolean deleteRegisteredCourse(int courseId) throws SQLException{
		String sql1= "UPDATE shortcourse.tblstudent_detail SET status=false  WHERE id=?";
		try {
				PreparedStatement ps1 = cnn.prepareStatement(sql1);
				ps1.setInt(1, courseId);
				if(ps1.executeUpdate() > 0){
					return true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cnn.close();
		}
		return false;
	}
	
	public int getStudentId(int userId) throws ClassNotFoundException, NamingException{
		String sql = "SELECT id FROM shortcourse.tblstudent WHERE ka_userid=?";
		try {
			cnn = new DBUtility().getConnect();
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException, ParseException {
		
		   System.out.println(new ShortCourseDao().isUserRegisterdSC(104));
		
//			ShortCourse c = new ShortCourse();
//			c.setFullname("dara");
//			c.setEmail("tola@gmail.com");
//			c.setPhonenumber("123");
//			c.setUniversity("rupp");
//			c.setYear("1");
			
//			DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
//			Date date =  format.parse("2016-03-22");
//			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//			c.setDob(sqlDate);
			
//			c.setDob("2016-03-22");
//			c.setGender("M");
//			c.setDescirption("test");
//			c.setAddress("PP");
//			c.setCourse(1);
//			c.setType(1);
//			c.setShift(1);
//			
//			new ShortCourseDao().registerShortCourse(c);
			
			
			
	}

}
