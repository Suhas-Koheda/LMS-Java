package model;

import java.util.Objects;

public class Transaction {
    private String MemID;
    private String BookID;
    private String TranID;
    private String IssueDate;
    private String ReturnDate;
    private String Status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(MemID, that.MemID) && Objects.equals(BookID, that.BookID) && Objects.equals(TranID, that.TranID) && Objects.equals(IssueDate, that.IssueDate) && Objects.equals(ReturnDate, that.ReturnDate) && Objects.equals(Status, that.Status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MemID, BookID, TranID, IssueDate, ReturnDate, Status);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "MemID='" + MemID + '\'' +
                ", BookID='" + BookID + '\'' +
                ", TranID='" + TranID + '\'' +
                ", IssueDate='" + IssueDate + '\'' +
                ", ReturnDate='" + ReturnDate + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

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
