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
	static PreparedStatement ps=null;
	
public static List<MessageBean> getUserMessages(String usernameRequest) throws SQLException {
        
		List<MessageBean> result = new ArrayList<MessageBean>();

        String searchQuery = "Select * From Messages INNER JOIN Mes_Receivers ON Messages.mID=Mes_Receivers.mID Where Username=? ORDER BY date_sent";
        
        try {
            ConnectionManager connect = new ConnectionManager();
            currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			rs = ps.executeQuery();
			
            while (rs.next()) {
            	MessageBean m = new MessageBean();
            	m.setmID(rs.getString("mID"));
            	m.setReceiver((String[])rs.getArray("receivers").getArray());
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
public static boolean sendMessage(MessageBean mes)
{
	String insertQuery= "insert into messages(mid,title,date_sent,content,sent_by) values(?,?,?,?,?)";
	String insertQuery2= "insert into mes_receivers(mid,receivers) values(?,?)";
	try {
		ConnectionManager connect = new ConnectionManager();
		currentCon = connect.getConnection();
		ps=currentCon.prepareStatement(insertQuery);
		ps.setString(1,mes.getmID());
		ps.setString(2,mes.getTitle());
		ps.setDate(3,mes.getDate());
		ps.setString(4, mes.getContent());
		ps.setString(5,mes.getSender());
		ps.executeUpdate();
	}
	catch(Exception e){
		e.printStackTrace();
		return false;
	}
	try{
		ConnectionManager connect = new ConnectionManager();
		currentCon = connect.getConnection();
		ps=currentCon.prepareStatement(insertQuery2);
		ps.setString(1,mes.getmID());
		//ps.setArray(2,mes.getReceiver());
		ps.executeUpdate();
	}
	catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return true;
	
}
}

