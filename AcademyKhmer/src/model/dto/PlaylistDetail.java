package model.dto;

public class PlaylistDetail {
	private int playlistid;
	private int videoid;
	private String playlistname; 
	private String videoname;
	
	public PlaylistDetail(){
		super();
	}
	
	public PlaylistDetail(int playlistid, int videoid) {
		super();
		this.playlistid = playlistid;
		this.videoid = videoid;
	}

	public int getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(int playlistid) {
		this.playlistid = playlistid;
	}

	public int getVideoid() {
		return videoid;
	}

	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}

	public String getPlaylistname() {
		return playlistname;
	}

	public void setPlaylistname(String playlistname) {
		this.playlistname = playlistname;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}
	
}
