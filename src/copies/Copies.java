package copies;

public class Copies 
{
	private int id;
	private int bookid;
	private int rack;
	private int status;
	public Copies(int id, int bookid, int rack, int status) {
		this.id = id;
		this.bookid = bookid;
		this.rack = rack;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getRack() {
		return rack;
	}
	public void setRack(int rack) {
		this.rack = rack;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
