package Dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import books.Books;
import utils.DBUtils;

public class MemberDao implements Closeable {
	private Connection connection;
	private PreparedStatement editProfile;
	private PreparedStatement changePassword;
	private PreparedStatement findBookByName;
	private PreparedStatement checkBookAvailabilty;
	private PreparedStatement listIssuedBooks;
	private PreparedStatement paymentHistory;

	public MemberDao() throws Exception {
		this.connection = DBUtils.getConnection();
		this.editProfile = connection.prepareStatement("UPDATE users set email=?,phone=? WHERE id = ?");
		this.changePassword = connection.prepareStatement("UPDATE users SET password=? WHERE id=?");
		this.findBookByName = connection.prepareStatement("SELECT * FROM books where name LIKE'%?%'");
		this.checkBookAvailabilty = connection.prepareStatement("SELECT COUNT(*) FROM copies where bookid=? && status = 'available'");
		this.listIssuedBooks = connection.prepareStatement("SELECT * FROM payments where id=?");
		this.paymentHistory = connection.prepareStatement("SELECT * FROM issuerecord where memberid=?");

	}

	public int editProfile(String email, String phone, int id) throws Exception {
		this.editProfile.setString(1, email);
		this.editProfile.setString(2, phone);
		this.editProfile.setInt(3, id);
		return this.editProfile.executeUpdate();
	}

	public int changePassword(int id, String password) throws Exception {
		this.changePassword.setString(1, password);
		this.changePassword.setInt(2, id);
		return this.changePassword.executeUpdate();

	}

	public void findBookByName(String name) throws Exception {
		this.findBookByName.setString(1, name);
		List<Books> bookList = new ArrayList<Books>();
		try (ResultSet rs = this.findBookByName.executeQuery()) {
			while (rs.next()) {
				Books book = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5),
						rs.getString(6));
				bookList.add(book);
				System.out.println(book);
			}
		}
	}

	public void checkBookAvailability(int bookid) throws Exception {
		this.checkBookAvailabilty.setInt(1, bookid);
		this.checkBookAvailabilty.executeQuery();
	}

	public void listIssuedBooks(int memberid) throws Exception {
		this.listIssuedBooks.setInt(1, memberid);
		this.listIssuedBooks.executeQuery();
	}

	public void paymentHistory(int paymentid) throws Exception {
		this.paymentHistory.setInt(1, paymentid);
		this.paymentHistory.executeQuery();
	}

	@Override
	public void close() throws IOException {
		try {
			this.editProfile.close();
			this.changePassword.close();
			this.findBookByName.close();
			this.checkBookAvailabilty.close();
			this.listIssuedBooks.close();
			this.paymentHistory.close();
			this.connection.close();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}