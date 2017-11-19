package models;

import java.sql.Date;

public class MessageBean {
	private String title;
	private String mID;
	private Date date;
	private String sender;
	public String[] receivers;
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String[] getReceiver() {
		return receivers;
	}
	public void setReceiver(String[] receiver) {
		this.receivers = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
