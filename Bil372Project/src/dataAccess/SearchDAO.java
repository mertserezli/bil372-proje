package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserBean;
import models.CompanyBean;
import models.Emp_QualificationsBean;
import models.ProjectBean;

public class SearchDAO extends DAO {

	static Connection currentConnection = null;
	static ResultSet rs = null;

	public static List<UserBean> searchForUser(String usernameRequest) throws SQLException {

		List<UserBean> result = new ArrayList<UserBean>();
		PreparedStatement ps = null;

		String searchQuery = "Select DISTINCT * From EMPLOYEE Where UPPER(Username) LIKE UPPER('%" + usernameRequest
				+ "%')";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserBean user = new UserBean();
				user.setUserName(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setJobTitle(rs.getString("JobTitle"));
				user.setImage(rs.getString("Image"));
				user.setFirstName(rs.getString("FirstName"));
				user.setMiddleName(rs.getString("MiddleName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmail(rs.getString("Email"));
				user.setValid(true);
				result.add(user);
			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}

	public static List<UserBean> searchForUserJobTitle(String jobtitleRequest) throws SQLException {

		List<UserBean> result = new ArrayList<UserBean>();
		PreparedStatement ps = null;

		String searchQuery = "Select DISTINCT * From EMPLOYEE Where UPPER(Jobtitle) LIKE UPPER('%" + jobtitleRequest
				+ "%')";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserBean user = new UserBean();
				user.setUserName(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setJobTitle(rs.getString("JobTitle"));
				user.setImage(rs.getString("Image"));
				user.setFirstName(rs.getString("FirstName"));
				user.setMiddleName(rs.getString("MiddleName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmail(rs.getString("Email"));
				user.setValid(true);
				result.add(user);
			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}

	public static List<UserBean> searchForUserWithQualifications(String qualificationsRequest) throws SQLException {

		List<String> employees = new ArrayList<String>();
		List<UserBean> result = new ArrayList<UserBean>();
		PreparedStatement ps = null;

		String searchQueryOne = "Select DISTINCT Username From EMP_QUALIFICATIONS Where ";
		String[] qRequests = qualificationsRequest.split(",");
		for (String s : qRequests) {
			searchQueryOne += "'" + s + "'=ANY(qualifications)";
			if (!s.equals(qRequests[qRequests.length - 1]))
				searchQueryOne += " AND ";
		}

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQueryOne);
			rs = ps.executeQuery();

			while (rs.next()) {
				String employee = rs.getString("Username");
				employees.add(employee);
			}

			String searchQueryTwo;
			for (String e : employees) {
				searchQueryTwo = "Select * From EMPLOYEE Where Username = '" + e + "'";

				ps = currentConnection.prepareStatement(searchQueryTwo);
				rs = ps.executeQuery();

				while (rs.next()) {
					UserBean user = new UserBean();
					user.setUserName(rs.getString("Username"));
					user.setPassword(rs.getString("Password"));
					user.setJobTitle(rs.getString("JobTitle"));
					user.setImage(rs.getString("Image"));
					user.setFirstName(rs.getString("FirstName"));
					user.setMiddleName(rs.getString("MiddleName"));
					user.setLastName(rs.getString("LastName"));
					user.setEmail(rs.getString("Email"));
					user.setValid(true);
					result.add(user);
				}
			}

			while (rs.next()) {

			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}

	public static List<ProjectBean> searchForProject(String projectRequest) throws SQLException {

		List<ProjectBean> result = new ArrayList<ProjectBean>();
		PreparedStatement ps = null;
		String searchQuery = "Select DISTINCT * From PROJECT Where UPPER(Title) LIKE UPPER('%" + projectRequest + "%')";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setTitle(rs.getString("Title"));
				project.setDescription(rs.getString("Description"));
				project.setPid(rs.getInt("Pid"));
				project.setState(rs.getString("State"));
				project.setVotenum(rs.getInt("Votenum"));
				project.setCreation_Date(rs.getDate("Creation_Date"));
				result.add(project);
			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}

	public static List<ProjectBean> searchForProjectDescription(String descriptionRequest) throws SQLException {

		List<ProjectBean> result = new ArrayList<ProjectBean>();
		PreparedStatement ps = null;

		String searchQuery = "Select DISTINCT * From PROJECT Where UPPER(Description) LIKE UPPER('%"
				+ descriptionRequest + "%')";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setTitle(rs.getString("Title"));
				project.setDescription(rs.getString("Description"));
				project.setPid(rs.getInt("Pid"));
				project.setState(rs.getString("State"));
				project.setVotenum(rs.getInt("Votenum"));
				project.setCreation_Date(rs.getDate("Creation_Date"));
				result.add(project);
			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}

	public static List<ProjectBean> searchForProjectWithTags(String tagsRequest) throws SQLException {

		List<ProjectBean> result = new ArrayList<ProjectBean>();
		PreparedStatement ps = null;

		String searchQuery = "Select DISTINCT * From Project Where ";
		String[] tRequests = tagsRequest.split(",");
		for (String s : tRequests) {
			searchQuery += "'" + s + "'=ANY(tags)";
			if (!s.equals(tRequests[tRequests.length - 1]))
				searchQuery += " AND ";
		}

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setTitle(rs.getString("Title"));
				project.setDescription(rs.getString("Description"));
				project.setPid(rs.getInt("Pid"));
				project.setState(rs.getString("State"));
				project.setVotenum(rs.getInt("Votenum"));
				project.setCreation_Date(rs.getDate("Creation_Date"));
				result.add(project);
			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}

	public static List<CompanyBean> searchForCompany(String companyRequest) throws SQLException {

		List<CompanyBean> result = new ArrayList<CompanyBean>();
		PreparedStatement ps = null;
		String searchQuery = "Select DISTINCT * From COMPANY Where UPPER(name) LIKE UPPER('%" + companyRequest + "%')";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				CompanyBean company = new CompanyBean();
				company.setCompID(rs.getString("CompId"));
				company.setName(rs.getString("Name"));
				company.setDescription(rs.getString("Description"));
				result.add(company);
			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}

	public static List<CompanyBean> searchForCompanyDescription(String descriptionRequest) throws SQLException {
		List<CompanyBean> result = new ArrayList<CompanyBean>();
		PreparedStatement ps = null;

		String searchQuery = "Select DISTINCT * From COMPANY Where UPPER(Description) LIKE UPPER('%"
				+ descriptionRequest + "%')";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				CompanyBean company = new CompanyBean();
				company.setCompID(rs.getString("CompId"));
				company.setName(rs.getString("Name"));
				company.setDescription(rs.getString("Description"));
				result.add(company);
			}
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return result;
	}
}
