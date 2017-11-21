package dataAccess;

import models.MessageBean;
import models.UserBean;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.UserBean;

public class MessageDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public static List<MessageBean> getUserMessages(String usernameRequest) throws SQLException {

		List<MessageBean> result = new ArrayList<MessageBean>();
		String searchQuery = "Select * From Messages INNER JOIN Mes_Receivers ON Messages.mid=Mes_Receivers.mid Where receivers @> ARRAY['"
				+ usernameRequest + "']::text[]";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				MessageBean m = new MessageBean();
				m.setmID(rs.getString("mID"));
				m.setReceiver((String[]) rs.getArray("receivers").getArray());
				m.setDate(rs.getDate("date_sent"));
				m.setSender(rs.getString("sent_by"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				result.add(m);
			}
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

	public static List<MessageBean> getUserOutbox(String usernameRequest) throws SQLException {

		List<MessageBean> result = new ArrayList<MessageBean>();
		String searchQuery = "Select * From Messages INNER JOIN Mes_Receivers ON Messages.mid=Mes_Receivers.mid Where sent_by='"
				+ usernameRequest + "'";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			rs = ps.executeQuery();

			while (rs.next()) {
				MessageBean m = new MessageBean();
				m.setmID(rs.getString("mid"));
				m.setReceiver((String[]) rs.getArray("receivers").getArray());
				m.setDate(rs.getDate("date_sent"));
				m.setSender(rs.getString("sent_by"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				result.add(m);
			}
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

	public static boolean sendMessage(MessageBean mes) {
		String insertQuery = "insert into messages(sent_by,title,content,date_sent) values(?,?,?,?)";
		String insertQuery2 = "insert into mes_receivers(receivers) values('{";

		for (int i = 0; i < mes.getReceiver().length; i++) {
			if (i == mes.getReceiver().length - 1) {
				insertQuery2 += mes.getReceiver()[i] + "}')";
			} else {
				insertQuery2 += mes.getReceiver()[i] + ",";
			}
		}

		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(insertQuery);
			// ps.setString(1,mes.getmID());
			ps.setString(1, mes.getSender());
			ps.setString(2, mes.getTitle());
			ps.setString(3, mes.getContent());
			ps.setDate(4, mes.getDate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(insertQuery2);
			;
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
