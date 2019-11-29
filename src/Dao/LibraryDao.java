package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import books.Books;
import copies.Copies;
import issuerecord.Issuerecord;
import utils.DBUtils;

public class LibraryDao 
{
	private Connection connection;
	private PreparedStatement editProfile;
	private PreparedStatement updatePasswordStatement;
	private PreparedStatement findBook;
	private PreparedStatement checkBookAvability;
	private PreparedStatement addBook;
	private PreparedStatement addNewCopy;
	private PreparedStatement issueBookCopy1;
	private PreparedStatement issueBookCopy2;
	private PreparedStatement returnBookCopy;
	private PreparedStatement listIssuedBooks;
	private PreparedStatement editBooks;
	private PreparedStatement changeRack;
	private PreparedStatement addMember;
	private PreparedStatement takePayment;
	private PreparedStatement paymentHistory;
	
	public LibraryDao()throws Exception
	{
		this.connection = DBUtils.getConnection();
		editProfile=connection.prepareStatement("UPDATE users SET email=?,phone=? WHERE id=?");
		updatePasswordStatement=connection.prepareStatement("UPDATE users SET password=? WHERE id=?");
		findBook=connection.prepareStatement("SELECT * FROM books where name=LIKE'%?%'");
		//checkBookAvability=connection.prepareStatement("");
		addBook=connection.prepareStatement("INSERT INTO books(name,author,subject,price,isbn) VALUES(?,?,?,?,?)");
		addNewCopy=connection.prepareStatement("INSERT INTO copies(bookid,rack,status) VALUES(?,?,?)");
		issueBookCopy1=connection.prepareStatement("SELECT * FROM copies where id=?");
		issueBookCopy2=connection.prepareStatement("INSERT INTO (copyid,memberid,issue_date,return_duedate,return_date,fine_amount) VALUES(?,?,?,?,?,?)");
//		returnBookCopy=connection.prepareStatement("");
//		listIssuedBooks=connection.prepareStatement("");
//		editBooks=connection.prepareStatement("");
//		changeRack=connection.prepareStatement("");
		addMember=connection.prepareStatement("INSERT INTO users(name,email,phone,password,role) VALUES(?,?,?,?,?)");
		 takePayment=connection.prepareStatement("update payments Set amount = amount-? where id = ?");

		paymentHistory=connection.prepareStatement("select * from issuerecord where id =?");
	}
	public int addUser(String name,String email,String phone,String password,String role) throws Exception
	{
		this.addMember.setString(1, name);
		this.addMember.setString(2, email);
		this.addMember.setString(3, phone);
		this.addMember.setString(4, password);
		this.addMember.setString(5, role);
	}
	public int issueBookCopy(int memId,int bookId) throws Exception
	{
		this.issueBookCopy1.setInt(1, bookId);
		
		 List<Books> bookList = new ArrayList<Books>();
			try( ResultSet rs = this.issueBookCopy1.executeQuery())
			{
				rs.next();
					Copies book = new Copies(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
					this.issueBookCopy2.setInt(1, book.getId());
					this.issueBookCopy2.setInt(2, book.getBookid());
					this.issueBookCopy2.setString(3, book.get);
			}
			return bookId;
		
	}
	public int updateProfile(String email, String phone,int id)throws Exception
	{
		this.editProfile.setString(1, email);
		this.editProfile.setString(2, phone);
		this.editProfile.setInt(3, id);
		return this.editProfile.executeUpdate();
	}
	public int updatePasswordStatement(String password,int id)throws Exception
	{
		this.updatePasswordStatement.setString(1, password);
		this.updatePasswordStatement.setInt(2, id);
		return this.updatePasswordStatement.executeUpdate();
	}
	public void findBook(String bookName) throws Exception
	{
		this.findBook.setString(1, bookName);
	       List<Books> bookList = new ArrayList<Books>();
			try( ResultSet rs = this.findBook.executeQuery())
			{
				while( rs.next())
				{
					Books book = new Books(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getString(6));
					bookList.add(book);
					System.out.println(book);
				}
			}
	}
	public int addBookCopy(int id,int rack) throws Exception
	{
		int avail = 1;
		this.addNewCopy.setInt(1, id);
		this.addNewCopy.setInt(2, rack);
		this.addNewCopy.setInt(3, avail);
		return this.addNewCopy.executeUpdate();
		
	}
	public int addBook(Books book) throws Exception
	{
		this.addBook.setString(1, book.getName());
		this.addBook.setString(2, book.getAuthor());
		this.addBook.setString(3, book.getSubject());
		this.addBook.setFloat(4, book.getPrice());
		this.addBook.setString(5, book.getIsbn());
		return this.addBook.executeUpdate();
	}
	public int selectBookReport()
	{
		return 0;
	}
	public int selectFeesReport()
	{
		return 0;
	}

	public void paymentHistory(int issueRecordId) throws Exception {
		this.paymentHistory.setInt(1, issueRecordId);
		;
		List<Issuerecord> issueList = new ArrayList<Issuerecord>();
		try (ResultSet rs = this.paymentHistory.executeQuery()) {
			while (rs.next()) {
				Issuerecord record = new Issuerecord(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getFloat(7));
				issueList.add(record);
				System.out.println(record);
			}
		}

	}
	public void takePayment(float payment , int id) throws Exception
	{
	this.takePayment.setFloat(1, payment);
	this.takePayment.setInt(2, id);
	   this.takePayment.executeUpdate();
	   System.out.println("payment Successful");

	}
}
