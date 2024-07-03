package model;

public class Transaction {
    private String MemID;
    private String BookID;
    private String TranID;

    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
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
