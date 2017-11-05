package models;

import java.sql.Date;

public class TaskBean {
	private int Tid;
	private int Pid;
	private String Title;
	private String Description;
	private String Username;
	private String PerformanceCriteria;
	private TaskBean Prerequisite;
	private Date Deadline;
	
	public int getTid() {
		return Tid;
	}
	public void setTid(int tid) {
		Tid = tid;
	}
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
	public String getPerformanceCriteria() {
		return PerformanceCriteria;
	}
	public void setPerformanceCriteria(String performanceCriteria) {
		PerformanceCriteria = performanceCriteria;
	}
	public TaskBean getPrerequisite() {
		return Prerequisite;
	}
	public void setPrerequisite(TaskBean prerequisite) {
		Prerequisite = prerequisite;
	}
	public Date getDeadline() {
		return Deadline;
	}
	public void setDeadline(Date deadline) {
		Deadline = deadline;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	


}
