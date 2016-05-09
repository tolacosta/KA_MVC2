package model.dto;

public class Maincategory {
	private int maincategoryid;
	private String maincategoryname;
	private String maincategorylogourl;
	private int maincategoryorder;
	private int countcategory;

	public Maincategory(){
		super();
	}
	
	public Maincategory(int maincategoryid, String maincategoryname, String maincategorylogourl) {
		super();
		this.maincategoryid = maincategoryid;
		this.maincategoryname = maincategoryname;
		this.maincategorylogourl = maincategorylogourl;
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

	public String getMaincategorylogourl() {
		return maincategorylogourl;
	}

	public void setMaincategorylogourl(String maincategorylogourl) {
		this.maincategorylogourl = maincategorylogourl;
	}

	public int getMaincategoryorder() {
		return maincategoryorder;
	}

	public void setMaincategoryorder(int maincategoryorder) {
		this.maincategoryorder = maincategoryorder;
	}

	public int getCountcategory() {
		return countcategory;
	}

	public void setCountcategory(int countcategory) {
		this.countcategory = countcategory;
	}
	
	

	
	
	
}
