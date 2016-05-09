package model.dto;

import java.util.Date;

public class ForumComment {
	private int commentid;
	private Date postdate;
	private String title;
	private String detail;
	private String tag;
	private int parentid;
	private Integer categoryid;
	private int userid;
	private boolean selected;
	private String categoryname;
	private String username;
	private String userimageurl;
	private int commentcount;
	private int vote;

	public ForumComment() {
		super();
	}

	public ForumComment(int commentid, Date postdate, String title,
			String detail, String tag, int parentid, int categoryid,
			int userid, boolean selected) {
		this.commentid = commentid;
		this.postdate = postdate;
		this.title = title;
		this.detail = detail;
		this.tag = tag;
		this.parentid = parentid;
		this.categoryid = categoryid;
		this.userid = userid;
		this.selected = selected;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
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

	public int getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}

}
