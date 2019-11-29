package issuerecord;

public class Issuerecord {
	private int id;
	private int copyid;
	private int memberid;
	private String issue_date;
	private String return_duedate;
	private String return_date;
	private float fine_amount;

	public Issuerecord(int id, int copyid, int memberid, String issue_date, String return_duedate, String return_date,
			float fine_amount) {
		super();
		this.id = id;
		this.copyid = copyid;
		this.memberid = memberid;
		this.issue_date = issue_date;
		this.return_duedate = return_duedate;
		this.return_date = return_date;
		this.fine_amount = fine_amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCopyid() {
		return copyid;
	}

	public void setCopyid(int copyid) {
		this.copyid = copyid;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getReturn_duedate() {
		return return_duedate;
	}

	public void setReturn_duedate(String return_duedate) {
		this.return_duedate = return_duedate;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public float getFine_amount() {
		return fine_amount;
	}

@Override
	public String toString() {
		return "Issuerecord [id=" + id + ", copyid=" + copyid + ", memberid=" + memberid + ", issue_date=" + issue_date
				+ ", return_duedate=" + return_duedate + ", return_date=" + return_date + ", fine_amount=" + fine_amount
				+ "]";
	}

public void setFine_amount(float fine_amount) {
this.fine_amount = fine_amount;
	}
}

