package models;

import java.sql.Date;

public class ProjectBean {
	private int Pid;
	private String Title;
	private String Description;
	private Date CreationDate;
	private int Votenum;
	private String State;
	private String[] Tags;
	private Date[] meeting_dates;
	
	public int getPid() {
		return Pid;
	}
	
	public void setPid(int pid) {
		Pid = pid;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String title) {
		Title = title;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
	public Date getCreationDate() {
		return CreationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	
	public int getVotenum() {
		return Votenum;
	}
	
	public void setVotenum(int votenum) {
		Votenum = votenum;
	}
	
	public String getState() {
		return State;
	}
	
	public void setState(String state) {
		State = state;
	}

	public String[] getTags() {
		return Tags;
	}

	public void setTags(String[] tags) {
		Tags = tags;
	}

	public Date[] getMeeting_dates() {
		return meeting_dates;
	}

	public void setMeeting_dates(Date[] meeting_dates) {
		this.meeting_dates = meeting_dates;
	}
	
}