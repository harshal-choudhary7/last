package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import users.Users;
import utils.DBUtils;

public class OwnerDao 
{
	private Connection connection;
	private PreparedStatement updateProfileStatement;

	private PreparedStatement getId;
	private PreparedStatement updatePasswordStatement;
	private PreparedStatement seleteSubjectWiseResport;
	private PreparedStatement selectBookwiseReport;
	private PreparedStatement selectFeesResport;
	private PreparedStatement checkUserLogin;
	public OwnerDao()throws Exception
	{
		this.connection = DBUtils.getConnection();
		this.checkUserLogin = connection.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
		this.updateProfileStatement = connection.prepareStatement("UPDATE users SET email=?,phone=? WHERE id=?");
		this.updatePasswordStatement = connection.prepareStatement("UPDATE users SET password=? WHERE id=?");
		this.seleteSubjectWiseResport = connection.prepareStatement("SELECT * FROM BookTable");
		this.selectBookwiseReport = connection.prepareStatement("select books.subject, count(*) AS 'COUNT' from books, copies where books.id=copies.bookid group by books.subject;");
		this.selectFeesResport = connection.prepareStatement("SELECT * FROM BookTable");
		this.getId=connection.prepareStatement("SELECT id FROM users WHERE email=?");
	}
	public int getId(String email) throws Exception
	{
		this.getId.setString(1, email);
		ResultSet rs =  this.getId.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	public int checkCredentials(String email,String password) throws Exception
	{
		this.checkUserLogin.setString(1, email);
		this.checkUserLogin.setString(2, password);
		
			List<Users> userList = new ArrayList<Users>();
			try( ResultSet rs = this.checkUserLogin.executeQuery())
			{
				while( rs.next())
				{
					Users user = new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
					if(user.getRole().equals("owner"))
					{
						return 0;
					}
					if(user.getRole().equals("librarian"))
						return 1;
					if(user.getRole().equals("member"))
						return 2;
				}
			}
			return 4;
	}
	private int sendId(int int1) 
	{
		return int1;
	}
	public int updateProfile(String email, String phone,int id)throws Exception
	{
		this.updateProfileStatement.setString(1, email);
		this.updateProfileStatement.setString(2, phone);
		this.updateProfileStatement.setInt(3, id);
		return this.updateProfileStatement.executeUpdate();
	}
	public int updatePasswordStatement(String password,int id)throws Exception
	{
		this.updatePasswordStatement.setString(1, password);
		this.updatePasswordStatement.setInt(2, id);
		return this.updatePasswordStatement.executeUpdate();
	}
	public int selectSubjectReport()
	{
		return 0;
	}
	public int selectBookReport()
	{
		return 0;
	}
	public int selectFeesReport()
	{
		return 0;
	}
}
