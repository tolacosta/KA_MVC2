package model.dto;

public class ForumVote {
	private int userid;
	private int commentid;
	private int votetype;
	
	public ForumVote() {
		super();
	}
	
	public ForumVote(int userid, int commentid, int votetype) {
		super();
		this.userid = userid;
		this.commentid = commentid;
		this.votetype = votetype;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getVotetype() {
		return votetype;
	}
	public void setVotetype(int votetype) {
		this.votetype = votetype;
	}
	
}
