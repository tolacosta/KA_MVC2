package model.dto;

public class Playlist {
	private int playlistid;
	private String playlistname;
	private String description;
	private int userid;
	private String thumbnailurl;
	private boolean publicview;
	private String username;
	private int countvideos;
	
	public Playlist(){
		super();
	}
	
	public Playlist(int playlistid, String playlistname, String description, int userid, String thumbnailurl,
			boolean publicview) {
		super();
		this.playlistid = playlistid;
		this.playlistname = playlistname;
		this.description = description;
		this.userid = userid;
		this.thumbnailurl = thumbnailurl;
		this.publicview = publicview;
	}

	public int getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(int playlistid) {
		this.playlistid = playlistid;
	}

	public int getUserid() {
		return userid;
	}

	public String getPlaylistname() {
		return playlistname;
	}

	public void setPlaylistname(String playlistname) {
		this.playlistname = playlistname;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getThumbnailurl() {
		return thumbnailurl;
	}

	public void setThumbnailurl(String thumbnailurl) {
		this.thumbnailurl = thumbnailurl;
	}

	public boolean isPublicview() {
		return publicview;
	}

	public void setPublicview(boolean publicview) {
		this.publicview = publicview;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCountvideos() {
		return countvideos;
	}

	public void setCountvideos(int countvideos) {
		this.countvideos = countvideos;
	}
	
	
}
