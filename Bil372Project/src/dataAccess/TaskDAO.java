package dataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.ProjectBean;
import models.TaskBean;
import models.UserBean;

public class TaskDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public static List<TaskBean> searchTasksForUser(String username) throws SQLException {
		List<TaskBean> tasks = new ArrayList<TaskBean>();
		String searchQuery = "Select * From TASK Where Username=? ORDER BY deadline";// TODO: rewrite sql
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				TaskBean task = new TaskBean();
				// task.setUsername(username);
				task.setTid(rs.getInt("TID"));
				task.setPid(rs.getInt("PID"));
				task.setDescription(rs.getString("description"));
				task.setPerformanceCriteria(rs.getString("performancecriteria"));
				task.setDeadline(rs.getDate("deadline"));
				// task.setPrerequisite(rs.getInt("PRETID"));
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return tasks;
		}
		return tasks;

	}

	public static ArrayList<TaskBean> getRootTasks(ProjectBean project) {
		PreparedStatement ps = null;
		String searchQuery = "select * from TASK as t where t.pid=? and NOT EXISTS(select * from prerequ_task as p where t.tid=p.tid)";
		ArrayList<TaskBean> result = new ArrayList<TaskBean>();
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			ps.setInt(1, project.getPid());
			rs = ps.executeQuery();
			while (rs.next()) {
				TaskBean task = new TaskBean();
				int tid = rs.getInt("taskid");
				String title = rs.getString("title");
				int Pid = rs.getInt("PID");
				String Description = rs.getString("description");
				String PerformanceCriteria = rs.getString("performanceCriteria");
				Date Deadline = rs.getDate("deadline");
				int performanceUpperbound = rs.getInt("performanceUpperbound");
				int perfromanceValue = rs.getInt("performanceValue");

				task.setTid(tid);
				task.setTitle(title);
				task.setPid(Pid);
				task.setDeadline(Deadline);
				task.setDescription(Description);
				task.setPerformanceCriteria(PerformanceCriteria);
				task.setPerformanceUpperbound(performanceUpperbound);
				task.setPerfromanceValue(perfromanceValue);

				result.add(task);
			}
		} catch (Exception ex) {
			System.out.println("Failed: An Exception has occurred! " + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return result;
	}

	public static ArrayList<TaskBean> getChildTasks(TaskBean parentTask) {
		PreparedStatement ps = null;
		String searchQuery = "select * from TASK where tid in (select tid from PREREQU_TASK where pretid=1)";
		ArrayList<TaskBean> result = new ArrayList<TaskBean>();
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			ps.setInt(1, parentTask.getTid());
			rs = ps.executeQuery();
			while (rs.next()) {
				TaskBean task = new TaskBean();
				int tid = rs.getInt("taskid");
				String title = rs.getString("title");
				int Pid = rs.getInt("PID");
				String Description = rs.getString("description");
				String PerformanceCriteria = rs.getString("performanceCriteria");
				Date Deadline = rs.getDate("deadline");
				int performanceUpperbound = rs.getInt("performanceUpperbound");
				int perfromanceValue = rs.getInt("performanceValue");

				task.setTid(tid);
				task.setTitle(title);
				task.setPid(Pid);
				task.setDeadline(Deadline);
				task.setDescription(Description);
				task.setPerformanceCriteria(PerformanceCriteria);
				task.setPerformanceUpperbound(performanceUpperbound);
				task.setPerfromanceValue(perfromanceValue);

				result.add(task);
			}
		} catch (Exception ex) {
			System.out.println("Failed: An Exception has occurred! " + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return result;
	}

	public static ArrayList<UserBean> getEmployeesWorksOn(TaskBean task) {
		PreparedStatement ps = null;
		String searchQuery = "select * from WORK_TASK_EMP w, EMPLOYEE e where w.tid=? AND w.username=e.username";
		ArrayList<UserBean> result = new ArrayList<UserBean>();
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			ps.setInt(1, task.getTid());
			rs = ps.executeQuery();
			while (rs.next()) {
				UserBean employee = new UserBean();

				String jobTitle = rs.getString("JobTitle");
				String image = rs.getString("Image");
				String firstName = rs.getString("FirstName");
				String middleName = rs.getString("MiddleName");
				String lastName = rs.getString("LastName");
				String email = rs.getString("Email");
				employee.setJobTitle(jobTitle);
				employee.setImage(image);
				employee.setFirstName(firstName);
				employee.setMiddleName(middleName);
				employee.setLastName(lastName);
				employee.setEmail(email);

				employee.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Failed: An Exception has occurred! " + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return result;
	}
}
