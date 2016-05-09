package model.dto;

import java.util.Date;

public class PreCourse {

	private int id;
	private Date dateCreate;
	private int userId;;
	private String username;
	private String email;
	private String telephone;
	private String university;
	private String pob;
	private Date dob;
	private String javaCourse;
	private String comment;
	private String userImage;
	private String webCourse;
	private int payment;
	private String gender;
	private String year;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public PreCourse(){}
	public PreCourse(int id,
					Date dateCreate,
					int userId,
					String username,
					String email,
					String telephone,
					String university,
					String pob,
					Date dob,
					String javaCourse,
					String comment,
					String userImage,
					String webCourse,
					int payment,
					String gender) {
		id = this.id;
		dateCreate = this.dateCreate;
		userId = this.userId;
		username = this.username;
		email = this.email;
		telephone = this.telephone;
		university = this.university;
		pob = this.pob;
		dob = this.dob;
		javaCourse = this.javaCourse;
		comment = this.comment;
		userImage = this.userImage;
		webCourse = this.webCourse;
		payment = this.payment;
		gender = this.gender;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getPob() {
		return pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getJavaCourse() {
		return javaCourse;
	}
	public void setJavaCourse(String javaCourse) {
		this.javaCourse = javaCourse;
	}
	public String getWebCourse() {
		return webCourse;
	}
	public void setWebCourse(String webCourse) {
		this.webCourse = webCourse;
	}
	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
			this.payment = payment;
	}
}
