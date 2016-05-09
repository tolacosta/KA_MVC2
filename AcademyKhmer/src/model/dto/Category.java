package model.dto;

public class Category {
	private int categoryid;
	private String categoryname;
	private String categorylogourl;
	private int maincategoryid;
	private String maincategoryname;
	private int countvideos;

	public Category(){
		super();
	}
	
	public Category(int categoryid, String categoryname, String categorylogourl) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.categorylogourl = categorylogourl;
	}
	
	public int getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getCategoryname() {
		return categoryname;
	}
	
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	public String getCategorylogourl() {
		return categorylogourl;
	}
	
	public void setCategorylogourl(String categorylogourl) {
		this.categorylogourl = categorylogourl;
	}

	public int getMaincategoryid() {
		return maincategoryid;
	}

	public void setMaincategoryid(int maincategoryid) {
		this.maincategoryid = maincategoryid;
	}

	public String getMaincategoryname() {
		return maincategoryname;
	}

	public void setMaincategoryname(String maincategoryname) {
		this.maincategoryname = maincategoryname;
	}

	public int getCountvideos() {
		return countvideos;
	}

	public void setCountvideos(int countvideos) {
		this.countvideos = countvideos;
	}
	
	
	
}
