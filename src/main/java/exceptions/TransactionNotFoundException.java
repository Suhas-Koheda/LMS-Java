package exceptions;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException(String s) {
        super("Transaction not Found");
    }
}
