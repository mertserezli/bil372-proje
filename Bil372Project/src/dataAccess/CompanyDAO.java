package dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.CompanyBean;

public class CompanyDAO extends DAO {
	static Connection currentConnection = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect = null;

	public static CompanyBean getCompany(CompanyBean company) {
		String name = company.getName();
		String query = "select * from company where name='" + name + "'";
		try {
			connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(query);
			rs = ps.executeQuery();
			boolean more = rs.next();
			if (more) {
				company.setCompID(rs.getString("CompID"));
				company.setName(name);
				company.setDescription(rs.getString("Description"));
				company.setAdministratorUserName((String[]) rs.getArray("AdministratorUserName").getArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return company;
	}
}
