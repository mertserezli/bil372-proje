package models;

import java.sql.Date;

public class TaskBean {
	private int Tid;
	private int Pid;
	private String Title;
	private String Description;
	private String PerformanceCriteria;
	private Date Deadline;
	private int performanceUpperbound;
	private int perfromanceValue;

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

	public Date getDeadline() {
		return Deadline;
	}

	public void setDeadline(Date deadline) {
		Deadline = deadline;
	}

	public int getPerformanceUpperbound() {
		return performanceUpperbound;
	}

	public void setPerformanceUpperbound(int performanceUpperbound) {
		this.performanceUpperbound = performanceUpperbound;
	}

	public int getPerfromanceValue() {
		return perfromanceValue;
	}

	public void setPerfromanceValue(int perfromanceValue) {
		this.perfromanceValue = perfromanceValue;
	}

}
