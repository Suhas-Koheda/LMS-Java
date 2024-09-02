package model;

public class Transaction {
    private String MemID;
    private String BookID;
    private String TranID;
    private String IssueDate;
    private String ReturnDate;

    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
    }

    public void setTranID(String tranID) {
        TranID = tranID;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getTranID() {
        return TranID;
    }
}
