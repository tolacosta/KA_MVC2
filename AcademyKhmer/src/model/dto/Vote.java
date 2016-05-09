package model.dto;

public class Vote {
	private int userid;
	private int videoid;
	private String username;
	private String videoname;
	
	public Vote(){
		super();
	}
	
	public Vote(int userid, int videoid) {
		super();
		this.userid = userid;
		this.videoid = videoid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getVideoid() {
		return videoid;
	}

	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}
	
}
