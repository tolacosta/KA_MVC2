package model.dto;

import java.util.Date;

public class Comment {
	private int commentid;
	private Date commentdate;
	private String commenttext;
	private int videoid;
	private int userid;
	private String videoname;
	private String username;
	private String userimageurl;
	private int recommid;
	


	public Comment(){
		super();
	}
	
	public Comment(int commentid, Date commentdate, String commenttext,
			int videoid, int userid) {
		super();
		this.commentid = commentid;
		this.commentdate = commentdate;
		this.commenttext = commenttext;
		this.videoid = videoid;
		this.userid = userid;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public Date getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}

	public String getCommenttext() {
		return commenttext;
	}

	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}

	public int getVideoid() {
		return videoid;
	}

	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserimageurl() {
		return userimageurl;
	}

	public void setUserimageurl(String userimageurl) {
		this.userimageurl = userimageurl;
	}

	public int getRecommid() {
		return recommid;
	}

	public void setRecommid(int recommid) {
		this.recommid = recommid;
	}
	
	
}
