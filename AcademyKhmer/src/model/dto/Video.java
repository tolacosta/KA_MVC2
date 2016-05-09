package model.dto;

import java.util.Date;

public class Video {
	private int videoid;
	private String videoname;
	private String description;
	private String youtubeurl;
	private String fileurl;
	private boolean publicview;
	private Date postdate;
	private int userid;
	private String username;
	private String categorynames;	//e.g. "Java, Jsp, J2EE, " it's multiple concated string
	private int countvoteplus;
	private int countvoteminus;
	private int countcomments;
	private int viewcounts;
	private String userimageurl;
	
	private int countalluser;
	private int countallplaylist;
	private int countallquestion;
	private int countallvideo;
	
	public int getCountalluser() {
		return countalluser;
	}

	public void setCountalluser(int countalluser) {
		this.countalluser = countalluser;
	}

	public int getCountallplaylist() {
		return countallplaylist;
	}

	public void setCountallplaylist(int countallplaylist) {
		this.countallplaylist = countallplaylist;
	}

	public int getCountallquestion() {
		return countallquestion;
	}

	public void setCountallquestion(int countallquestion) {
		this.countallquestion = countallquestion;
	}

	public int getCountallvideo() {
		return countallvideo;
	}

	public void setCountallvideo(int countallvideo) {
		this.countallvideo = countallvideo;
	}

	public Video(){
		super();
	}
	
	public Video(int videoid, String videoname, String description,
			String youtubeurl, String fileurl, boolean publicview,
			Date postdate, int userid, int viewcount) {
		super();
		this.videoid = videoid;
		this.videoname = videoname;
		this.description = description;
		this.youtubeurl = youtubeurl;
		this.fileurl = fileurl;
		this.publicview = publicview;
		this.postdate = postdate;
		this.userid = userid;
		this.viewcounts = viewcount;
	}

	public int getVideoid() {
		return videoid;
	}

	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYoutubeurl() {
		return youtubeurl;
	}

	public void setYoutubeurl(String youtubeurl) {
		this.youtubeurl = youtubeurl;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public boolean isPublicview() {
		return publicview;
	}

	public void setPublicview(boolean publicview) {
		this.publicview = publicview;
	}

	public java.util.Date getPostdate() {
		return postdate;
	}

	public void setPostdate(java.util.Date postdate) {
		this.postdate = postdate;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCategorynames() {
		return categorynames;
	}

	public void setCategorynames(String categorynames) {
		this.categorynames = categorynames;
	}

	public int getCountvoteplus() {
		return countvoteplus;
	}

	public void setCountvoteplus(int countvoteplus) {
		this.countvoteplus = countvoteplus;
	}

	public int getCountvoteminus() {
		return countvoteminus;
	}

	public void setCountvoteminus(int countvoteminus) {
		this.countvoteminus = countvoteminus;
	}

	public int getCountcomments() {
		return countcomments;
	}

	public void setCountcomments(int countcomments) {
		this.countcomments = countcomments;
	}

	public int getViewcounts() {
		return viewcounts;
	}

	public void setViewcounts(int viewcounts) {
		this.viewcounts = viewcounts;
	}

	public String getUserimageurl() {
		return userimageurl;
	}

	public void setUserimageurl(String userimageurl) {
		this.userimageurl = userimageurl;
	}
	
	
	
}
