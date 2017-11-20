package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.CommentBean;
import models.ProjectBean;
import models.UserBean;

public class CommentsDao {
	static Connection currentCon = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect = null;
	
	public static boolean addComment(ProjectBean project,UserBean user,CommentBean comment) {
		String query="insert into project_comments (username,content,pid) values (?,?,?)";
		try {
			connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, comment.getContent());
			ps.setInt(3, project.getPid());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		connect=null;
		currentCon=null;
		return true;
	}
}
