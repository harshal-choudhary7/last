import java.util.Scanner;

import Dao.LibraryDao;
import Dao.MemberDao;
import Dao.OwnerDao;
import books.Books;

public class Program 
{
	static Scanner sc = new Scanner(System.in);
	public int MainId;
	public static int menuList()
	{
		System.out.println("0.Exit");
		System.out.println("1.Sign In");
		System.out.println("2 Sign Up");
		System.out.print("Enter choice	:	");
		return sc.nextInt();
	}
	public static void main(String[] args) throws Exception 
	{
		int choice,id;
		int MainId;
		String name, email;
		String password,phone;
		OwnerDao owner= new OwnerDao();
		while((choice = Program.menuList()) !=0 )
		{
			switch(choice)
			{
			case 1:
				System.out.println("Enter your emailId: ");
				sc.nextLine();
				email = sc.nextLine();
				System.out.println("Enter Password: ");
				password = sc.nextLine();
				
				if(owner.checkCredentials(email, password)==0)
				{
					System.out.println("owner");
					MainId = owner.getId(email);
					while((choice = Program.ownerList()) != 0)
					{
						
						switch(choice)
						{
						case 1:
							System.out.print("Enter email :: ");
							sc.nextLine();
							email = sc.nextLine();
							System.out.print("Enter phone :: ");
							phone = sc.nextLine();
							owner.updateProfile(email, phone, MainId);
							break;
						case 2:
							System.out.print("Enter new password:: ");
							sc.nextLine();
							password = sc.nextLine();
							owner.updatePasswordStatement(password, MainId);
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							break;
						}
					}
				}
				else if(owner.checkCredentials(email, password)==1)
				{
					int memberid,bookid;
					float amount;
					int choice1;
					  String email1,phone1,name1,pass;
					  float price;
					  MainId = owner.getId(email);
					  LibraryDao libdao = new LibraryDao();
					while( ( choice1 = Program.libraryMenuList( ) ) != 0 )
					{
					switch( choice1 )
					{
					case 0:
					           System.out.println();
					           break;
					case 1:
					          System.out.println("enter new email");
					          sc.nextLine();
					          email1 = sc.nextLine();
					          System.out.println("enter new password ");
					          phone1 = sc.nextLine();
					          libdao.updateProfile(email1, phone1, MainId);
					           break;
					case 2:
					System.out.println("enter new password ");
					          pass = sc.nextLine();
					          libdao.updatePasswordStatement(pass,MainId);
					           break;
					case 3:
					     System.out.println("enter book name");
					     sc.nextLine();
					          libdao.findBook(sc.nextLine());
					           break;
					case 4:    
					System.out.println("enter book id");
					             bookid = sc.nextInt();

					           break;
					case 5:
						
							String author,subject,string,isbn;
					             System.out.println("enter book info ::");
					             System.out.println("enter book name");
					             sc.nextLine();
					          name1 = sc.nextLine();
					          System.out.println("enter book author");
					          author = sc.nextLine();
					          System.out.println("enter book subject");
					          subject = sc.nextLine();
					          System.out.println("enter book price");
					          price = sc.nextFloat();
					          sc.nextLine();
					          System.out.println("enter book isbn");
					          isbn = sc.nextLine();
					          Books book = new Books(name1, author, subject, price, isbn);
					          libdao.addBook(book);
					           break;
					           //ALTER TABLE books ADD id int NOT NULL AUTO_INCREMENT
					case 6:
					System.out.println("enter book id");
					             bookid = sc.nextInt();
					          System.out.println("enter book rack");
					             int rack = sc.nextInt();
					             libdao.addBookCopy(bookid, rack);
					           break;
					case 7:
					System.out.println("enter member id");
					             memberid = sc.nextInt();
					System.out.println("enter book id");
					             bookid = sc.nextInt();
					     
					           break;
					case 8:
					System.out.println("enter issue id");
					int bookIssueId =sc.nextInt();
					//functionality
					           System.out.println("book returned suxccesfully");
					           break;
					case 9:
					System.out.println("enter user id");
					             memberid = sc.nextInt();
					           System.out.println();
					           break;
					case 10:
					           System.out.println();
					           break;
					case 11:
					           System.out.println();
					           break;
					case 12:
					           System.out.println();
					           break;
					case 13:
						System.out.println("enter member id");
			            memberid = sc.nextInt();
			           System.out.println("enter payment amount");
			           amount = sc.nextFloat();
			           libdao.takePayment(amount, memberid);
					           break;
					case 14:
						System.out.println("enter member id");
			             memberid = sc.nextInt();
			          libdao.paymentHistory( memberid);
			           System.out.println();
			           break;
					           

					}
					

					}
				}
				else if(owner.checkCredentials(email, password)==2)
				{
					int choice2;
					MemberDao memdao = new MemberDao();
					while ((choice2 = Program.memberMenuList()) != 0) {
						switch (choice2) {
						case 1:
							System.out.println("enter new email");
							sc.nextLine();
							String email1 = sc.nextLine();
							System.out.println("enter new password ");
							String phone1 = sc.nextLine();
							memdao.editProfile(email1, phone1, MainId);
							break;
						case 2:
							System.out.println("enter new password ");
							String pass = sc.nextLine();
							memdao.changePassword(MainId, pass);
							break;
						case 3:
							System.out.println("enter book name ");
							String name1 = sc.nextLine();
							memdao.findBookByName(name1);
							break;
						case 4:
							System.out.println("enter book id ");
							int id1 = sc.nextInt();
							memdao.checkBookAvailability(id1);
							break;
						case 5:
							System.out.println("enter user id");
							int memberid = sc.nextInt();
							memdao.listIssuedBooks(memberid);
							break;
						case 6:
							System.out.println("enter member id ");
							int paymentid = sc.nextInt();
							memdao.paymentHistory(paymentid);
							break;
						}
					}
				}
				else
				{
					System.out.println("invalid user name");
				}
				break;
			case 2:
				System.out.println("Enter Name: ");
				name = sc.nextLine();
				System.out.println("Enter Phone Number");
				phone = sc.nextLine();
				System.out.println("Enter your emailId: ");
				email = sc.nextLine();
				System.out.println("Enter Password: ");
				password = sc.nextLine();
				break;
			}
		}
	}
	public static int libraryMenuList( )
	{
	System.out.println("0.sign out");
	System.out.println("1.edit profile");
	System.out.println("2.change password");
	System.out.println("3.find book by name");
	System.out.println("4.check book availabilty");
	System.out.println("5.add new book");
	System.out.println("6.add new copy");
	System.out.println("7.issue book copy");;
	System.out.println("8.return book copy");;
	System.out.println("9.list issued books");;
	System.out.println("10.edit book");;
	System.out.println("11.change rack");;
	System.out.println("12.add member");;
	System.out.println("13.take payment");;
	System.out.println("14.payment history");;

	System.out.print("Enter choice : ");
	return sc.nextInt();
	}
	public static int memberMenuList()
	{
	System.out.println("0.sign out");
	System.out.println("1.edit profile");
	System.out.println("2.change password");
	System.out.println("3.find book by name");
	System.out.println("4.check book availabilty");
	System.out.println("5.list issued books");
	System.out.println("6.payment history");

	System.out.print("Enter choice : ");
	return sc.nextInt();
	}
	private static int ownerList() 
	{
		System.out.println("1. edit profile");
		System.out.println("2. change password");
		System.out.println("3. subjectwise report");
		System.out.println("4. bookwise report");
		System.out.println("5. fess report");
		return sc.nextInt();
	}
}
