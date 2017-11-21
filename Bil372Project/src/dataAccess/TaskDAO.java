package dataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.MessageBean;
import models.ProjectBean;
import models.TaskBean;
import models.UserBean;

public class TaskDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public static List<TaskBean> searchTasksForUser(String usernameRequest) throws SQLException {
		List<TaskBean> tasks = new ArrayList<TaskBean>();
		String searchQuery = "Select * From work_task_emp INNER JOIN task ON work_task_emp.tid=task.tid Where username='"
				+ usernameRequest + "'";
		// String searchQuery = "Select * From TASK Where Username=? ORDER BY
		// deadline";// TODO: rewrite sql
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			// ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				TaskBean task = new TaskBean();
				// task.setUsername(username);
				task.setTid(rs.getInt("tid"));
				task.setPid(rs.getInt("pid"));
				task.setDescription(rs.getString("description"));
				task.setPerformanceCriteria(rs.getString("performancecriteria"));
				task.setPerformanceUpperbound(rs.getInt("performanceupperbound"));
				task.setPerfromanceValue(rs.getInt("performancevalue"));
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
				int tid = rs.getInt("tid");
				String title = rs.getString("title");
				String Description = rs.getString("description");

				task.setTid(tid);
				task.setTitle(title);
				task.setDescription(Description);

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
		String searchQuery = "select * from TASK where tid in (select tid from PREREQU_TASK where pretid=?)";
		ArrayList<TaskBean> result = new ArrayList<TaskBean>();
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			ps.setInt(1, parentTask.getTid());
			rs = ps.executeQuery();
			while (rs.next()) {
				TaskBean task = new TaskBean();
				int tid = rs.getInt("tid");
				String title = rs.getString("title");
				String Description = rs.getString("description");

				task.setTid(tid);
				task.setTitle(title);
				task.setDescription(Description);

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

				employee.setUserName(rs.getString("username"));
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setJobTitle(rs.getString("jobtitle"));
				employee.setLastName(rs.getString("lastname"));
				employee.setMiddleName(rs.getString("middlename"));

				result.add(employee);
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

	public static boolean addEmpToTask(String username, int tid) {
		String insertQuery = "INSERT INTO work_task_emp(username, tid) VALUES (?, ?);";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(insertQuery);
			ps.setString(1, username);
			ps.setInt(2, tid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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

		return true;
	}

	public static boolean addPreToTask(int ptid, int ctid) {
		String insertQuery = "INSERT INTO prerequ_task(tid, pretid) VALUES (?, ?);";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(insertQuery);
			ps.setInt(1, ctid);
			ps.setInt(2, ptid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
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

		return true;
	}

	public static boolean addTask(TaskBean task) {
		String insertQuery = "INSERT INTO public.task(deadline, description, performancecriteria, performanceupperbound, performancevalue, pid, tid, title)VALUES (?, ?, ?, ?, NULL, ?, DEFAULT, ?);";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(insertQuery);
			ps.setDate(1, task.getDeadline());
			ps.setString(2, task.getDescription());
			ps.setString(3, task.getPerformanceCriteria());
			ps.setInt(4, task.getPerformanceUpperbound());
			ps.setInt(5, task.getPid());
			ps.setString(6, task.getTitle());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
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

		return true;
	}

	public static boolean deleteTask(int tid) {
		String deleteTask = "DELETE FROM task WHERE tid=?;";
		String deletePrerequisite = "DELETE FROM prerequ_task WHERE pretid=?;";
		String deleteWorkOnTask = "DELETE FROM work_task_emp WHERE tid=?;";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(deleteTask);
			ps.setInt(1, tid);
			ps.executeUpdate();
			ps.close();
			ps = currentCon.prepareStatement(deletePrerequisite);
			ps.setInt(1, tid);
			ps.executeUpdate();
			ps.close();
			ps = currentCon.prepareStatement(deleteWorkOnTask);
			ps.setInt(1, tid);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
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

		return true;
	}

	public static boolean removeEmployee(int tid, String username) {
		String remove = "DELETE FROM work_task_emp WHERE username=? and tid=?;";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(remove);
			ps.setString(1, username);
			ps.setInt(2, tid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
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

		return true;
	}

}
